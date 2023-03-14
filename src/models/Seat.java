package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
}
