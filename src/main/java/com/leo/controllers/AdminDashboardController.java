package com.leo.controllers;

import com.leo.controllers.admin.AuditoriumManagerController;
import com.leo.controllers.admin.MovieManagerController;
import com.leo.controllers.admin.UserManagerController;
import com.leo.main.SessionManager;
import com.leo.models.User;
import com.leo.views.AdminDashboardFrame;
import com.leo.views.LoginView;
import com.leo.views.admin.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminDashboardController {
  private AdminDashboardFrame view;
  private ManagerController userManagerController;
  private ManagerController movieManagerController;
  private ManagerController auditoriumManagerController;
  // movieManagerController = new MovieManagerController();

  private JPanel headerPanel;

  private ManagerPaneView userManagerView, movieManagerView, auditoriumManagerView; // View
  // movieManagerView = new MovieManagerView();
  private JPanel[] cards;
  private SidebarController sidebarController = new SidebarController();

  public AdminDashboardController() throws SQLException {
    view = new AdminDashboardFrame();

    userManagerView = new UserManagerView();
    movieManagerView = new MovieManagerView();
    auditoriumManagerView = new AuditoriumManagerView();

    userManagerController = new UserManagerController();
    movieManagerController = new MovieManagerController();
    auditoriumManagerController = new AuditoriumManagerController();

    headerPanel = view.getPanelHeader();
    sidebarController.setPanelSidebar(view.getPanelSidebar());
    cards = new JPanel[] { movieManagerView, userManagerView, auditoriumManagerView };
    view.setVisible(true);
    initMenu();
    addEvent();
    User session = SessionManager.getSession().getUser();
    if (session != null) {
      view.getLbName().setText(session.getName());
    }
    view.setCards(cards);
    view.setPanel(headerPanel);
  }

  public AdminDashboardFrame getView() {
    return view;
  }

  public void setView(AdminDashboardFrame view) {
    this.view = view;
    sidebarController.setPanelSidebar(view.getPanelSidebar());
  }

  private void initMenu() {
    MenuItem menuUser = new MenuItem("USER", "User Management");
    MenuItem menuMovie = new MenuItem("MOVIE", "Movie Management");
    MenuItem menuAuditorium = new MenuItem("AUDITORIUM", "Auditorium Management");
    sidebarController.addMenu(menuUser);
    sidebarController.addMenu(menuMovie);
    sidebarController.addMenu(menuAuditorium);
    sidebarController.addMenuEvent(this::onMenuChange);
  }

  // Tạo sự kiện
  private void addEvent() {
    view.getBtnLogout().addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent evt) {
        System.out.println("logout click");
        int confirm = JOptionPane.showConfirmDialog(view, "Do you want to log out?");
        if (confirm != JOptionPane.YES_OPTION) {
          return;
        }
        try {
          SessionManager.update();// Log out
        } catch (SQLException ex) {
          view.showError(ex);
        }
        view.dispose();
        new LoginController(new LoginView());
      }
    });
  }

  private void onMenuChange(MenuItem item) {
    System.out.println("menu change" + item.getId());
    switch (item.getId()) {
      case "USER":
        view.setPanel(userManagerView);
        userManagerController.setView(userManagerView);
        userManagerController.updateData();
        break;
      case "MOVIE":
        view.setPanel(movieManagerView);
        movieManagerController.setView(movieManagerView);
        movieManagerController.updateData();
        break;
      case "AUDITORIUM":
        view.setPanel(auditoriumManagerView);
        auditoriumManagerController.setView(auditoriumManagerView);
        auditoriumManagerController.updateData();
        break;
    }
  }
}
