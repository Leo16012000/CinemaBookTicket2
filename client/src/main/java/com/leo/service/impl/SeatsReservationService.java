package com.leo.service.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.utils.ServiceHandler;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SeatAuditoriumRequestDto;
import com.leo.models.SeatAuditorium;
import com.leo.models.SeatSelection;
import com.leo.service.ISeatsReservationService;
import com.leo.utils.Sockets;

public class SeatsReservationService implements ISeatsReservationService {
  private static ISeatsReservationService instance;
  private ServiceHandler serviceHandler = ServiceHandler.getInstance();

  @Override
  public SeatAuditorium getSeatAuditorium(Integer showtimeId, Integer userId) throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "GET_AUDITORIUM_SEATS",
            SeatAuditoriumRequestDto.builder().showtimeId(showtimeId).userId(userId).build(),
            new TypeReference<ResponseDto<SeatAuditorium>>() {
            })
        .getPayload();
  }

  @Override
  public void unhold(SeatSelection seatSelection) throws IOException {
    serviceHandler
        .sendRequest(Sockets.getSocket(), "UNHOLD_SEAT", seatSelection,
            new TypeReference<ResponseDto<Void>>() {
            });
  }

  @Override
  public void hold(SeatSelection seatSelection) throws IOException {
    serviceHandler
        .sendRequest(Sockets.getSocket(), "HOLD_SEAT", seatSelection,
            new TypeReference<ResponseDto<Void>>() {
            });
  }

  @Override
  public void bookSeats(Integer userId, Integer showtimeId) throws IOException {
    serviceHandler
        .sendRequest(Sockets.getSocket(), "BOOK_SEATS",
            SeatAuditoriumRequestDto.builder().showtimeId(showtimeId).userId(userId).build(),
            new TypeReference<ResponseDto<Void>>() {
            });
  }

  public static ISeatsReservationService getInstance() {
    if (instance == null) {
      synchronized (SeatsReservationService.class) {
        if (instance == null) {
          instance = new SeatsReservationService();
        }
      }
    }
    return instance;
  }
}
