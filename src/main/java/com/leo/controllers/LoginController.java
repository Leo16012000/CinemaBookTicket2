package com.leo.controllers;

import com.leo.dao.UserDao;
import com.leo.views.AdminDashboardFrame;
import com.leo.views.LoginView;
import com.leo.models.Login;
import com.leo.models.User;
import com.leo.utils.SessionManager;

import java.sql.SQLException;

public class LoginController {

  private LoginView view;
  private UserDao userDao;

  public LoginController(LoginView view) {
    this.view = view;
    this.userDao = UserDao.getInstance();
  }

  public LoginView getView() {
    return view;
  }

  public void setView(LoginView view) {
    this.view = view;
    view.setVisible(true);
  }

  public void loginAsGuest() throws SQLException {
    UserHomeController controller = new UserHomeController();
    // controller.getView().setPanel(new HomeView());
  }

  public void login() {
    Login model = view.getModel();
    String username = model.getUsername();
    String password = model.getPassword();
    try {
      User user = userDao.getByUsername(username);
      if (user == null) {
        view.showError("Account is not existed!");
        return;
      }
      // TODO: Compare using hash password
      if (!password.trim().equalsIgnoreCase(user.getPassword().trim())) {
        view.showError("Wrong password");
        return;
      }
      SessionManager.create(user);// Khởi tạo session
      switch (user.getPermission()) {
        case USER:
          UserHomeController controller = new UserHomeController();
          // controller.getView().setPanel(new MainPanel());
          view.dispose();// Tắt form đăng nhập
          break;
        case ADMIN:
          AdminDashboardFrame adminDashboardView = new AdminDashboardFrame();
          adminDashboardView.setVisible(true);
          view.dispose();// Tắt form đăng nhập
          break;
        default:
          view.showError("Unexpected error");
          SessionManager.logout();
          view.dispose();
          break;
      }
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
