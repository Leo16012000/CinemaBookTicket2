package com.leo.dao;

import com.leo.models.Showtime;
import com.leo.utils.PrepareStatements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowtimeDao extends Dao<Showtime> {
  public static ShowtimeDao instance = null;

  @Override
  public ArrayList<Showtime> getAll() throws SQLException {
    ArrayList<Showtime> showtimes = new ArrayList<>();
    ResultSet rs = conn.prepareStatement("SELECT * FROM `showtimes`").executeQuery();
    while (rs.next()) {
      Showtime seat = Showtime.getFromResultSet(rs);
      showtimes.add(seat);
    }
    return showtimes;
  }

  @Override
  public Showtime get(int id) throws SQLException {
    ResultSet rs = PrepareStatements
        .setPreparedStatementParams(conn.prepareStatement("SELECT * FROM `showtimes` WHERE id = ?"), id).executeQuery();
    if (rs.next()) {
      Showtime seat = Showtime.getFromResultSet(rs);
      return seat;
    }
    return null;
  }

  @Override
  public void save(Showtime t) throws SQLException {
    if (t == null) {
      throw new SQLException("Empty Showtime");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("INSERT INTO `showtimes` (`start_time`, `end_time`, `movie_id`) VALUES (?, ?, ?)"),
        t.getStartTime(), t.getEndTime(), t.getMovieId()).executeUpdate();
  }

  @Override
  public void update(Showtime t) throws SQLException {
    if (t == null) {
      throw new SQLException("Showtime rỗng");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("UPDATE `showtimes` SET `start_time` = ?, `end_time` = ?, `movie_id` = ? WHERE `id` = ?"),
        t.getStartTime(), t.getEndTime(), t.getMovieId()).executeUpdate();
  }

  @Override
  public void delete(Showtime t) throws SQLException {
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("DELETE FROM `showtimes` WHERE `id` = ?"), t.getId()).executeUpdate();
  }

  @Override
  public void deleteById(int id) throws SQLException {
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("DELETE FROM `showtimes` WHERE `id` = ?"), id).executeUpdate();
  }

  public ArrayList<Showtime> searchByKey(String key, String word) throws SQLException {
    ArrayList<Showtime> showtimes = new ArrayList<>();
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `showtimes` WHERE ? LIKE '%?%'"), key, word).executeQuery();
    while (rs.next()) {
      Showtime seat = Showtime.getFromResultSet(rs);
      showtimes.add(seat);
    }
    return showtimes;
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
