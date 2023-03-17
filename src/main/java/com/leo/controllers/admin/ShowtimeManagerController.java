package com.leo.controllers.admin;

import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.ShowtimePopupController;
import com.leo.controllers.popup.ShowtimePopupController;
import com.leo.dao.ShowtimeDao;
import com.leo.dao.ShowtimeDao;
import com.leo.models.Showtime;
import com.leo.views.popup.ShowtimePopupView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

public class ShowtimeManagerController extends ManagerController {
  private ShowtimeDao showtimeDao;
  private ShowtimePopupController popupController;

  public ShowtimeManagerController() {
    super();
    showtimeDao = new ShowtimeDao();
    popupController = new ShowtimePopupController();
  }

  @Override
  public void actionAdd() throws SQLException {
    popupController.add(new ShowtimePopupView(), this::updateData, view::showError);
  }

  @Override
  public void actionEdit() {
    try {
      int selectedId = view.getSelectedId();
      if (selectedId < 0) {
        throw new Exception("Chooose the one to edit");
      }
      Showtime showtime = showtimeDao.get(selectedId);
      if (showtime == null) {
        throw new Exception("Invalid showtime selected");
      }
      popupController.edit(new ShowtimePopupView(), showtime, this::updateData, view::showError);

    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionDelete() {
    int selectedIds[] = view.getSelectedIds();
    try {
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete showtime",
          ERROR_MESSAGE) != YES_OPTION) {
        return;
      }
      for (int i = 0; i < selectedIds.length; i++) {
        showtimeDao.deleteById(selectedIds[i]);
        updateData();
      }
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void updateData() {
    try {
      ArrayList<Showtime> showtimes = showtimeDao.getAll();
      System.out.println(showtimes);
      view.setTableData(showtimes);
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionSearch() {
    try {
      ArrayList<Showtime> showtimes = showtimeDao.searchByKey(
          view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
      view.setTableData(showtimes);
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
