package com.leo.controllers.admin;

import javax.swing.JOptionPane;

import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.AuditoriumPopupController;
import com.leo.dao.AuditoriumDao;
import com.leo.models.Auditorium;
import com.leo.views.popup.AuditoriumPopupView;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.List;

public class AuditoriumManagerController extends ManagerController {
  private AuditoriumDao auditoriumDao;
  private AuditoriumPopupController popupController;

  public AuditoriumManagerController() {
    super();
    auditoriumDao = AuditoriumDao.getInstance();
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
      Auditorium auditorium = auditoriumDao.get(selectedId);
      if (auditorium == null) {
        throw new Exception("Invalid auditorium selected");
      }
      popupController.edit(new AuditoriumPopupView(), auditorium, this::updateData, view::showError);

    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionDelete() {
    int selectedIds[] = view.getSelectedIds();
    try {
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete auditorium",
          ERROR_MESSAGE) != YES_OPTION) {
        return;
      }
      for (int i = 0; i < selectedIds.length; i++) {
        auditoriumDao.deleteById(selectedIds[i]);
        updateData();
      }
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void updateData() {
    try {
      List<Auditorium> auditoriums = auditoriumDao.getAll();
      System.out.println(auditoriums);
      view.setTableData(auditoriums);
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionSearch() {
    try {
      List<Auditorium> auditoriums = auditoriumDao.searchByKey(
          view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
      view.setTableData(auditoriums);
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
