package com.leo.dao;

import com.leo.models.Showtime;
import com.leo.utils.PrepareStatements;
import java.sql.SQLException;
import java.util.List;

public class ShowtimeDao extends Dao<Showtime> {
  public static ShowtimeDao instance = null;

  @Override
  public List<Showtime> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `showtimes`").executeQuery(),
            Showtime::getFromResultSet);
  }

  @Override
  public Showtime get(int id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `showtimes` WHERE id = ?"), id)
                .executeQuery(),
            Showtime::getFromResultSet);
  }

  @Override
  public void save(Showtime t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("Empty Showtime");
              }
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `showtimes` (`start_time`, `end_time`, `movie_id`) VALUES (?, ?, ?)"),
                  t.getStartTime(),
                  t.getEndTime(),
                  t.getMovieId())
                  .executeUpdate();
            });

  }

  @Override
  public void update(Showtime t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("Showtime rỗng");
              }
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "UPDATE `showtimes` SET `start_time` = ?, `end_time` = ?, `movie_id` = ? WHERE `id` = ?"),
                  t.getStartTime(),
                  t.getEndTime(),
                  t.getMovieId())
                  .executeUpdate();
            });
  }

  @Override
  public void delete(Showtime t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> deleteById(t.getId()));
  }

  @Override
  public void deleteById(int id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("DELETE FROM `showtimes` WHERE `id` = ?"), id).executeUpdate());
  }

  public List<Showtime> searchByKey(String key, String word) throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `showtimes` WHERE ? LIKE '%?%'"), key, word)
                .executeQuery(),
            Showtime::getFromResultSet);
  }

  public static ShowtimeDao getInstance() {
    if (instance == null) {
      synchronized (ShowtimeDao.class) {
        if (instance == null) {
          instance = new ShowtimeDao();
        }
      }
    }
    return instance;
  }
}
