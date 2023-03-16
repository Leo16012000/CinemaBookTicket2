package com.leo.dao;

import com.leo.models.Reservation;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDao extends Dao<Reservation> {

  @Override
  public ArrayList<Reservation> getAll() throws SQLException {
    ArrayList<Reservation> reservations = new ArrayList<>();
    ResultSet rs = conn.prepareStatement("SELECT * FROM `reservations`").executeQuery();
    while (rs.next()) {
      Reservation reservation = Reservation.getFromResultSet(rs);
      reservations.add(reservation);
    }
    return reservations;
  }

  @Override
  public Reservation get(int id) throws SQLException {
    ResultSet rs = PrepareStatements
        .setPreparedStatementParams(conn.prepareStatement("SELECT * FROM `reservations` WHERE id = ?"), id)
        .executeQuery();
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
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("INSERT INTO `reservations` (`userId`, `showtime_id`) VALUES (?, ?)"),
        t.getUserId(), t.getShowtimeId()).executeUpdate();
  }

  @Override
  public void update(Reservation t) throws SQLException {
    if (t == null) {
      throw new SQLException("Reservation rỗng");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("UPDATE `reservations` SET `user_id` = ?, `showtime_id` = ? WHERE `id` = ?"),
        t.getUserId(), 2, t.getShowtimeId()).executeUpdate();

  }

  @Override
  public void delete(Reservation t) throws SQLException {
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("DELETE FROM `reservations` WHERE `id` = ?"), t.getId()).executeUpdate();
  }

  @Override
  public void deleteById(int id) throws SQLException {
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("DELETE FROM `reservations` WHERE `id` = ?"),
        id).executeUpdate();
  }

  public ArrayList<Reservation> searchByKey(String key, String word) throws SQLException {
    ArrayList<Reservation> reservations = new ArrayList<>();
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `reservations` WHERE ? LIKE '%?%'"), key, word).executeQuery();
    while (rs.next()) {
      Reservation reservation = Reservation.getFromResultSet(rs);
      reservations.add(reservation);
    }
    return reservations;
  }
}
