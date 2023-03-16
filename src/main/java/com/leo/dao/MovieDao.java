package com.leo.dao;

import com.leo.models.Movie;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDao extends Dao<Movie> {
  private static MovieDao instance;

  @Override
  public ArrayList<Movie> getAll() throws SQLException {
    ArrayList<Movie> Movies = new ArrayList<>();
    ResultSet rs = conn.prepareStatement("SELECT * FROM `movies`").executeQuery();
    while (rs.next()) {
      Movie movie = Movie.getFromResultSet(rs);
      Movies.add(movie);
    }
    return Movies;
  }

  @Override
  public Movie get(int id) throws SQLException {
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `movies` WHERE id = ?"), id).executeQuery();
    if (rs.next()) {
      Movie movie = Movie.getFromResultSet(rs);
      return movie;
    }
    return null;
  }

  @Override
  public void save(Movie t) throws SQLException {
    if (t == null) {
      throw new SQLException("Empty Movie");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "INSERT INTO `movies` (`price`, `duration_time`, `title`, `country`) VALUES (?, ?, ?, ?)"),
        t.getPrice(), t.getDurationTime(), t.getTitle(), t.getCountry()).executeUpdate();
  }

  @Override
  public void update(Movie t) throws SQLException {
    if (t == null) {
      throw new SQLException("Movie rỗng");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "UPDATE `movies` SET `price` = ?, `duration_time` = ?, `title` = ?, `country` = ? WHERE `id` = ?"),
        t.getPrice(), t.getDurationTime(), t.getTitle(), t.getCountry(), t.getId()).executeUpdate();
  }

  @Override
  public void delete(Movie t) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("DELETE FROM `movies` WHERE `id` = ?");
    stmt.setInt(1, t.getId());
    stmt.executeUpdate();
  }

  @Override
  public void deleteById(int id) throws SQLException {
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("DELETE FROM `movies` WHERE `id` = ?"), id).executeUpdate();
  }

  public ArrayList<Movie> searchByKey(String key, String word) throws SQLException {
    ArrayList<Movie> movies = new ArrayList<>();
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `movies` WHERE ? LIKE '%?%'"),
        key, word).executeQuery();
    while (rs.next()) {
      Movie movie = Movie.getFromResultSet(rs);
      movies.add(movie);
    }
    return movies;
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
