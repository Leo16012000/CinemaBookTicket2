package com.leo.controllers.admin;

import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.AuditoriumPopupController;
import com.leo.models.Auditorium;
import com.leo.service.IAuditoriumService;
import com.leo.service.impl.AuditoriumService;
import com.leo.views.popup.AuditoriumPopupView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

public class AuditoriumManagerController extends ManagerController {
  private AuditoriumPopupController popupController;
  private static Logger logger = LogManager.getLogger(AuditoriumManagerController.class);
  private IAuditoriumService auditoriumService = AuditoriumService.getInstance();

  public AuditoriumManagerController() {
    super();
    popupController = new AuditoriumPopupController();
  }

  @Override
  public void actionAdd() {
    popupController.add(new AuditoriumPopupView(), this::updateData, view::showError);
  }

  @Override
  public void actionEdit() {
    try {
      int selectedId = view.getSelectedId();
      if (selectedId < 0) {
        throw new Exception("Chooose the one to edit");
      }
      Auditorium auditorium = auditoriumService.get(selectedId);
      if (auditorium == null) {
        throw new Exception("Invalid auditorium selected");
      }
      popupController.edit(new AuditoriumPopupView(), auditorium, this::updateData, view::showError);

    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void actionDelete() {
    try {
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete auditorium",
          ERROR_MESSAGE) != YES_OPTION) {
      }
      auditoriumService.deleteByIds(view.getSelectedIds());
      updateData();
    } catch (Exception e) {
      // view.showError(e);
    }
  }

  @Override
  public void updateData() {
    try {
      List<Auditorium> auditoriums = auditoriumService.getAll();
      System.out.println(auditoriums);
      view.setTableData(auditoriums);
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionSearch() {
    try {
      List<Auditorium> auditoriums = auditoriumService.searchByKey(
          view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
      view.setTableData(auditoriums);
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
