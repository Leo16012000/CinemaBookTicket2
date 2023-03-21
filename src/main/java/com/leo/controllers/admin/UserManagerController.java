package com.leo.controllers.admin;

import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.UserPopupController;
import com.leo.dao.UserDao;
import com.leo.utils.UserPermission;
import com.leo.views.admin.UserManagerView;
import com.leo.views.popup.UserPopupView;
import com.leo.models.User;

import javax.swing.JOptionPane;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;
import java.util.List;

public class UserManagerController extends ManagerController<User, UserManagerView> {
  private UserDao userDao;
  private UserPopupView popup;

  public UserManagerController(UserManagerView view) {
    super(view);
    this.userDao = UserDao.getInstance();
  }

  @Override
  public void actionAdd() {
    if (popup != null && popup.isVisible()) {
      return;
    }
    this.popup = new UserPopupView();
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
        throw new Exception("Choose user need to edit");
      }
      User u = userDao.get(selectedId);
      if (u == null) {
        throw new Exception("Invalid user selected");
      }
      if (u.getPermission() == UserPermission.ADMIN) {
        int value = JOptionPane.showConfirmDialog(null, "Are you sure that you want to edit admin?");
        if (value != YES_OPTION) {
          return;
        }
      }
      this.popup = new UserPopupView();
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
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete user", ERROR_MESSAGE) != YES_OPTION) {
        return;
      }
      for (int i = 0; i < selectedIds.length; i++) {
        userDao.deleteById(selectedIds[i]);
      }
      view.refresh();
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public List<User> getAllData() {
    try {
      return userDao.getAll();
    } catch (Exception e) {
      view.showError(e);
      return new ArrayList<>();
    }
  }

  @Override
  public List<User> search(String key, String word) {
    try {
      return userDao.searchByKey(key, word);
    } catch (Exception e) {
      view.showError(e);
      return new ArrayList<>();
    }
  }
}
