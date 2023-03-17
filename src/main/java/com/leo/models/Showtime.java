package com.leo.models;

import com.leo.dao.AuditoriumDao;
import com.leo.dao.MovieDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Showtime extends Model{
  private int id, movieId, auditoriumId;
  private Timestamp startTime, endTime, createdAt;
  private Movie movie;

  public void setAuditorium(Auditorium auditorium) {
    this.auditorium = auditorium;
  }

  private Auditorium auditorium;

  public static Showtime getFromResultSet(ResultSet rs) throws SQLException {
    Showtime s = new Showtime();
    s.setId(rs.getInt("id"));
    s.setMovieId(rs.getInt("movie_id"));
    s.setAuditoriumId(rs.getInt("auditorium_id"));
    s.setStartTime(rs.getTimestamp("start_time"));
    s.setEndTime(rs.getTimestamp("end_time"));
    s.setCreatedAt(rs.getTimestamp("created_at"));
    s.setMovie(MovieDao.getInstance().get(s.getMovieId()));
    s.setAuditorium(AuditoriumDao.getInstance().get(s.getAuditoriumId()));
    return s;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  @Override
  public String toString() {
    return id + " " + startTime + " " + endTime + " " + movieId + " " + auditoriumId;
  }

  @Override
  public Object[] toRowTable() {
    return new Object[]{startTime, endTime, auditorium.getAuditoriumNum(), movie.getTitle()};
  }

  public void setAuditoriumId(int auditoriumId) {
    this.auditoriumId = auditoriumId;
  }

  public int getAuditoriumId() {
    return auditoriumId;
  }
}
