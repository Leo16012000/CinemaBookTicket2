package com.leo.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leo.models.Movie;
import com.leo.service.IMovieService;
import com.leo.service.impl.MovieService;

public class MoviePanelController {
  private IMovieService movieService = MovieService.getInstance();

  public List<Movie> searchMovie(String keyword, String term) throws IOException {
    final Map<String, String> keywordColumnMap = new HashMap<String, String>() {
      {
        put("title", "title");
        put("duration time", "duration_time");
        put("country", "country");
        put("ticket price", "price");
      }
    };
    return movieService.searchByKey(keywordColumnMap.get(keyword.trim().toLowerCase()), term);
  }

  public List<Movie> getAllMovies() throws IOException {
    return movieService.getAll();
  }
}
