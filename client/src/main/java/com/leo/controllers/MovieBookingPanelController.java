package com.leo.controllers;

import java.io.IOException;
import java.util.List;

import com.leo.models.Auditorium;
import com.leo.models.Movie;
import com.leo.models.Showtime;
import com.leo.service.IAuditoriumService;
import com.leo.service.IMovieService;
import com.leo.service.IShowtimeService;
import com.leo.service.impl.AuditoriumService;
import com.leo.service.impl.MovieService;
import com.leo.service.impl.ShowtimeService;

public class MovieBookingPanelController {
  private IMovieService movieService = MovieService.getInstance();
  private IAuditoriumService auditoriumService = AuditoriumService.getInstance();
  private IShowtimeService showtimeService = ShowtimeService.getInstance();

  public Movie getMovie(int movieId) throws IOException {
    return movieService.get(movieId);
  }

  public List<Showtime> getMovieShowtimes(int movieId) throws IOException {
    return showtimeService.getMovieShowtimes(movieId);
  }

  public Auditorium getAuditorium(Showtime showtime) throws IOException {
    return auditoriumService.get(showtime.getAuditoriumId());
  }
}
