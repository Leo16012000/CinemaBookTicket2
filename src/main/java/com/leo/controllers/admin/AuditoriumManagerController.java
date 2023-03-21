package com.leo.controllers.admin;

import javax.swing.JOptionPane;

import com.leo.controllers.ManagerController;
import com.leo.dao.AuditoriumDao;
import com.leo.models.Auditorium;
import com.leo.views.admin.AuditoriumManagerView;
import com.leo.views.popup.AuditoriumPopupView;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;
import java.util.List;

public class AuditoriumManagerController extends ManagerController<Auditorium, AuditoriumManagerView> {
  private AuditoriumDao auditoriumDao;
  private AuditoriumPopupView popup;

  public AuditoriumManagerController(AuditoriumManagerView view) {
    super(view);
    this.auditoriumDao = AuditoriumDao.getInstance();
  }

  @Override
  public void actionAdd() {
    if (popup != null && popup.isVisible()) {
      return;
    }
    this.popup = new AuditoriumPopupView();
    popup.registerErrorHandler(view::showError);
    popup.show();
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
      Auditorium auditorium = auditoriumDao.get(selectedId);
      this.popup = new AuditoriumPopupView(auditorium);
      popup.registerErrorHandler(view::showError);
      popup.show();
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
      }
      view.refresh();
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public List<Auditorium> getAllData() {
    try {
      return auditoriumDao.getAll();
    } catch (Exception e) {
      view.showError(e);
      return new ArrayList<>();
    }
  }

  @Override
  public List<Auditorium> search(String key, String word) {
    try {
      return auditoriumDao.searchByKey(key, word);
    } catch (Exception e) {
      view.showError(e);
      return new ArrayList<>();
    }
  }
}
