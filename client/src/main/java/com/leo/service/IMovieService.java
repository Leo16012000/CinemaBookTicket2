package com.leo.service;

import java.util.List;

import com.leo.models.Movie;

public interface IMovieService {
  List<Movie> searchByKey(String key, String term);

  List<Movie> getAll();

  Movie get(int selectedId);

  void deleteById(int i);

  void save(Movie movie);

  void update(Movie movie);
}
