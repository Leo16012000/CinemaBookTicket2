package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Showtime;

public class ShowtimeDao extends Dao<Showtime> {
	public static ShowtimeDao instance = null;
    @Override
    public ArrayList<Showtime> getAll() throws SQLException {
        ArrayList<Showtime> showtimes = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `showtimes`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Showtime seat = Showtime.getFromResultSet(rs);
            showtimes.add(seat);
        }
        return showtimes;
    }

    @Override
    public Showtime get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `showtimes` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Showtime seat = Showtime.getFromResultSet(rs);
            return seat;
        }
        return null;
    }

    @Override
    public void save(Showtime t) throws SQLException {
        if (t == null) {
            throw new SQLException("Empty Showtime");
        }
        String query = "INSERT INTO `showtimes` (`start_time`, `end_time`, `movie_id`) VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setTimestamp(1, t.getStartTime());
        stmt.setTimestamp(2, t.getEndTime());
        stmt.setInt(2, t.getMovieId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Showtime t) throws SQLException {
        if (t == null) {
            throw new SQLException("Showtime rỗng");
        }
        String query = "UPDATE `showtimes` SET `start_time` = ?, `end_time` = ?, `movie_id` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setTimestamp(1, t.getStartTime());
        stmt.setTimestamp(2, t.getEndTime());
        stmt.setInt(2, t.getMovieId());
        int row = stmt.executeUpdate();

    }

    @Override
    public void delete(Showtime t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `showtimes` WHERE `id` = ?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();

    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `showtimes` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<Showtime> searchByKey(String key, String word) throws SQLException {
        ArrayList<Showtime> showtimes = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `showtimes` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Showtime seat = Showtime.getFromResultSet(rs);
            showtimes.add(seat);
        }
        return showtimes;
    }
    
    public static ShowtimeDao getInstance() {
    	if (instance == null) {
    		instance = new ShowtimeDao();
    	}
    	return instance;
    }
}
