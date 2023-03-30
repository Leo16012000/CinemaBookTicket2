package com.leo.dao;

import java.sql.SQLException;
import java.util.List;

import com.leo.models.SeatsReservation;
import com.leo.utils.PrepareStatements;

public class SeatsReservationDao extends Dao<SeatsReservation> {
  private static SeatsReservationDao instance;

  @Override
  public List<SeatsReservation> getAll() throws SQLException {
    throw new UnsupportedOperationException("Do not supported this action");
  }

  @Override
  public SeatsReservation get(Integer id) throws SQLException {
    return transactionManager.getTransaction().query(conn -> PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM seats_reservation sr WHERE sr.id = ?"), id).executeQuery(),
        SeatsReservation::getFromResultSet);
  }

  @Override
  public Integer save(SeatsReservation t) throws SQLException {
    return transactionManager.getTransaction().query(conn -> PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "INSERT INTO seats_reservation sr (seat_id, user_id, showtime_id, status, expired_at) VALUES (?, ?, ?, ?, ?, ?)"),
        t.getSeatId(), t.getUserId(), t.getShowtimeId(), t.getStatus(), t.getExpiredAt()).executeQuery(),
        rs -> rs.getInt(1));
  }

  @Override
  public void update(SeatsReservation t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "UPDATE seats_reservation sr SET (user_id, status, expired_at) VALUES (?, ?, ?) WHERE sr.id = ?"),
                  t.getUserId(),
                  t.getStatus(),
                  t.getExpiredAt())
                  .executeUpdate();
            });
  }

  @Override
  public void delete(SeatsReservation t) throws SQLException {
    transactionManager.getTransaction().run(conn -> deleteById(t.getId()));
  }

  @Override
  public void deleteById(Integer id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement("DELETE FROM `seats_reservation` WHERE `id` = ?"), id)
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

  public List<SeatsReservation> getAllByUserIdAndShowtimeId(Integer userId, Integer showtimeId) throws SQLException {
    return transactionManager.getTransaction().queryList(conn -> PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM seats_reservation sr WHERE sr.user_id = ? AND sr.showtime_id"), userId,
        showtimeId).executeQuery(),
        SeatsReservation::getFromResultSet);
  }

  public List<SeatsReservation> getAllByShowtimeId(int showtimeId)
      throws SQLException {
    return transactionManager.getTransaction().queryList(conn -> PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "SELECT * FROM seats_reservation sr WHERE sr.showtime_id = ?"),
        showtimeId)
        .executeQuery(), SeatsReservation::getFromResultSet);
  }

  public static SeatsReservationDao getInstance() {
    if (instance == null) {
      synchronized (SeatsReservationDao.class) {
        if (instance == null) {
          instance = new SeatsReservationDao();
        }
      }
    }
    return instance;
  }

  public SeatsReservation getBySeatIdAndShowtimeId(int seatId, int showtimeId) throws SQLException {
    return transactionManager.getTransaction().query(conn -> PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "SELECT * FROM seats_reservation sr WHERE sr.showtime_id = ? AND sr.seat_id = ?"),
        showtimeId, seatId)
        .executeQuery(), SeatsReservation::getFromResultSet);
  }
}