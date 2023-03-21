package com.leo.controllers.popup;

import com.leo.dao.MovieDao;
import com.leo.models.Movie;

public class MoviePopupController {
  private MovieDao movieDao = MovieDao.getInstance();

  public Movie addMovie(Movie movie) throws Exception {
    return movieDao.get(movieDao.save(movie));
  }

  public Movie editMovie(Movie movie) throws Exception {
    String title = movie.getTitle();
    String country = movie.getCountry();
    if (title.isEmpty() || country.isEmpty()) {
      throw new Exception("Please complete all infomation!");
    }
    movieDao.update(movie);
    return movieDao.get(movie.getId());
  }
}
