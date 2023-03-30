package com.leo.dao;

import com.leo.utils.PrepareStatements;
import com.leo.models.Showtime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ShowtimeDao extends Dao<Showtime> {
  public static ShowtimeDao instance = null;

  public Showtime getFromResultSet(ResultSet rs) throws SQLException {
    Showtime showtime = Showtime.getFromResultSet(rs);
    showtime.setAuditorium(AuditoriumDao.getInstance().get(showtime.getAuditoriumId()));
    showtime.setMovie(MovieDao.getInstance().get(showtime.getMovieId()));
    return showtime;
  }

  @Override
  public List<Showtime> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `showtimes`").executeQuery(),
            this::getFromResultSet);
  }

  @Override
  public Showtime get(Integer id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `showtimes` WHERE id = ?"), id)
                .executeQuery(),
            this::getFromResultSet);
  }

  @Override
  public Integer save(Showtime t) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> {
              if (t == null) {
                throw new SQLException("Empty Showtime");
              }
              PreparedStatement stmt = PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `showtimes` (`start_time`, `end_time`, `movie_id`, `auditorium_id`) VALUES (?, ?, ?, ?)",
                      Statement.RETURN_GENERATED_KEYS),
                  t.getStartTime(),
                  t.getEndTime(),
                  t.getMovieId(), t.getAuditoriumId());
              stmt.executeUpdate();
              return stmt.getGeneratedKeys();
            }, rs -> rs.getInt(1));
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
                      "UPDATE `showtimes` SET `start_time` = ?, `end_time` = ?, `movie_id` = ?, auditorium_id = ? WHERE `id` = ?"),
                  t.getStartTime(),
                  t.getEndTime(),
                  t.getMovieId(),
                  t.getAuditoriumId(), t.getId())
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
  public void deleteById(Integer id) throws SQLException {
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
                conn.prepareStatement(
                    "SELECT * FROM `showtimes` inner join movies on showtimes.movie_id = movies.id inner join auditoriums on showtimes.auditorium_id = auditoriums.id WHERE ? LIKE '%?%'"),
                key, word)
                .executeQuery(),
            this::getFromResultSet);
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
