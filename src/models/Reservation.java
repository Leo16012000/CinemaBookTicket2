package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Reservation {
	private int id, userId, showtimeId;
	private Timestamp createdAt;
	private User user;
	private Showtime showtime;
	public static Reservation getFromResultSet(ResultSet rs) throws SQLException {
        Reservation o = new Reservation();
        o.setId(rs.getInt("id"));
        o.setUserId(rs.getInt("userId"));
        o.setShowtimeId(rs.getInt("showtimeId"));
        o.setCreatedAt(rs.getTimestamp("createdAt"));
        return o;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShowtimeId() {
		return showtimeId;
	}
	public void setShowtimeId(int showtimeId) {
		this.showtimeId = showtimeId;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Showtime getShowtime() {
		return showtime;
	}
	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}
}
