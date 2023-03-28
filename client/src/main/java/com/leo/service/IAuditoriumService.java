package com.leo.service;

import java.util.List;

import com.leo.models.Auditorium;

public interface IAuditoriumService {
  Auditorium get(int auditoriumId);

  List<Auditorium> getAll();

  List<Auditorium> searchByKey(String string, String valueOf);

  void deleteByIds(List<Integer> ids);

  void save(Auditorium showtime);

  void update(Auditorium showtime);
}
