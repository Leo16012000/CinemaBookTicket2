package com.leo.models;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends Model {
  private int id, durationTime, price;
  private String title, country;
  private Timestamp createdAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getDurationTime() {
    return durationTime;
  }

  public void setDurationTime(int durationTime) {
    this.durationTime = durationTime;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

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
    return title;
  }

  @Override
  public Object[] toRowTable() {
    // TODO Auto-generated method stub
    return new Object[] { id, title, country, durationTime, price, createdAt };
  }
}
