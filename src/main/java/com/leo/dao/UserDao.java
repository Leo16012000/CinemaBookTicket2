package com.leo.dao;

import com.leo.models.Showtime;
import com.leo.models.User;
import com.leo.utils.PrepareStatements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao<User> {
  @Override
  public List<User> getAll() throws SQLException {
    return transactionManager
            .getTransaction()
            .queryList(
                    conn -> conn.prepareStatement("SELECT * FROM `users`").executeQuery(),
                    User::getFromResultSet);
  }

  @Override
  public User get(int id) throws SQLException {
    return transactionManager
            .getTransaction()
            .query(
                    conn -> PrepareStatements.setPreparedStatementParams(
                                    conn.prepareStatement("SELECT * FROM `users` WHERE id = ?"), id)
                            .executeQuery(),
                    User::getFromResultSet);
  }

  public User getByUsername(String username) throws SQLException {
    return transactionManager
            .getTransaction()
            .query(
                    conn ->  PrepareStatements.setPreparedStatementParams(
                                    conn.prepareStatement("SELECT * FROM `users` WHERE username = ?"), username)
                            .executeQuery(),
                    User::getFromResultSet);
  }

  @Override
  public void save(User t) throws SQLException {
    transactionManager
            .getTransaction()
            .run(
                    conn -> {
                      if (t == null) {
                        throw new SQLException("Empty User");
                      }
                      PrepareStatements.setPreparedStatementParams(
                                      conn.prepareStatement(
                                              "INSERT INTO users (`name`, `username`, `password`, permission) VALUES (?, ?, ?, ?)"),
                                      t.getName(),
                                      t.getUsername(),
                                      t.getPassword(),
                                      t.getPermission().getCode())
                              .executeUpdate();
                    });
  }

  @Override
  public void update(User t) throws SQLException {
    transactionManager
            .getTransaction()
            .run(
                    conn -> {
                      if (t == null) {
                        throw new SQLException("User rỗng");
                      }
                      PrepareStatements.setPreparedStatementParams(
                                      conn.prepareStatement(
                                              "UPDATE users SET `name` = ?, `username` = ?, `password` = ?, permission = ? WHERE `id` = ?"),
                                      t.getName(),
                                      t.getUsername(),
                                      t.getPassword(),
                                      t.getPermission().getCode(),
                                      t.getId())
                              .executeUpdate();
                    });
  }

  @Override
  public void delete(User t) throws SQLException {
    transactionManager
            .getTransaction()
            .run(
                    conn -> deleteById(t.getId()));

  }

  @Override
  public void deleteById(int id) throws SQLException {
    transactionManager
            .getTransaction()
            .run(
                    conn -> {
                      PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE `id` = ?");
                      stmt.setInt(1, id);
                      stmt.executeUpdate();
                    });
  }

}
