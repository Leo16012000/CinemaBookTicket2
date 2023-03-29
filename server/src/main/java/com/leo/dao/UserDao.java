package com.leo.dao;

import com.leo.models.User;
import com.leo.utils.PrepareStatements;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDao extends Dao<User> {
  private static UserDao instance;

  @Override
  public List<User> getAll() throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(
            conn -> conn.prepareStatement("SELECT * FROM `users`").executeQuery(),
            User::getFromResultSet);
  }

  @Override
  public User get(Integer id) throws SQLException {
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
            conn -> PrepareStatements.setPreparedStatementParams(
                conn.prepareStatement("SELECT * FROM `users` WHERE username = ?"), username)
                .executeQuery(),
            User::getFromResultSet);
  }

  @Override
  public Integer save(User t) throws SQLException {
    return transactionManager
        .getTransaction()
        .query(
            conn -> {
              if (t == null) {
                throw new SQLException("Empty User");
              }
              PreparedStatement stmt = PrepareStatements.setPreparedStatementParams(
                  conn.prepareStatement(
                      "INSERT INTO users (`name`, `username`, `password`, permission) VALUES (?, ?, ?, ?)",
                      Statement.RETURN_GENERATED_KEYS),
                  t.getName(),
                  t.getUsername(),
                  t.getPassword(),
                  t.getPermission().getCode());
              stmt.executeUpdate();
              return stmt.getGeneratedKeys();
            }, rs -> rs.getInt(1));
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
  public void deleteById(Integer id) throws SQLException {
    transactionManager
        .getTransaction()
        .run(
            conn -> {
              PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE `id` = ?");
              stmt.setInt(1, id);
              stmt.executeUpdate();
            });
  }

  public List<User> searchByKey(String key, String word) throws SQLException {
    return transactionManager
        .getTransaction()
        .queryList(conn -> PrepareStatements.setPreparedStatementParams(
            conn.prepareStatement("SELECT * FROM `users` WHERE ? LIKE '%?%'"), key, word)
            .executeQuery(), User::getFromResultSet);
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

  public static UserDao getInstance() {
    if (instance == null) {
      synchronized (UserDao.class) {
        if (instance == null) {
          instance = new UserDao();
        }
      }
    }
    return instance;
  }
}
