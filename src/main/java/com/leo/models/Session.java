package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session {
  private int id, userId;
  private Timestamp startTime, endTime;
  private User user;
  private String message;

  public Session() {
  }

  public static Session getFromResultSet(ResultSet rs) throws SQLException {
    Session s = new Session();
    s.setId(rs.getInt("id"));
    s.setUserId(rs.getInt("user_id"));
    s.setMessage(rs.getNString("message"));
    s.setStartTime(rs.getTimestamp("start_time"));
    s.setEndTime(rs.getTimestamp("end_time"));
    return s;
  }

  @Override
  public String toString() {
    return "Session{" + "id=" + id + ", userId=" + userId + ", startTime=" + startTime + ", endTime=" + endTime
        + ", message=" + message + '}';
  }

}
