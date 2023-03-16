package com.leo.dao;

import com.leo.models.Reservation;
import com.leo.utils.PrepareStatements;
import java.sql.SQLException;
import java.util.List;

public class ReservationDao extends Dao<Reservation> {
  @Override
  public List<Reservation> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `reservations`").executeQuery(),
            Reservation::getFromResultSet);
  }

  @Override
  public Reservation get(int id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `reservations` WHERE id = ?"), id)
                .executeQuery(),
            Reservation::getFromResultSet);
  }

  @Override
  public void save(Reservation t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("Empty Reservation");
              }
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `reservations` (`userId`, `showtime_id`) VALUES (?, ?)"),
                  t.getUserId(),
                  t.getShowtimeId())
                  .executeUpdate();
            });
  }

  @Override
  public void update(Reservation t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("Reservation rỗng");
              }
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "UPDATE `reservations` SET `user_id` = ?, `showtime_id` = ? WHERE `id` = ?"),
                  t.getUserId(),
                  2,
                  t.getShowtimeId())
                  .executeUpdate();
            });
  }

  @Override
  public void delete(Reservation t) throws SQLException {
    transactionManager.getTransaction().run(conn -> deleteById(t.getId()));
  }

  @Override
  public void deleteById(int id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement("DELETE FROM `reservations` WHERE `id` = ?"), id)
                  .executeUpdate();
            });
  }

  public List<Reservation> searchByKey(String key, String word) throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `reservations` WHERE ? LIKE '%?%'"),
                key,
                word)
                .executeQuery(),
            Reservation::getFromResultSet);
  }
}
