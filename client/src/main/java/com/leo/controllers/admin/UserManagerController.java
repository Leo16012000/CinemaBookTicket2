package com.leo.controllers.admin;

import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.UserPopupController;
import com.leo.dao.UserDao;
import com.leo.dtos.ResponseDto;
import com.leo.utils.UserPermission;
import com.leo.views.popup.UserPopupView;
import com.leo.models.User;

import javax.swing.JOptionPane;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.List;

public class UserManagerController extends ManagerController {
  UserDao userDao = UserDao.getInstance();
  UserPopupController popupController = new UserPopupController();

  public UserManagerController() {
    super();
  }

  @Override
  public void actionAdd() {
    popupController.add(new UserPopupView(), this::updateData, view::showError);
  }

  @Override
  public void actionEdit() {
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
      popupController.edit(new UserPopupView(), u, this::updateData, view::showError);

    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public ResponseDto actionDelete() {
    int selectedIds[] = view.getSelectedIds();
    try {
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete user", ERROR_MESSAGE) != YES_OPTION) {
          return null;
      }
      for (int i = 0; i < selectedIds.length; i++) {
        userDao.deleteById(selectedIds[i]);
        updateData();
      }
    } catch (Exception e) {
      view.showError(e);
    }
      return null;
  }

  @Override
  public void updateData() {
    try {
      List<User> users = userDao.getAll();
      System.out.println(users);
      view.setTableData(users);
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionSearch() {
    try {
      List<User> users = userDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
          String.valueOf(view.getTxtSearch().getText()));
      view.setTableData(users);
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
