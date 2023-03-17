package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatsReservation {
  private int id, seatId, reservationId;
  private Timestamp createdAt;

  public static SeatsReservation getFromResultSet(ResultSet rs) throws SQLException {
    SeatsReservation o = new SeatsReservation();
    o.setId(rs.getInt("id"));
    o.setSeatId(rs.getInt("seatId"));
    o.setReservationId(rs.getInt("reservationId"));
    o.setCreatedAt(rs.getTimestamp("createdAt"));
    return o;
  }
}
