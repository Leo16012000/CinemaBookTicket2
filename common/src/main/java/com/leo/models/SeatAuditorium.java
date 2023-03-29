package com.leo.models;

import java.util.Arrays;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatAuditorium implements Cloneable {
  private SeatSelection[][] seats;
  private int auditoriumWidth;
  private int auditoriumHeight;
  private int auditoriumId;
  private Showtime showtime;

  public SeatSelection getSeatSelectionAt(int row, int column) {
    return seats[row][column];
  }

  public Stream<SeatSelection> streamSeat() {
    return Arrays.stream(seats).flatMap(Arrays::stream);
  }

  @Override
  public SeatAuditorium clone() {
    try {
      return (SeatAuditorium) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}
