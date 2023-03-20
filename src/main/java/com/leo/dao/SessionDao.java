package com.leo.dao;

import com.leo.models.Session;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class SessionDao extends Dao<Integer, Session> {
  private static SessionDao instance;

  @Override
  public List<Session> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `session`  ORDER BY `session`.`start_time` DESC")
                .executeQuery(),
            Session::getFromResultSet);
  }

  @Override
  public Session get(Integer id) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements
                .setPreparedStatementParams(conn.prepareStatement("SELECT * FROM `session` WHERE `id` = ?"), id)
                .executeQuery(),
            Session::getFromResultSet);
  }

  public List<Session> getSession(int id) throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement(
                    "SELECT * FROM `session` WHERE `user_id` = ? ORDER BY `session`.`start_time` DESC"),
                id).executeQuery(),
            Session::getFromResultSet);
  }

  @Override
  public Integer save(Session t) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> {
              if (t == null) {
                throw new SQLException("Shipment rỗng");
              }
              PreparedStatement stmt = PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO `session` (`user_id`, `start_time`, `end_time` , `message`) VALUES (?, ?, ?, ?)",
                      Statement.RETURN_GENERATED_KEYS),
                  t.getUserId(),
                  t.getStartTime(),
                  t.getEndTime(),
                  t.getMessage());
              stmt.executeUpdate();
              return stmt.getGeneratedKeys();
            }, rs -> rs.getInt(0));

  }

  @Override
  public void update(Session t) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              if (t == null) {
                throw new SQLException("Shipment rỗng");
              }
              PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "UPDATE `session` SET `start_time` = ?, `end_time` = ?, `message` = ? WHERE `session`.`id` = ?"),
                  t.getStartTime(),
                  t.getEndTime(),
                  t.getMessage(),
                  t.getId())
                  .executeUpdate();
            });

  }

  @Override
  public void delete(Session t) throws SQLException {
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
            conn -> {
              PreparedStatement stmt = conn.prepareStatement("DELETE FROM `session` WHERE `id` = ?");
              stmt.setInt(1, id);
              stmt.executeUpdate();
            });
  }

  public Session getLast(int userId) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement(
                    "SELECT * FROM `session` WHERE `user_id` = ? ORDER BY `id` DESC LIMIT 1"),
                userId).executeQuery(),
            Session::getFromResultSet);
  }

  public List<Session> getAll(Timestamp start, Timestamp end) throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement(
                    "SELECT * FROM `session` WHERE `message` = ? AND DATE(start_time) >= DATE(?) AND DATE(start_time) <= DATE(?) ORDER BY `session`.`start_time` DESC"),
                "logout",
                start,
                end)
                .executeQuery(),
            Session::getFromResultSet);
  }

  public static SessionDao getInstance() {
    if (instance == null) {
      synchronized (SessionDao.class) {
        if (instance == null) {
          instance = new SessionDao();
        }
      }
    }
    return instance;
  }
}
