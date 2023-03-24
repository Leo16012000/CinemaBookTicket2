package com.leo.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SeatSelection {
  private SeatSelectionStatus status;
  private int seatRow;
  private int seatColumn;
  private int seatId;
  private int showtimeId;

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
