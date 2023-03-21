package com.leo.controllers.admin;

import com.leo.controllers.ManagerController;
import com.leo.dao.ShowtimeDao;
import com.leo.dao.ShowtimeDao;
import com.leo.models.Showtime;
import com.leo.views.admin.ShowtimeManagerView;
import com.leo.views.popup.ShowtimePopupView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

public class ShowtimeManagerController extends ManagerController<Showtime, ShowtimeManagerView> {
  private ShowtimeDao showtimeDao;
  private ShowtimePopupView popup;

  public ShowtimeManagerController(ShowtimeManagerView view) {
    super(view);
    this.showtimeDao = ShowtimeDao.getInstance();
  }

  @Override
  public void actionAdd() throws SQLException {
    if (popup != null && popup.isVisible()) {
      return;
    }
    this.popup = new ShowtimePopupView();
    popup.registerErrorHandler(view::showError);
    popup.registerConfirmHandler(it -> view.updateData());
    popup.popup();
  }

  @Override
  public void actionEdit() {
    if (popup != null && popup.isVisible()) {
      return;
    }
    try {
      int selectedId = view.getSelectedId();
      if (selectedId < 0) {
        throw new Exception("Chooose the one to edit");
      }
      Showtime showtime = showtimeDao.get(selectedId);
      if (showtime == null) {
        throw new Exception("Invalid showtime selected");
      }
      this.popup = new ShowtimePopupView();
      popup.registerErrorHandler(view::showError);
      popup.registerConfirmHandler(view::updateData);
      popup.popup();
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
      }
      view.refresh();
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public List<Showtime> getAllData() {
    try {
      return showtimeDao.getAll();
    } catch (Exception e) {
      view.showError(e);
      return new ArrayList<>();
    }
  }

  @Override
  public List<Showtime> search(String key, String word) {
    try {
      return showtimeDao.searchByKey(key, word);
    } catch (Exception e) {
      view.showError(e);
      return new ArrayList<>();
    }
  }
}
