package com.leo.service;

import java.io.IOException;
import java.util.List;

import com.leo.models.Auditorium;

public interface IAuditoriumService {
  Auditorium get(int auditoriumId) throws IOException;

  List<Auditorium> getAll() throws IOException;

  List<Auditorium> searchByKey(String key, String term) throws IOException;

  void deleteByIds(List<Integer> ids) throws IOException;

  void save(Auditorium auditorium) throws IOException;

  void update(Auditorium auditorium) throws IOException;
}
