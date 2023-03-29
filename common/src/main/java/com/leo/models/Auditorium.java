package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Auditorium extends Model {
  private int id, auditoriumNum, seatsRowNum, seatsColumnNum;
  private Timestamp createdAt;

  public Auditorium(int id, int auditoriumNum, int seatsRowNum, int seatsColumnNum) {
    this.id = id;
    this.auditoriumNum = auditoriumNum;
    this.seatsRowNum = seatsRowNum;
    this.seatsColumnNum = seatsColumnNum;
  }

  public Auditorium(int auditoriumNum, int seatsRowNum, int seatsColumnNum) {
    this.auditoriumNum = auditoriumNum;
    this.seatsRowNum = seatsRowNum;
    this.seatsColumnNum = seatsColumnNum;
  }

  public Auditorium() {
  }

  public static Auditorium getFromResultSet(ResultSet rs) throws SQLException {
    Auditorium o = new Auditorium();
    o.setId(rs.getInt("id"));
    o.setAuditoriumNum(rs.getInt("auditorium_num"));
    o.setSeatsRowNum(rs.getInt("seats_row_num"));
    o.setSeatsColumnNum(rs.getInt("seats_column_num"));
    o.setCreatedAt(rs.getTimestamp("created_at"));
    return o;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAuditoriumNum() {
    return auditoriumNum;
  }

  public void setAuditoriumNum(int auditoriumNum) {
    this.auditoriumNum = auditoriumNum;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public int getSeatsRowNum() {
    return seatsRowNum;
  }

  public void setSeatsRowNum(int seatsRowNum) {
    this.seatsRowNum = seatsRowNum;
  }

  public int getSeatsColumnNum() {
    return seatsColumnNum;
  }

  public void setSeatsColumnNum(int seatsColumnNum) {
    this.seatsColumnNum = seatsColumnNum;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return Integer.toString(auditoriumNum);
  }

  @Override
  public Object[] toRowTable() {
    // TODO Auto-generated method stub
    return new Object[] { id, auditoriumNum, seatsRowNum, seatsColumnNum, createdAt };
  }
}
