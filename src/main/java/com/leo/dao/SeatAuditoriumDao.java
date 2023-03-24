package com.leo.dao;

import com.leo.models.Auditorium;
import com.leo.models.Reservation;
import com.leo.models.Seat;
import com.leo.models.SeatAuditorium;
import com.leo.models.SeatSelection;
import com.leo.models.SeatsReservation;
import com.leo.models.Showtime;
import com.leo.models.SeatSelection.SeatSelectionStatus;
import com.leo.utils.PrepareStatements;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeatAuditoriumDao {
  private TransactionManager transactionManager = TransactionManager.getInstance();
  private static SeatAuditoriumDao instance;
  private ShowtimeDao showtimeDao = ShowtimeDao.getInstance();
  private ReservationDao reservationDao = ReservationDao.getInstance();

  public List<Showtime> getShowtimes(int movieId, int startTime) throws SQLException {
    return transactionManager.getTransaction().queryList(conn -> PrepareStatements.setPreparedStatementParams(conn
        .prepareStatement("SELECT * FROM cinema.showtimes st WHERE st.start_time = ? AND st.movie_id = ?"), startTime,
        movieId)
        .executeQuery(), Showtime::getFromResultSet);
  }

  public SeatAuditorium getSeatAuditorium(int showtimeId) throws SQLException {
    Transaction tx = transactionManager.getTransaction();
    return tx.func(conn -> {
      Auditorium auditorium = tx.query(innerConn -> PrepareStatements
          .setPreparedStatementParams(innerConn
              .prepareStatement(
                  "SELECT * FROM cinema.auditoriums a JOIN cinema.showtimes st ON a.id = st.auditorium_id WHERE st.id = ?"),
              showtimeId)
          .executeQuery(), Auditorium::getFromResultSet);
      List<Seat> seats = tx.queryList(innerCon -> PrepareStatements.setPreparedStatementParams(innerCon
          .prepareStatement("SELECT * FROM cinema.seats s WHERE s.auditorium_id = ?"), auditorium.getId())
          .executeQuery(), Seat::getFromResultSet);
      List<Reservation> reservations = tx.queryList(innerCon -> PrepareStatements.setPreparedStatementParams(
          innerCon.prepareStatement("SELECT * FROM cinema.reservations r WHERE r.showtime_id = ?"), showtimeId)
          .executeQuery(), Reservation::getFromResultSet);
      List<SeatsReservation> seatsReservations = new ArrayList<>();
      for (Reservation r : reservations) {
        seatsReservations.addAll(getSeatReservations(r.getId()));
      }
      Map<Integer, Boolean> seatReservationMap = seatsReservations.stream()
          .collect(Collectors.toMap(it -> it.getSeatId(), it -> true));
      int auditoriumHeight = auditorium.getSeatsRowNum();
      int auditoriumWidth = auditorium.getSeatsColumnNum();
      SeatSelection[][] seatSelections = new SeatSelection[auditoriumHeight][auditoriumWidth];
      seats.stream().forEach(seat -> {
        SeatSelection seatSelection = SeatSelection.builder()
            .seatColumn(seat.getSeatColumn())
            .seatRow(seat.getSeatRow())
            .seatId(seat.getId())
            .status(seatReservationMap.getOrDefault(seat.getId(), false) ? SeatSelectionStatus.RESERVED
                : SeatSelectionStatus.AVAILABLE)
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
      return seatAuditorium;
    });
  }

  protected List<SeatsReservation> getSeatReservations(int reservationId) throws SQLException {
    return transactionManager.getTransaction().queryList(conn -> PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "SELECT * FROM cinema.seats_reservation rs JOIN cinema.reservations r ON rs.reservation_id = r.id WHERE r.id = ?"),
        reservationId)
        .executeQuery(), SeatsReservation::getFromResultSet);
  }

  public void save(int userId, int showtimeId, List<SeatSelection> seatSelections) throws SQLException {
    transactionManager.getTransaction().run(conn -> {
      Reservation newReservation = Reservation.builder()
          .userId(userId)
          .showtimeId(showtimeId)
          .build();
      int reservationId = reservationDao.save(newReservation);
      for (SeatSelection s : seatSelections) {
        PrepareStatements.setPreparedStatementParams(
            conn.prepareStatement("INSERT INTO cinema.seats_reservation (seat_id, reservation_id) VALUES (?, ?)"),
            s.getSeatId(), reservationId).executeUpdate();
      }
    });
  }

  public static SeatAuditoriumDao getInstance() {
    if (instance == null) {
      synchronized (SeatAuditoriumDao.class) {
        if (instance == null) {
          instance = new SeatAuditoriumDao();
        }
      }
    }
    return instance;
  }
}
