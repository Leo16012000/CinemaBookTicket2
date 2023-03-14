package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Session {

    private int id, userId;
    private Timestamp startTime, endTime;
    private User user;
    private String message;

    public Session() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.userId = user.getId();
        }
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Session getFromResultSet(ResultSet rs) throws SQLException {
        Session s = new Session();
        s.setId(rs.getInt("id"));
        s.setUserId(rs.getInt("user_id"));
        s.setMessage(rs.getNString("message"));
        s.setStartTime(rs.getTimestamp("start_time"));
        s.setEndTime(rs.getTimestamp("end_time"));
        return s;
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", userId=" + userId + ", startTime=" + startTime + ", endTime=" + endTime + ", message=" + message + '}';
    }

}
