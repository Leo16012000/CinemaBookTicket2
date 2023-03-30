package com.leo.dao;

import com.leo.utils.PrepareStatements;
import com.leo.models.Reservation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
  public Reservation get(Integer id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `reservations` WHERE id = ?"), id)
                .executeQuery(),
            Reservation::getFromResultSet);
  }

  @Override
  public Integer save(Reservation t) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> {
              if (t == null) {
                throw new SQLException("Empty Reservation");
              }
              PreparedStatement stmt = PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `reservations` (`userId`, `showtime_id`) VALUES (?, ?)",
                      Statement.RETURN_GENERATED_KEYS),
                  t.getUserId(),
                  t.getShowtimeId());
              stmt.executeUpdate();
              return stmt.getGeneratedKeys();
            }, rs -> rs.getInt(1));
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
  public void deleteById(Integer id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement("DELETE FROM `reservations` WHERE `id` = ?"), id)
                  .executeUpdate();
            });
  }

    @Override
    public void deleteByIds(List<Integer> ids) throws SQLException {
        transactionManager
                .getTransaction()
                .run(
                        conn -> {
                            for (Integer id : ids) {
                                deleteById(id);
                            }
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
