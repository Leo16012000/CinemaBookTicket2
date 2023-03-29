package com.leo.service;

import java.util.List;

import com.leo.models.Seat;

public interface ISeatService {

  List<Seat> getByAuditoriumIdAndShowtimeId(int auditoriumId, int showtimeId);

}
