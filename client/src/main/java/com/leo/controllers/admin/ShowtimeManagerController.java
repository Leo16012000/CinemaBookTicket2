package com.leo.controllers.admin;

import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.ShowtimePopupController;
import com.leo.models.Showtime;
import com.leo.service.IShowtimeService;
import com.leo.service.impl.ShowtimeService;
import com.leo.views.popup.ShowtimePopupView;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

public class ShowtimeManagerController extends ManagerController {
  private ShowtimePopupController popupController;
  private IShowtimeService showtimeService = ShowtimeService.getInstance();
  private Logger logger = LogManager.getLogger(ShowtimeManagerController.class);

  public ShowtimeManagerController() {
    super();
    popupController = new ShowtimePopupController();
  }

  @Override
  public void actionAdd() throws IOException {
    popupController.add(new ShowtimePopupView(), this::updateData, view::showError);
  }

  @Override
  public void actionEdit() {
    try {
      int selectedId = view.getSelectedId();
      if (selectedId < 0) {
        throw new Exception("Chooose the one to edit");
      }
      Showtime showtime = showtimeService.get(selectedId);
      if (showtime == null) {
        throw new Exception("Invalid showtime selected");
      }
      popupController.edit(new ShowtimePopupView(), showtime, this::updateData, view::showError);

    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void actionDelete() {
    List<Integer> selectedIds = view.getSelectedIds();
    try {
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete showtime",
          ERROR_MESSAGE) != YES_OPTION) {
        return;
      }
      showtimeService.deleteByIds(selectedIds);
      updateData();
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void updateData() {
    try {
      List<Showtime> showtimes = showtimeService.getAll();
      logger.debug(showtimes);
      view.setTableData(showtimes);
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionSearch() {
    try {
      List<Showtime> showtimes = showtimeService.searchByKey(
          view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
      view.setTableData(showtimes);
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
