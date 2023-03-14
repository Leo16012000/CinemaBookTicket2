package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Auditorium {
	private int id, auditoriumNum, seatsRowNum, seatsColumnNum;
	private Timestamp createdAt; 
	public static Auditorium getFromResultSet(ResultSet rs) throws SQLException {
        Auditorium o = new Auditorium();
        o.setId(rs.getInt("id"));
        o.setAuditoriumNum(rs.getInt("auditorium_num"));
        o.setSeatsRowNum(rs.getInt("seats_row_num"));
        o.setSeatsColumnNum(rs.getInt("seats_column_num"));
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
}
