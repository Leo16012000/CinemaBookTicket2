package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Seat;

public class SeatDao extends Dao<Seat> {
    private static SeatDao instance = null;
    @Override
    public ArrayList<Seat> getAll() throws SQLException {
        ArrayList<Seat> seats = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `seats`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Seat seat = Seat.getFromResultSet(rs);
            seats.add(seat);
        }
        return seats;
    }

    @Override
    public Seat get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `seats` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Seat seat = Seat.getFromResultSet(rs);
            return seat;
        }
        return null;
    }

    @Override
    public void save(Seat t) throws SQLException {
        if (t == null) {
            throw new SQLException("Empty Seat");
        }
        String query = "INSERT INTO `seats` (`auditorium_id`, `seat_num`, `seat_row`) VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getAuditoriumId());
        stmt.setInt(2, t.getSeatNum());
        stmt.setNString(2, t.getSeatRow());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Seat t) throws SQLException {
        if (t == null) {
            throw new SQLException("Seat rỗng");
        }
        String query = "UPDATE `seats` SET `auditorium_id` = ?, `seat_num` = ?, `seat_row` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getAuditoriumId());
        stmt.setInt(2, t.getSeatNum());
        stmt.setNString(2, t.getSeatRow());
        int row = stmt.executeUpdate();

    }

    @Override
    public void delete(Seat t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `seats` WHERE `id` = ?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();

    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `seats` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<Seat> searchByKey(String key, String word) throws SQLException {
        ArrayList<Seat> seats = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `seats` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Seat seat = Seat.getFromResultSet(rs);
            seats.add(seat);
        }
        return seats;
    }
    
    public static SeatDao getInstance() {
        if (instance == null) {
            instance = new SeatDao();
        }
        return instance;
    }
    
    public ArrayList<Seat> getByAuditoriumIdAndShowtimeId(int auditoriumId, int showtimeId) throws SQLException {
    	ArrayList<Seat> seats = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `seats` s"
        		+ "inner join `seats_reservation` sr"
        		+ "inner join  reservations r"
        		+ "inner join showtimes sh"
        		+ "ON sr.seat_id = s.id "
        		+ "ON r.id = sr.reservation_id "
        		+ "ON r.showtime_id = sh.id"
        		+ "WHERE auditorium_id = " + auditoriumId + " AND showtime_id = " + showtimeId;
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Seat seat = Seat.getFromResultSet(rs);
            seats.add(seat);
        }
        return null;
    }
}
