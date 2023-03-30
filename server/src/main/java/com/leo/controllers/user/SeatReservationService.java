package com.leo.controllers.user;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.leo.dao.SeatsReservationDao;
import com.leo.dao.ShowtimeDao;
import com.leo.dao.Transaction;
import com.leo.dao.TransactionManager;
import com.leo.dtos.ResponseDto;
import com.leo.models.Auditorium;
import com.leo.models.Seat;
import com.leo.models.SeatAuditorium;
import com.leo.models.SeatSelection;
import com.leo.models.SeatsReservation;
import com.leo.utils.PrepareStatements;
import com.leo.models.SeatSelection.SeatSelectionStatus;
import com.leo.models.SeatsReservation.SeatsReservationStatus;

public class SeatReservationService {
  private static SeatReservationService instance;

  private SeatsReservationDao seatReservationDao = SeatsReservationDao.getInstance();
  private TransactionManager transactionManager = TransactionManager.getInstance();
  private ShowtimeDao showtimeDao = ShowtimeDao.getInstance();

  public ResponseDto<SeatAuditorium> getSeatAuditorium(Integer showtimeId, Integer userId) {
    ResponseDto<SeatAuditorium> responseDto = new ResponseDto<>();
    try {
      Transaction tx = transactionManager.getTransaction();
      return tx.func(conn -> {
        Timestamp now = Timestamp.from(Instant.now());
        Auditorium auditorium = tx.query(innerConn -> PrepareStatements
            .setPreparedStatementParams(innerConn
                .prepareStatement(
                    "SELECT * FROM cinema.auditoriums a JOIN cinema.showtimes st ON a.id = st.auditorium_id WHERE st.id = ?"),
                showtimeId)
            .executeQuery(), Auditorium::getFromResultSet);
        List<Seat> seats = tx.queryList(innerCon -> PrepareStatements.setPreparedStatementParams(innerCon
            .prepareStatement("SELECT * FROM cinema.seats s WHERE s.auditorium_id = ?"),
            auditorium.getId())
            .executeQuery(), Seat::getFromResultSet);
        List<SeatsReservation> seatsReservations = seatReservationDao.getAllByShowtimeId(showtimeId);
        int auditoriumHeight = auditorium.getSeatsRowNum();
        int auditoriumWidth = auditorium.getSeatsColumnNum();
        SeatSelection[][] seatSelections = new SeatSelection[auditoriumHeight][auditoriumWidth];
        seats.stream().forEach(seat -> {
          Optional<SeatsReservation> seatsReservation = seatsReservations.stream()
              .filter(it -> it.getSeatId() == seat.getId())
              .findFirst();
          SeatSelectionStatus status = seatsReservation
              .map(it -> {
                if (SeatsReservationStatus.PENDING.equals(it.getStatus()) && it.getExpiredAt().before(now)) {
                  return SeatSelectionStatus.AVAILABLE;
                }
                switch (it.getStatus()) {
                  case BOOKING:
                    return SeatSelectionStatus.RESERVED;
                  case PENDING:
                    return SeatSelectionStatus.HOLD;
                  default:
                    throw new UnsupportedOperationException();
                }
              })
              .orElse(SeatSelectionStatus.AVAILABLE);
          SeatSelection seatSelection = SeatSelection.builder()
              .seatColumn(seat.getSeatColumn())
              .seatRow(seat.getSeatRow())
              .seatId(seat.getId())
              .userId(status != SeatSelectionStatus.AVAILABLE
                  && seatsReservation.filter(it -> it.getUserId() == userId).isPresent() ? userId : null)
              .status(status)
              .showtimeId(showtimeId)
              .build();
          seatSelections[seatSelection.getSeatRow()][seatSelection.getSeatColumn()] = seatSelection;
        });

        SeatAuditorium seatAuditorium = SeatAuditorium.builder()
            .auditoriumHeight(auditoriumHeight)
            .auditoriumWidth(auditoriumWidth)
            .auditoriumId(auditorium.getId())
            .showtime(showtimeDao.get(showtimeId))
            .seats(seatSelections)
            .build();

        responseDto.setStatus("SUCCESS");
        responseDto.setMessage("Get seat auditorium successfully");
        responseDto.setPayload(seatAuditorium);
        return responseDto;
      });
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> unhold(SeatSelection seatSelection) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      return transactionManager.getTransaction().func(conn -> {
        Timestamp now = Timestamp.from(Instant.now());
        SeatsReservation seatsReservation = seatReservationDao.getBySeatIdAndShowtimeId(seatSelection.getSeatId(),
            seatSelection.getShowtimeId());
        if (seatsReservation == null || seatsReservation.getStatus() != SeatsReservationStatus.PENDING) {
          responseDto.setMessage("Data out of sync between server and client");
          responseDto.setStatus("FAILURE");
          return responseDto;
        }
        if (seatsReservation.getUserId() == seatSelection.getUserId() || seatsReservation.getExpiredAt().before(now)) {
          seatReservationDao.delete(seatsReservation);
        }
        responseDto.setStatus("SUCCESS");
        responseDto.setMessage("Unhold seat successfully");
        return responseDto;
      });
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<?> hold(SeatSelection seatSelection) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      return transactionManager.getTransaction().func(conn -> {
        Timestamp now = Timestamp.from(Instant.now());
        SeatsReservation seatsReservation = seatReservationDao.getBySeatIdAndShowtimeId(seatSelection.getSeatId(),
            seatSelection.getShowtimeId());
        if (seatsReservation != null) {
          if (seatsReservation.getStatus() == SeatsReservationStatus.BOOKING
              || seatsReservation.getExpiredAt().before(now)) {
            responseDto.setMessage("Data out of sync between server and client");
            responseDto.setStatus("FAILURE");
            return responseDto;
          }
          seatsReservation.setUserId(seatSelection.getUserId());
          seatsReservation.setExpiredAt(now);
          seatReservationDao.update(seatsReservation);
        } else {
          seatReservationDao.save(SeatsReservation.builder()
              .userId(seatSelection.getUserId())
              .seatId(seatSelection.getSeatId())
              .showtimeId(seatSelection.getShowtimeId())
              .status(SeatsReservationStatus.PENDING)
              .expiredAt(new Timestamp(now.getTime() + Duration.ofMinutes(15).toMillis()))
              .build());
        }
        responseDto.setStatus("SUCCESS");
        responseDto.setMessage("Hold seat successfully");
        return responseDto;
      });
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> bookSeats(Integer userId, Integer showtimeId) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      return transactionManager.getTransaction().func(conn -> {
        List<SeatsReservation> pendingReservations = seatReservationDao.getAllByUserIdAndShowtimeId(userId, showtimeId)
            .stream()
            .filter(it -> it.getStatus() == SeatsReservationStatus.PENDING).collect(Collectors.toList());
        for (SeatsReservation pendingReservation : pendingReservations) {
          pendingReservation.setStatus(SeatsReservationStatus.BOOKING);
          pendingReservation.setExpiredAt(null);
          seatReservationDao.update(pendingReservation);
        }
        responseDto.setStatus("SUCCESS");
        responseDto.setMessage("Book seats successfully");
        return responseDto;
      });
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public static SeatReservationService getInstance() {
    if (instance == null) {
      synchronized (SeatReservationService.class) {
        if (instance == null) {
          instance = new SeatReservationService();
        }
      }
    }
    return instance;
  }
}
