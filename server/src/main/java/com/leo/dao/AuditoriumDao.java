package com.leo.dao;

import com.leo.models.Auditorium;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AuditoriumDao extends Dao<Auditorium> {
  private static AuditoriumDao instance;

  @Override
  public List<Auditorium> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `auditoriums`").executeQuery(),
            Auditorium::getFromResultSet);
  }

  @Override
  public Auditorium get(Integer id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `auditoriums` WHERE id = ?"), id)
                .executeQuery(),
            Auditorium::getFromResultSet);
  }

  public Integer save(Auditorium a) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> {
              if (a == null) {
                throw new SQLException("Empty Auditorium");
              }
              PreparedStatement stmt = PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `auditoriums` (`auditorium_num`, `seats_row_num`, `seats_column_num`) VALUES (?, ?, ?)",
                      Statement.RETURN_GENERATED_KEYS),
                  a.getAuditoriumNum(),
                  a.getSeatsRowNum(),
                  a.getSeatsColumnNum());
              stmt.executeUpdate();
              return stmt.getGeneratedKeys();
            }, rs -> rs.getInt(1));
  }

  @Override
  public void update(Auditorium t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("Rỗng");
              }

              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "UPDATE `auditoriums` SET `auditorium_num` = ?, `seats_row_num` = ?, `seats_column_num` = ? WHERE `id` = ?"),
                  t.getAuditoriumNum(),
                  t.getSeatsRowNum(),
                  t.getSeatsColumnNum(),
                  t.getId())
                  .executeUpdate();
            });
  }

  @Override
  public void delete(Auditorium t) throws SQLException {
    transactionManager.getTransaction().run(conn -> deleteById(t.getId()));
  }

  @Override
  public void deleteById(Integer id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PreparedStatement stmt = conn.prepareStatement("DELETE FROM `auditoriums` WHERE `id` = ?");
              stmt.setInt(1, id);
              stmt.executeUpdate();
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

  public List<Auditorium> searchByKey(String key, String word) throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `auditoriums` WHERE ? LIKE '%?%'"),
                key,
                word)
                .executeQuery(),
            Auditorium::getFromResultSet);
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
