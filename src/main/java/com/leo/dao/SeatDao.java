package com.leo.dao;

import com.leo.models.Seat;
import com.leo.utils.PrepareStatements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatDao extends Dao<Seat> {
  private static SeatDao instance = null;

  @Override
  public ArrayList<Seat> getAll() throws SQLException {
    ArrayList<Seat> seats = new ArrayList<>();
    ResultSet rs = conn.prepareStatement("SELECT * FROM `reservations`").executeQuery();
    while (rs.next()) {
      Seat seat = Seat.getFromResultSet(rs);
      seats.add(seat);
    }
    return seats;
  }

  @Override
  public Seat get(int id) throws SQLException {
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `seats` WHERE id = ?"), id).executeQuery();
    if (rs.next()) {
      Seat seat = Seat.getFromResultSet(rs);
      return seat;
    }
    return null;
  }

  @Override
  public void save(Seat t) throws SQLException {
    if (t == null) {
      throw new SQLException("Empty Seat");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("INSERT INTO `seats` (`auditorium_id`, `seat_num`, `seat_row`) VALUES (?, ?, ?)"),
        t.getAuditoriumId(), t.getSeatColumn(), t.getSeatRow()).executeUpdate();
  }

  @Override
  public void update(Seat t) throws SQLException {
    if (t == null) {
      throw new SQLException("Seat rỗng");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("UPDATE `seats` SET `auditorium_id` = ?, `seat_num` = ?, `seat_row` = ? WHERE `id` = ?"),
        t.getAuditoriumId(), t.getSeatColumn(), t.getSeatRow()).executeUpdate();
  }

  @Override
  public void delete(Seat t) throws SQLException {
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("DELETE FROM `seats` WHERE `id` = ?"), t.getId()).executeUpdate();
  }

  @Override
  public void deleteById(int id) throws SQLException {
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("DELETE FROM `seats` WHERE `id` = ?"), id).executeUpdate();
  }

  public ArrayList<Seat> searchByKey(String key, String word) throws SQLException {
    ArrayList<Seat> seats = new ArrayList<>();
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `seats` WHERE ? LIKE '%?%'"),
        key, word).executeQuery();
    ;
    while (rs.next()) {
      Seat seat = Seat.getFromResultSet(rs);
      seats.add(seat);
    }
    return seats;
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

  public ArrayList<Seat> getByAuditoriumIdAndShowtimeId(int auditoriumId, int showtimeId) throws SQLException {
    ArrayList<Seat> seats = new ArrayList<>();
    ResultSet rs = PrepareStatements.setPreparedStatementParams(conn.prepareStatement(
        "SELECT * FROM `seats` s\n" +
            "INNER JOIN `seats_reservation` sr\n" +
            "INNER JOIN `reservations` r\n" +
            "INNER JOIN `showtimes` sh\n" +
            "ON sr.seat_id = s.id\n" +
            "ON r.id = sr.reservation_id\n" +
            "ON r.showtime_id = sh.id\n" +
            "WHERE auditorium_id = ? AND showtime_id = ?"),
        auditoriumId, showtimeId).executeQuery();
    while (rs.next()) {
      Seat seat = Seat.getFromResultSet(rs);
      seats.add(seat);
    }
    return null;
  }
}
