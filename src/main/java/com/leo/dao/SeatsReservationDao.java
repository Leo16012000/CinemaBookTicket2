package com.leo.dao;

import com.leo.models.SeatsReservation;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatsReservationDao extends Dao<SeatsReservation> {

  @Override
  public ArrayList<SeatsReservation> getAll() throws SQLException {
    ArrayList<SeatsReservation> seatsReservations = new ArrayList<>();
    ResultSet rs = conn.prepareStatement("SELECT * FROM `seats_reservation`").executeQuery();
    while (rs.next()) {
      SeatsReservation seatsReservation = SeatsReservation.getFromResultSet(rs);
      seatsReservations.add(seatsReservation);
    }
    return seatsReservations;
  }

  @Override
  public SeatsReservation get(int id) throws SQLException {
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `seats_reservation` WHERE id = ?"), id).executeQuery();
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
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("INSERT INTO `seats_reservation` (`seat_id`, `reservation_id`) VALUES (?, ?)"),
        t.getSeatId(), t.getReservationId()).executeUpdate();
  }

  @Override
  public void update(SeatsReservation t) throws SQLException {
    if (t == null) {
      throw new SQLException("SeatsReservation rỗng");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("UPDATE `seats_reservation` SET `seat_id` = ?, `reservation_id` = ? WHERE `id` = ?"),
        t.getSeatId(), t.getReservationId()).executeUpdate();
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

  // TODO: Recheck this
  // public ArrayList<SeatsReservation> searchByKey(String key, StrauditoriumIding
  // word) throws SQLException {
  // ArrayList<SeatsReservation> seatsReservations = new ArrayList<>();
  // Statement statement = conn.createStatement();
  // String query = "SELECT * FROM `seats_reservation` WHERE " + key + " LIKE '%"
  // + word + "%';";
  // ResultSet rs = statement.executeQuery(query);
  // while (rs.next()) {
  // SeatsReservation seatsReservation = SeatsReservation.getFromResultSet(rs);
  // seatsReservations.add(seatsReservation);
  // }
  // return seatsReservations;
  // }
}
