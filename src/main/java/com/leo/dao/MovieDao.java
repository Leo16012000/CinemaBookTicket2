package com.leo.dao;

import com.leo.models.Movie;
import com.leo.models.Showtime;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieDao extends Dao<Movie> {
  private static MovieDao instance;

  @Override
  public List<Movie> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `movies`").executeQuery(),
            Movie::getFromResultSet);
  }

  @Override
  public Movie get(Integer id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `movies` WHERE id = ?"), id)
                .executeQuery(),
            Movie::getFromResultSet);
  }

  @Override
  public Integer save(Movie t) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> {
              if (t == null) {
                throw new SQLException("Empty Movie");
              }
              PreparedStatement stmt = PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `movies` (`price`, `duration_time`, `title`, `country`) VALUES (?, ?, ?, ?)",
                      Statement.RETURN_GENERATED_KEYS),
                  t.getPrice(),
                  t.getDurationTime(),
                  t.getTitle(),
                  t.getCountry());
              stmt.executeUpdate();
              return stmt.getGeneratedKeys();
            }, rs -> rs.getInt(1));
  }

  @Override
  public void update(Movie t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("Movie rỗng");
              }
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "UPDATE `movies` SET `price` = ?, `duration_time` = ?, `title` = ?, `country` = ? WHERE `id` = ?"),
                  t.getPrice(),
                  t.getDurationTime(),
                  t.getTitle(),
                  t.getCountry(),
                  t.getId())
                  .executeUpdate();
            });
  }

  @Override
  public void delete(Movie t) throws SQLException {
    transactionManager.getTransaction().run(conn -> deleteById(t.getId()));
  }

  @Override
  public void deleteById(Integer id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement("DELETE FROM `movies` WHERE `id` = ?"), id)
                  .executeUpdate();
            });
  }

  public List<Movie> searchByKey(String key, String word) throws SQLException {
    final Set<String> columnNames = Arrays.asList("id", "title", "duration_time", "country", "price").stream()
        .collect(Collectors.toSet());
    if (!columnNames.contains(key)) {
      throw new IllegalArgumentException();
    }
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `movies` WHERE " + key + " LIKE ?"),
                "%" + word.trim() + "%")
                .executeQuery(),
            Movie::getFromResultSet);
  }

  public List<Showtime> getMovieShowtimes(int movieId) throws SQLException {
    return transactionManager.getTransaction().queryList(
        conn -> PrepareStatements.setPreparedStatementParams(
            conn.prepareStatement("SELECT * FROM `showtimes` s WHERE s.movie_id = ?"), movieId).executeQuery(),
        Showtime::getFromResultSet);
  }

  public static MovieDao getInstance() {
    if (instance == null) {
      synchronized (MovieDao.class) {
        if (instance == null) {
          instance = new MovieDao();
        }
      }
    }
    return instance;
  }
}
