package com.leo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie extends Model {
  private int id, durationTime, price;
  private String title, country;
  private Timestamp createdAt;

  public static Movie getFromResultSet(ResultSet rs) throws SQLException {
    Movie o = new Movie();
    o.setId(rs.getInt("id"));
    o.setPrice(rs.getInt("price"));
    o.setDurationTime(rs.getInt("duration_time"));
    o.setTitle(rs.getNString("title"));
    o.setCountry(rs.getNString("country"));
    o.setCreatedAt(rs.getTimestamp("created_at"));
    return o;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return title + " " + country + " " + durationTime + " " + price;
  }

  @Override
  public Object[] toRowTable() {
    // TODO Auto-generated method stub
    return new Object[] { id, title, country, durationTime, price, createdAt };
  }
}
