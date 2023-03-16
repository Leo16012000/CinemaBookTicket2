package com.leo.dao;

import com.leo.models.Auditorium;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuditoriumDao extends Dao<Auditorium> {
  public static AuditoriumDao instance = null;

  @Override
  public ArrayList<Auditorium> getAll() throws SQLException {
    ArrayList<Auditorium> auditoriums = new ArrayList<>();
    ResultSet rs = conn.prepareStatement("SELECT * FROM `auditoriums`").executeQuery();
    while (rs.next()) {
      Auditorium auditorium = Auditorium.getFromResultSet(rs);
      auditoriums.add(auditorium);
    }
    return auditoriums;
  }

  @Override
  public Auditorium get(int id) throws SQLException {
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `auditoriums` WHERE id = ?"),
        id).executeQuery();
    if (rs.next()) {
      Auditorium auditorium = Auditorium.getFromResultSet(rs);
      return auditorium;
    }
    return null;
  }

  @Override
  public void save(Auditorium t) throws SQLException {
    if (t == null) {
      throw new SQLException("Empty Auditorium");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "INSERT INTO `auditoriums` (`auditorium_num`, `seats_row_num`, `seats_column_num`) VALUES (?, ?, ?)"),
        t.getAuditoriumNum(),
        t.getSeatsRowNum(),
        t.getSeatsColumnNum()).executeUpdate();
  }

  @Override
  public void update(Auditorium t) throws SQLException {
    if (t == null) {
      throw new SQLException("Rỗng");
    }

    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "UPDATE `auditoriums` SET `auditorium_num` = ?, `seats_row_num` = ?, `seats_column_num` = ? WHERE `id` = ?"),
        t.getAuditoriumNum(),
        t.getSeatsRowNum(),
        t.getSeatsColumnNum(),
        t.getId()).executeUpdate();
  }

  @Override
  public void delete(Auditorium t) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("DELETE FROM `auditoriums` WHERE `id` = ?");
    stmt.setInt(1, t.getId());
    stmt.executeUpdate();
  }

  @Override
  public void deleteById(int id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("DELETE FROM `auditoriums` WHERE `id` = ?");
    stmt.setInt(1, id);
    stmt.executeUpdate();
  }

  public ArrayList<Auditorium> searchByKey(String key, String word) throws SQLException {
    ArrayList<Auditorium> auditoriums = new ArrayList<>();
    ResultSet rs = PrepareStatements
        .setPreparedStatementParams(conn.prepareStatement("SELECT * FROM `auditoriums` WHERE ? LIKE '%?%'"), key, word)
        .executeQuery();
    while (rs.next()) {
      Auditorium auditorium = Auditorium.getFromResultSet(rs);
      auditoriums.add(auditorium);
    }
    return auditoriums;
  }

  public static AuditoriumDao getInstance() {
    if (instance == null) {
      synchronized (AuditoriumDao.class) {
        if (instance == null) {
          instance = new AuditoriumDao();
        }
      }
    }
    return instance;
  }
}
