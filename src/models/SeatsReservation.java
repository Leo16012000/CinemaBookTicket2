package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
