package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class SeatsReservation {
  private int id, seatId, reservationId;
  private Timestamp createdAt;

  public static SeatsReservation getFromResultSet(ResultSet rs) throws SQLException {
    SeatsReservation o = new SeatsReservation();
    o.setId(rs.getInt("id"));
    o.setSeatId(rs.getInt("seat_id"));
    o.setReservationId(rs.getInt("reservation_id"));
    o.setCreatedAt(rs.getTimestamp("created_at"));
    return o;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSeatId() {
    return seatId;
  }

  public void setSeatId(int seatId) {
    this.seatId = seatId;
  }

  public int getReservationId() {
    return reservationId;
  }

  public void setReservationId(int reservationId) {
    this.reservationId = reservationId;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
