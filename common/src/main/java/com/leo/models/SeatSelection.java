package com.leo.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatSelection {
  private SeatSelectionStatus status;
  private int seatRow;
  private int seatColumn;
  private int seatId;
  private int showtimeId;
  private Integer userId;

  public void setStatus(SeatSelectionStatus status) {
    this.status = status;
  }

  public boolean toggle() {
    switch (status) {
      case RESERVED:
        return false;
      case AVAILABLE:
        status = SeatSelectionStatus.HOLD;
        return true;
      case HOLD:
        status = SeatSelectionStatus.AVAILABLE;
        return true;
      default:
        throw new UnsupportedOperationException();
    }
  };

  public enum SeatSelectionStatus {
    RESERVED, AVAILABLE, HOLD
  }
}
