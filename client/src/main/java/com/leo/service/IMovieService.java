package com.leo.service;

import java.io.IOException;
import java.util.List;

import com.leo.models.Movie;

public interface IMovieService {
  List<Movie> searchByKey(String key, String term) throws IOException;

  List<Movie> getAll() throws IOException;

  Movie get(int selectedId) throws IOException;

  void save(Movie movie) throws IOException;

  void update(Movie movie) throws IOException;

  void deleteByIds(List<Integer> selectedIds) throws IOException;
}
