package com.leo.controllers;

import java.sql.SQLException;
import java.util.List;

import com.leo.dao.SeatAuditoriumDao;
import com.leo.main.SessionManager;
import com.leo.models.SeatAuditorium;
import com.leo.models.SeatSelection;
import com.leo.models.User;
import com.leo.models.SeatSelection.SeatSelectionStatus;

public class SeatPanelController {
  private SeatAuditoriumDao seatAuditoriumDao = SeatAuditoriumDao.getInstance();

  public SeatAuditorium getSeatAuditorium(int showtimeId) throws SQLException {
    return seatAuditoriumDao.getSeatAuditorium(showtimeId);
  }

  public void reserve(int showtimeId, List<SeatSelection> selections) throws SQLException {
    User user = SessionManager.getSession().getUser();
    seatAuditoriumDao.save(user.getId(), showtimeId, selections);
    selections.forEach(it -> it.setStatus(SeatSelectionStatus.RESERVED));
  }
}
