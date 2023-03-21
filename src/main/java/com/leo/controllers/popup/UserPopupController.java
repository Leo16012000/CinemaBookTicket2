package com.leo.controllers.popup;

import javax.swing.JFrame;

import com.leo.dao.UserDao;
import com.leo.models.User;

public class UserPopupController {
  UserDao userDao = UserDao.getInstance();
  JFrame previousView;

  // public void add(UserPopupView view, SuccessCallback sc, ErrorCallback ec) {
  // if (previousView != null && previousView.isDisplayable()) {
  // previousView.requestFocus();
  // return;
  // }
  // previousView = view;
  // view.setVisible(true);
  // view.getBtnCancel().addActionListener(evt -> view.dispose());
  // for (UserPermission permission : UserPermission.values()) {
  // view.getCboPermission().addItem(permission.getName());
  // }
  // view.getBtnOK().addActionListener(evt -> {
  // try {
  // addUser(view);
  // view.dispose();
  // view.showMessage("Added user successfully!");
  // sc.onSuccess();
  // } catch (Exception ex) {
  // ec.onError(ex);
  // }
  // });

  // }

  public User addUser(User user) throws Exception {
    String username = user.getUsername();
    String password = user.getPassword();
    String name = user.getName();

    // NOTE: Permission not implemented
    // UserPermission permission =
    // UserPermission.getByName(view.getCboPermission().getSelectedItem().toString());
    // System.out.println(permission + " " +
    // view.getCboPermission().getSelectedItem().toString());

    if (username.isEmpty() || password.isEmpty()) {
      throw new Exception("Please complete all the fields");
    }
    if (userDao.getByUsername(username) != null) {
      throw new Exception("User already existed");
    }
    User u = new User();
    u.setUsername(username);
    u.setPassword(password);
    u.setName(name);

    // u.setPermission(permission);

    return userDao.get(userDao.save(u));
  }

  // public void edit(UserPopupView view, User user, SuccessCallback sc,
  // ErrorCallback ec) {
  // if (previousView != null && previousView.isDisplayable()) {
  // previousView.requestFocus();
  // return;
  // }
  // previousView = view;
  // view.setVisible(true);
  // view.getBtnCancel().addActionListener(evt -> view.dispose());
  // for (UserPermission permission : UserPermission.values()) {
  // view.getCboPermission().addItem(permission.getName());
  // }
  // view.getLbTitle().setText("Update user - " + user.getId());
  // view.getTxtUsername().setText(user.getUsername());
  // view.getTxtPassword().setText(user.getPassword());
  // view.getTxtName().setText(user.getName());
  // view.getCboPermission().setSelectedItem(user.getPermission().getName());
  // view.getBtnOK().setText("Update");
  // view.getBtnOK().addActionListener(evt -> {
  // try {
  // editUser(view, user);
  // view.dispose();
  // view.showMessage("Updated successfully!");
  // sc.onSuccess();
  // } catch (Exception ex) {
  // ec.onError(ex);
  // }
  // });
  // }

  public User editUser(User u) throws Exception {
    String username = u.getUsername();
    String password = u.getPassword();
    String name = u.getName();

    // NOTE: Permission not implemented
    // UserPermission permission =
    // UserPermission.getByName(view.getCboPermission().getSelectedItem().toString());

    if (username.isEmpty() || password.isEmpty()) {
      throw new Exception("Please complete all the fields");
    }

    User temp = userDao.getByUsername(username);
    if (temp != null && temp.getId() != u.getId()) {
      throw new Exception("User already exists");
    }
    u.setUsername(username);
    u.setPassword(password);
    u.setName(name);

    // u.setPermission(permission);

    userDao.update(u);
    return userDao.get(u.getId());
  }
}
