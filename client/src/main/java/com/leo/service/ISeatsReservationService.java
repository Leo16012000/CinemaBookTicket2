package com.leo.service;

import java.io.IOException;

import com.leo.models.SeatAuditorium;
import com.leo.models.SeatSelection;

public interface ISeatsReservationService {
  SeatAuditorium getSeatAuditorium(Integer showtimeId, Integer userId) throws IOException;

  void unhold(SeatSelection seatSelection) throws IOException;

  void hold(SeatSelection seatSelection) throws IOException;

  void bookSeats(Integer userId, Integer showtimeId) throws IOException;
}
