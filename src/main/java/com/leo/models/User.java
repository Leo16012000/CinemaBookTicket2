package com.leo.models;

import com.leo.utils.UserPermission;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class User implements Model {
  private int id;
  private String name;
  private String username;
  private String password;
  private UserPermission permission;
  private Timestamp createdAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public static User getFromResultSet(ResultSet rs) throws SQLException {
    User o = new User();
    o.setId(rs.getInt("id"));
    o.setName(rs.getNString("name"));
    o.setUsername(rs.getNString("username"));
    o.setPassword(rs.getNString("password"));
    o.setPermission(UserPermission.getByCode(rs.getNString("permission")));
    o.setCreatedAt(rs.getTimestamp("created_at"));
    return o;
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

  public UserPermission getPermission() {
    return permission;
  }

  public void setPermission(UserPermission permission) {
    this.permission = permission;
  }

  @Override
  public String toString() {
    return id + " " + username + " " + password + " " + createdAt + " " + permission;
  }

  @Override
  public Object[] toRowTable() {
    return new Object[] {
        id, name, username,
        permission.getName(), createdAt
    };
  }
}