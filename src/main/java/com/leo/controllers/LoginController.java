package com.leo.controllers;

import com.leo.dao.UserDao;
import com.leo.main.SessionManager;
import com.leo.views.LoginView;
import com.leo.models.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class LoginController {

  private LoginView view;
  UserDao userDao = new UserDao();

  public LoginController(LoginView view) {
    this.view = view;
    view.setVisible(true);
    addEvent();
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
    String username = view.getTxtUsername().getText();
    String password = new String(view.getTxtPassword().getPassword());
    try {
      User user = userDao.getByUsername(username);
      if (user == null) {
        view.showError("Account is not existed!");
        return;
      }
      if (!user.checkPassword(password)) {
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
          AdminDashboardController controller2 = new AdminDashboardController();
          // controller.getView().setPanel(new MainPanel());
          view.dispose();// Tắt form đăng nhập
          break;
        default:
          view.showError("Unexpected error");
          SessionManager.update();
          view.dispose();
          break;
      }
    } catch (Exception e) {
      view.showError(e);
    }
  }

  // Tạo sự kiện
  public void addEvent() {
    // Sự kiện login
    view.getTxtPassword().addKeyListener(new java.awt.event.KeyAdapter() {
      @Override
      public void keyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          view.getBtnLogin().doClick();
        }
      }
    });
    view.getBtnLogin().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        login();
      }
    });
    view.getLblForgotPassword().addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        view.showMessage("Chưa hỗ trợ!");
      }
    });
    view.getLblRegister().addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        view.showMessage("Chưa hỗ trợ!");
      }
    });
    view.getLblAccessAsGuest().addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        try {
          loginAsGuest();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
  }

}
