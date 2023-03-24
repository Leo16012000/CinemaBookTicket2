package com.leo.controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leo.dao.MovieDao;
import com.leo.models.Movie;

public class MoviePanelController {
  private MovieDao movieDao = MovieDao.getInstance();

  public List<Movie> searchMovie(String keyword, String term) throws SQLException {
    final Map<String, String> keywordColumnMap = new HashMap<String, String>() {
      {
        put("title", "title");
        put("duration time", "duration_time");
        put("country", "country");
        put("ticket price", "price");
      }
    };
    return movieDao.searchByKey(keywordColumnMap.get(keyword.trim().toLowerCase()), term);
  }

  public List<Movie> getAllMovies() throws SQLException {
    return movieDao.getAll();
  }
}
