package com.leo.service;

import java.util.List;

import com.leo.models.Showtime;

public interface IShowtimeService {

  Showtime get(int showtimeId);

  void deleteById(int i);

  List<Showtime> getAll();

  List<Showtime> searchByKey(String string, String valueOf);

  void save(Showtime showtime);

  void update(Showtime showtime);

}
