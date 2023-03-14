package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Reservation;

public class ReservationDao extends Dao<Reservation> {

    @Override
    public ArrayList<Reservation> getAll() throws SQLException {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `reservations`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Reservation reservation = Reservation.getFromResultSet(rs);
            reservations.add(reservation);
        }
        return reservations;
    }

    @Override
    public Reservation get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `reservations` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Reservation reservation = Reservation.getFromResultSet(rs);
            return reservation;
        }
        return null;
    }

    @Override
    public void save(Reservation t) throws SQLException {
        if (t == null) {
            throw new SQLException("Empty Reservation");
        }
        String query = "INSERT INTO `reservations` (`userId`, `showtime_id`) VALUES (?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getUserId());
        stmt.setInt(2, t.getShowtimeId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Reservation t) throws SQLException {
        if (t == null) {
            throw new SQLException("Reservation rỗng");
        }
        String query = "UPDATE `reservations` SET `user_id` = ?, `showtime_id` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getUserId());
        stmt.setInt(2, t.getShowtimeId());
        int row = stmt.executeUpdate();

    }

    @Override
    public void delete(Reservation t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `reservations` WHERE `id` = ?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();

    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `reservations` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<Reservation> searchByKey(String key, String word) throws SQLException {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `reservations` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Reservation reservation = Reservation.getFromResultSet(rs);
            reservations.add(reservation);
        }
        return reservations;
    }
}
