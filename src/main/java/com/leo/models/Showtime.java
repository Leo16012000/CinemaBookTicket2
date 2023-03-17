package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Showtime {
  private int id, movieId;
  private Timestamp startTime, endTime, createdAt;
  private Movie movie;

  public static Showtime getFromResultSet(ResultSet rs) throws SQLException {
    Showtime s = new Showtime();
    s.setId(rs.getInt("id"));
    s.setMovieId(rs.getInt("movie_id"));
    s.setStartTime(rs.getTimestamp("start_time"));
    s.setEndTime(rs.getTimestamp("end_time"));
    s.setCreatedAt(rs.getTimestamp("created_at"));
    return s;
  }
}
