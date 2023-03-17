package com.leo.models;

import com.leo.utils.UserPermission;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Getter
@Setter
public class User extends Model {
  private int id;
  private String name;
  private String username;
  private String password;
  private UserPermission permission;
  private Timestamp createdAt;

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
    // TODO Auto-generated method stub
    return id + " " + username + " " + password + " " + createdAt + " " + permission;
  }

  @Override
  public Object[] toRowTable() {
    // TODO Auto-generated method stub
    return new Object[] {
        id, name, username,
        permission.getName(), createdAt
    };
  }
}