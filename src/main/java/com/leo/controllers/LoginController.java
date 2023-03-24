package com.leo.controllers;

import com.leo.dao.UserDao;
import com.leo.main.SessionManager;
import com.leo.views.LoginView;
import com.leo.views.MainFrame;
import com.leo.models.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginController {
  private static final Logger logger = LogManager.getLogger(LoginController.class);

  private LoginView view;
  UserDao userDao = UserDao.getInstance();
  private MainFrame bookingMovieFrame;

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
    if (!Optional.ofNullable(bookingMovieFrame).map(it -> it.isVisible()).orElse(false)) {
      view.dispose();
      this.bookingMovieFrame = new MainFrame();
      bookingMovieFrame.pack();
      bookingMovieFrame.setVisible(true);
    }
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
          MainFrame bookingMovieFrame;
          bookingMovieFrame = new MainFrame();
          bookingMovieFrame.pack();
          bookingMovieFrame.setVisible(true);

          view.dispose();
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
          logger.error(e.getMessage());
        }
      }
    });
  }

}
