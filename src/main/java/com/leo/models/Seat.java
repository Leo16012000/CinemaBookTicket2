package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {
  private int id, auditoriumId, reservationId, seatColumn, seatRow;
  private Timestamp createdAt;

  public static Seat getFromResultSet(ResultSet rs) throws SQLException {
    Seat o = new Seat();
    o.setId(rs.getInt("id"));
    o.setAuditoriumId(rs.getInt("auditorium_id"));
    o.setReservationId(rs.getInt("reservation_id"));
    o.setSeatColumn(rs.getInt("seat_column"));
    o.setSeatRow(rs.getInt("seat_row"));
    o.setCreatedAt(rs.getTimestamp("createdAt"));
    return o;
  }
}
