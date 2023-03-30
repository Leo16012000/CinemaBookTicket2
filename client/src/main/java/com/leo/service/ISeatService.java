package com.leo.service;

import java.io.IOException;
import java.util.List;

import com.leo.models.Seat;

public interface ISeatService {

  List<Seat> getByAuditoriumIdAndShowtimeId(int auditoriumId, int showtimeId) throws IOException;

}
