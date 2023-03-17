package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {
  private int id, userId, showtimeId;
  private Timestamp createdAt;
  private User user;
  private Showtime showtime;

  public static Reservation getFromResultSet(ResultSet rs) throws SQLException {
    Reservation o = new Reservation();
    o.setId(rs.getInt("id"));
    o.setUserId(rs.getInt("userId"));
    o.setShowtimeId(rs.getInt("showtimeId"));
    o.setCreatedAt(rs.getTimestamp("createdAt"));
    return o;
  }
}
