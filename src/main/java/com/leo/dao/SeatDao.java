package com.leo.dao;

import com.leo.models.Seat;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SeatDao extends Dao<Integer, Seat> {
  private static SeatDao instance = null;

  @Override
  public List<Seat> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `seats`").executeQuery(),
            Seat::getFromResultSet);
  }

  @Override
  public Seat get(Integer id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `seats` WHERE id = ?"), id)
                .executeQuery(),
            Seat::getFromResultSet);
  }

  @Override
  public Integer save(Seat t) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> {
              if (t == null) {
                throw new SQLException("Empty Seat");
              }
              PreparedStatement stmt = PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `seats` (`auditorium_id`, `seat_num`, `seat_row`) VALUES (?, ?, ?)",
                      Statement.RETURN_GENERATED_KEYS),
                  t.getAuditoriumId(),
                  t.getSeatColumn(),
                  t.getSeatRow());
              stmt.executeUpdate();
              return stmt.getGeneratedKeys();
            }, rs -> rs.getInt(1));
  }

  @Override
  public void update(Seat t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("Seat rỗng");
              }
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "UPDATE `seats` SET `auditorium_id` = ?, `seat_num` = ?, `seat_row` = ? WHERE `id` = ?"),
                  t.getAuditoriumId(),
                  t.getSeatColumn(),
                  t.getSeatRow())
                  .executeUpdate();
            });
  }

  @Override
  public void delete(Seat t) throws SQLException {
    transactionManager.getTransaction().run(conn -> deleteById(t.getId()));
  }

  @Override
  public void deleteById(Integer id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement("DELETE FROM `seats` WHERE `id` = ?"), id)
                  .executeUpdate();
            });
  }

  public List<Seat> searchByKey(String key, String word) throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `seats` WHERE ? LIKE '%?%'"),
                key,
                word)
                .executeQuery(),
            Seat::getFromResultSet);
  }

  public static SeatDao getInstance() {
    if (instance == null) {
      synchronized (SeatDao.class) {
        if (instance == null) {
          instance = new SeatDao();
        }
      }
    }
    return instance;
  }

  public List<Seat> getByAuditoriumIdAndShowtimeId(int auditoriumId, int showtimeId)
      throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement(
                    "SELECT * FROM `seats` s\n"
                        + "INNER JOIN `seats_reservation` sr\n"
                        + "INNER JOIN `reservations` r\n"
                        + "INNER JOIN `showtimes` sh\n"
                        + "ON sr.seat_id = s.id\n"
                        + "ON r.id = sr.reservation_id\n"
                        + "ON r.showtime_id = sh.id\n"
                        + "WHERE auditorium_id = ? AND showtime_id = ?"),
                auditoriumId,
                showtimeId)
                .executeQuery(),
            Seat::getFromResultSet);
  }
}
