package com.leo.dao;

import com.leo.models.Session;
import com.leo.utils.PrepareStatements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SessionDao extends Dao<Session> {

  UserDao userDao = new UserDao();

  @Override
  public ArrayList<Session> getAll() throws SQLException {
    ArrayList<Session> sessions = new ArrayList<>();
    ResultSet rs = conn.prepareStatement("SELECT * FROM `session`  ORDER BY `session`.`start_time` DESC")
        .executeQuery();
    while (rs.next()) {
      Session session = Session.getFromResultSet(rs);
      session.setUser(userDao.get(session.getUserId()));
      sessions.add(session);
    }
    return sessions;
  }

  @Override
  public Session get(int id) throws SQLException {
    ResultSet rs = conn.prepareStatement("SELECT * FROM `session` WHERE `id` = ?").executeQuery();
    if (rs.next()) {
      Session session = Session.getFromResultSet(rs);
      session.setUser(userDao.get(session.getUserId()));
      return session;
    }
    return null;
  }

  public ArrayList<Session> getSession(int id) throws SQLException {
    ArrayList<Session> sessions = new ArrayList<>();
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `session` WHERE `user_id` = ? ORDER BY `session`.`start_time` DESC"), id)
        .executeQuery();
    while (rs.next()) {
      Session session = Session.getFromResultSet(rs);
      session.setUser(userDao.get(session.getUserId()));
      sessions.add(session);
    }
    return sessions;
  }

  @Override
  public void save(Session t) throws SQLException {
    if (t == null) {
      throw new SQLException("Shipment rỗng");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "INSERT INTO `session` (`user_id`, `start_time`, `end_time` , `message`) VALUES (?, ?, ?, ?)"),
        t.getUserId(), t.getStartTime(), t.getEndTime(), t.getMessage()).executeUpdate();
  }

  @Override
  public void update(Session t) throws SQLException {
    if (t == null) {
      throw new SQLException("Shipment rỗng");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "UPDATE `session` SET `start_time` = ?, `end_time` = ?, `message` = ? WHERE `session`.`id` = ?"),
        t.getStartTime(), t.getEndTime(), t.getMessage(), t.getId()).executeUpdate();
  }

  @Override
  public void delete(Session t) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void deleteById(int id) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public Session getLast(int userId) throws SQLException {
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `session` WHERE `user_id` = ? ORDER BY `id` DESC LIMIT 1"), userId)
        .executeQuery();
    if (rs.next()) {
      Session session = Session.getFromResultSet(rs);
      session.setUser(userDao.get(session.getUserId()));
      return session;
    }
    return null;
  }

  public ArrayList<Session> getAll(Timestamp start, Timestamp end) throws SQLException {
    ArrayList<Session> sessions = new ArrayList<>();
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "SELECT * FROM `session` WHERE `message` = ? AND DATE(start_time) >= DATE(?) AND DATE(start_time) <= DATE(?) ORDER BY `session`.`start_time` DESC"),
        "logout", start, end).executeQuery();
    while (rs.next()) {
      Session session = Session.getFromResultSet(rs);
      session.setUser(userDao.get(session.getUserId()));
      sessions.add(session);
    }
    return sessions;
  }

}
