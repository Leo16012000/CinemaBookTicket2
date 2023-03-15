package com.leo.dao;

import com.leo.models.User;
import com.leo.utils.PrepareStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends Dao<User> {
  @Override
  public ArrayList<User> getAll() throws SQLException {
    ArrayList<User> users = new ArrayList<>();
    ResultSet rs = conn.prepareStatement("SELECT * FROM `users`").executeQuery();
    while (rs.next()) {
      User user = User.getFromResultSet(rs);
      users.add(user);
    }
    return users;
  }

  @Override
  public User get(int id) throws SQLException {
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `users` WHERE id = ?"), id).executeQuery();
    if (rs.next()) {
      User user = User.getFromResultSet(rs);
      return user;
    }
    return null;
  }

  public User logIn(String username, String password) throws SQLException {
    System.out.println(username + " " + password);
    ResultSet rs = PrepareStatements
        .setPreparedStatementParams(
            conn.prepareStatement("SELECT * FROM `users` WHERE username = ? AND password = ?"), username, password)
        .executeQuery();
    if (rs.next()) {
      User user = User.getFromResultSet(rs);
      return user;
    }
    return null;
  }

  public User getByUsername(String username) throws SQLException {
    ResultSet rs = PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("SELECT * FROM `users` WHERE username = ?"), username).executeQuery();
    if (rs.next()) {
      User user = User.getFromResultSet(rs);
      return user;
    }
    return null;
  }

  @Override
  public void save(User t) throws SQLException {
    if (t == null) {
      throw new SQLException("Empty User");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement("INSERT INTO users (`name`, `username`, `password`, permission) VALUES (?, ?, ?, ?)"),
        t.getName(), t.getUsername(), t.getPassword(), t.getPermission().getCode()).executeUpdate();
  }

  @Override
  public void update(User t) throws SQLException {
    if (t == null) {
      throw new SQLException("User rỗng");
    }
    PrepareStatements.setPreparedStatementParams(
        conn.prepareStatement(
            "UPDATE users SET `name` = ?, `username` = ?, `password` = ?, permission = ? WHERE `id` = ?"),
        t.getName(), t.getUsername(), t.getPassword(), t.getPermission().getCode(), t.getId()).executeUpdate();
  }

  @Override
  public void delete(User t) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE `id` = ?");
    stmt.setInt(1, t.getId());
    stmt.executeUpdate();

  }

  @Override
  public void deleteById(int id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE `id` = ?");
    stmt.setInt(1, id);
    stmt.executeUpdate();
  }

  public ArrayList<User> searchByKey(String key, String word) throws SQLException {
    ArrayList<User> users = new ArrayList<>();
    ResultSet rs = PrepareStatements
        .setPreparedStatementParams(conn.prepareStatement("SELECT * FROM users WHERE ? LIKE '%?%'"), key, word)
        .executeQuery();
    while (rs.next()) {
      User user = User.getFromResultSet(rs);
      users.add(user);
    }
    return users;
  }

}
