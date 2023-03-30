package com.leo.models;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
  private int id, auditoriumId, seatColumn, seatRow;
  private Timestamp createdAt;

  public static Seat getFromResultSet(ResultSet rs) throws SQLException {
    Seat o = new Seat();
    o.setId(rs.getInt("id"));
    o.setAuditoriumId(rs.getInt("auditorium_id"));
    o.setSeatColumn(rs.getInt("seat_column"));
    o.setSeatRow(rs.getInt("seat_row"));
    o.setCreatedAt(rs.getTimestamp("created_at"));
    return o;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAuditoriumId() {
    return auditoriumId;
  }

  public void setAuditoriumId(int auditoriumId) {
    this.auditoriumId = auditoriumId;
  }

  public int getSeatRow() {
    return seatRow;
  }

  public void setSeatRow(int seatRow) {
    this.seatRow = seatRow;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public int getSeatColumn() {
    return seatColumn;
  }

  public void setSeatColumn(int seatColumn) {
    this.seatColumn = seatColumn;
  }
}
