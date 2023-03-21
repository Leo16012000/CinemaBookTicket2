package com.leo.controllers.popup;

import java.sql.SQLException;

import com.leo.dao.AuditoriumDao;
import com.leo.models.Auditorium;

public class AuditoriumPopupController {
  private AuditoriumDao auditoriumDao = AuditoriumDao.getInstance();

  public Auditorium addAuditorium(Auditorium model) throws SQLException {
    return auditoriumDao.get(auditoriumDao.save(model));
  }

  public Auditorium editAuditorium(Auditorium auditorium) throws Exception {
    Integer number = auditorium.getAuditoriumNum();
    Integer rowsNum = auditorium.getSeatsRowNum();
    Integer columnsNum = auditorium.getSeatsColumnNum();
    if (number == null || rowsNum == null || columnsNum == null) {
      throw new Exception("Please complete all infomation!");
    }
    auditoriumDao.update(auditorium);
    return auditoriumDao.get(auditorium.getId());
  }
}
