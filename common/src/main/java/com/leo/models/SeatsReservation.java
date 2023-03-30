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
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatsReservation {
  private int id, seatId, userId, showtimeId;
  private Timestamp createdAt, expiredAt;
  private SeatsReservationStatus status;

  public static SeatsReservation getFromResultSet(ResultSet rs) throws SQLException {
    SeatsReservation o = new SeatsReservation();
    o.setId(rs.getInt("id"));
    o.setSeatId(rs.getInt("seat_id"));
    o.setCreatedAt(rs.getTimestamp("created_at"));
    o.setUserId(rs.getInt("user_id"));
    o.setShowtimeId(rs.getInt("showtime_id"));
    o.setExpiredAt(rs.getTimestamp("expired_at"));
    o.setStatus(SeatsReservationStatus.valueOf(rs.getString("status")));
    return o;
  }

  public enum SeatsReservationStatus {
    PENDING, BOOKING
  }
}
