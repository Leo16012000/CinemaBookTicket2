package com.leo.service;

import java.io.IOException;
import java.util.List;

import com.leo.models.Showtime;

public interface IShowtimeService {

  Showtime get(int showtimeId) throws IOException;

  List<Showtime> getAll() throws IOException;

  List<Showtime> searchByKey(String key, String term) throws IOException;

  void save(Showtime showtime) throws IOException;

  void update(Showtime showtime) throws IOException;

  void deleteByIds(List<Integer> selectedIds) throws IOException;

  List<Showtime> getMovieShowtimes(int movieId) throws IOException;
}
