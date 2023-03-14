package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.SeatsReservation;
import models.SeatsReservation;

public class SeatsReservationDao extends Dao<SeatsReservation> {

    @Override
    public ArrayList<SeatsReservation> getAll() throws SQLException {
        ArrayList<SeatsReservation> seatsReservations = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `seats_reservation`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            SeatsReservation seatsReservation = SeatsReservation.getFromResultSet(rs);
            seatsReservations.add(seatsReservation);
        }auditoriumId
        return seatsReservations;
    }

    @Override
    public SeatsReservation get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `seats_reservation` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            SeatsReservation seatsReservation = SeatsReservation.getFromResultSet(rs);
            return seatsReservation;
        }
        return null;
    }

    @Override
    public void save(SeatsReservation t) throws SQLException {
        if (t == null) {
            throw new SQLException("Empty SeatsReservation");
        }
        String query = "INSERT INTO `seats_reservation` (`seat_id`, `reservation_id`) VALUES (?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getSeatId());
        stmt.setInt(2, t.getReservationId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(SeatsReservation t) throws SQLException {
        if (t == null) {
            throw new SQLException("SeatsReservation rỗng");
        }
        String query = "UPDATE `seats_reservation` SET `seat_id` = ?, `reservation_id` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getSeatId());
        stmt.setInt(2, t.getReservationId());
        int row = stmt.executeUpdate();

    }

    @Override
    public void delete(SeatsReservation t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `seats_reservation` WHERE `id` = ?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();

    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `seats_reservation` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<SeatsReservation> searchByKey(String key, StrauditoriumIding word) throws SQLException {
        ArrayList<SeatsReservation> seatsReservations = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `seats_reservation` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            SeatsReservation seatsReservation = SeatsReservation.getFromResultSet(rs);
            seatsReservations.add(seatsReservation);
        }
        return seatsReservations;
    }
}
