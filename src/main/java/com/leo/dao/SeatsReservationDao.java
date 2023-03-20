package com.leo.dao;

import com.leo.models.SeatsReservation;
import com.leo.utils.PrepareStatements;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SeatsReservationDao extends Dao<Integer, SeatsReservation> {

  @Override
  public List<SeatsReservation> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `seats_reservation`").executeQuery(),
            SeatsReservation::getFromResultSet);
  }

  @Override
  public SeatsReservation get(Integer id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `seats_reservation` WHERE id = ?"), id)
                .executeQuery(),
            SeatsReservation::getFromResultSet);
  }

  @Override
  public Integer save(SeatsReservation t) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> {
              if (t == null) {
                throw new SQLException("Empty SeatsReservation");
              }
              PreparedStatement stmt = PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `seats_reservation` (`seat_id`, `reservation_id`) VALUES (?, ?)",
                      Statement.RETURN_GENERATED_KEYS),
                  t.getSeatId(),
                  t.getReservationId());
              stmt.executeUpdate();
              return stmt.getGeneratedKeys();
            }, rs -> rs.getInt(0));

  }

  @Override
  public void update(SeatsReservation t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("SeatsReservation rỗng");
              }
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "UPDATE `seats_reservation` SET `seat_id` = ?, `reservation_id` = ? WHERE `id` = ?"),
                  t.getSeatId(),
                  t.getReservationId())
                  .executeUpdate();
            });

  }

  @Override
  public void delete(SeatsReservation t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> deleteById(t.getId()));
  }

  @Override
  public void deleteById(Integer id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PreparedStatement stmt = conn.prepareStatement("DELETE FROM `seats_reservation` WHERE `id` = ?");
              stmt.setInt(1, id);
              stmt.executeUpdate();
            });
  }

  public List<SeatsReservation> searchByKey(String key, String word) throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `seats_reservation` WHERE ? LIKE '%?%'"),
                key,
                word)
                .executeQuery(),
            SeatsReservation::getFromResultSet);
  }
}
