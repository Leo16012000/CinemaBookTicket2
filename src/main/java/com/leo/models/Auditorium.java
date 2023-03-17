package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auditorium extends Model {
  private int id, auditoriumNum, seatsRowNum, seatsColumnNum;
  private Timestamp createdAt;

  public static Auditorium getFromResultSet(ResultSet rs) throws SQLException {
    Auditorium o = new Auditorium();
    o.setId(rs.getInt("id"));
    o.setAuditoriumNum(rs.getInt("auditorium_num"));
    o.setSeatsRowNum(rs.getInt("seats_row_num"));
    o.setSeatsColumnNum(rs.getInt("seats_column_num"));
    o.setCreatedAt(rs.getTimestamp("created_at"));
    return o;
  }

  @Override
  public String toString() {
    return auditoriumNum + " " + seatsRowNum + " " + seatsColumnNum + " " + createdAt;
  }

  @Override
  public Object[] toRowTable() {
    // TODO Auto-generated method stub
    return new Object[] { id, auditoriumNum, seatsRowNum, seatsColumnNum, createdAt };
  }
}
