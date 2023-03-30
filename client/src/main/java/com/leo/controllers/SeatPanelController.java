package com.leo.controllers;

import java.io.IOException;
import java.util.Optional;

import com.leo.main.SessionManager;
import com.leo.models.SeatAuditorium;
import com.leo.models.SeatSelection;
import com.leo.service.ISeatsReservationService;
import com.leo.service.impl.SeatsReservationService;

public class SeatPanelController {
  private ISeatsReservationService seatReservationService = SeatsReservationService.getInstance();
  private SessionManager sessionManager = SessionManager.getInstance();

  public SeatAuditorium getSeatAuditorium(int showtimeId) throws IOException {
    return seatReservationService.getSeatAuditorium(showtimeId,
        Optional.ofNullable(sessionManager.getSession()).map(it -> it.getId()).orElse(null));
  }

  public void reserve(int showtimeId) throws IOException {
    seatReservationService
        .bookSeats(Optional.ofNullable(sessionManager.getSession()).map(it -> it.getId()).orElse(null), showtimeId);
  }

  public void hold(SeatSelection model) throws IOException {
    seatReservationService.hold(model);
  }

  public void unhold(SeatSelection model) throws IOException {
    seatReservationService.unhold(model);
  }
}
