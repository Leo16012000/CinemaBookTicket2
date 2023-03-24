package com.leo.controllers;

import java.sql.SQLException;
import java.util.List;

import com.leo.dao.AuditoriumDao;
import com.leo.dao.MovieDao;
import com.leo.models.Auditorium;
import com.leo.models.Movie;
import com.leo.models.Showtime;

public class MovieBookingPanelController {
  private MovieDao movieDao = MovieDao.getInstance();
  private AuditoriumDao auditoriumDao = AuditoriumDao.getInstance();

  public Movie getMovie(int movieId) throws SQLException {
    return movieDao.get(movieId);
  }

  public List<Showtime> getMovieShowtimes(int movieId) throws SQLException {
    return movieDao.getMovieShowtimes(movieId);
  }

  public Auditorium getAuditorium(Showtime showtime) throws SQLException {
    return auditoriumDao.get(showtime.getAuditoriumId());
  }
}
