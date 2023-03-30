package com.leo.controllers;

import com.leo.controllers.admin.AuditoriumManagerController;
import com.leo.controllers.admin.MovieManagerController;
import com.leo.controllers.admin.ShowtimeManagerController;
import com.leo.controllers.admin.UserManagerController;
import com.leo.main.SessionManager;
import com.leo.models.User;
import com.leo.views.AdminDashboardFrame;
import com.leo.views.LoginView;
import com.leo.views.admin.*;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardController {
  private AdminDashboardFrame view;
  private ManagerController userManagerController;
  private ManagerController movieManagerController;
  private ManagerController auditoriumManagerController;

  private ManagerController showtimeManagerController;
  // movieManagerController = new MovieManagerController();
  private Logger logger = LogManager.getLogger(AdminDashboardController.class);

  private JPanel headerPanel;

  private ManagerPaneView userManagerView, movieManagerView, auditoriumManagerView, showtimeManagerView; // View
  // movieManagerView = new MovieManagerView();
  private JPanel[] cards;
  private SidebarController sidebarController = new SidebarController();

  public AdminDashboardController() {
    view = new AdminDashboardFrame();

    userManagerView = new UserManagerView();
    movieManagerView = new MovieManagerView();
    auditoriumManagerView = new AuditoriumManagerView();
    showtimeManagerView = new ShowtimeManagerView();

    userManagerController = new UserManagerController();
    movieManagerController = new MovieManagerController();
    auditoriumManagerController = new AuditoriumManagerController();
    showtimeManagerController = new ShowtimeManagerController();

    headerPanel = view.getPanelHeader();
    sidebarController.setPanelSidebar(view.getPanelSidebar());
    cards = new JPanel[] { movieManagerView, userManagerView, auditoriumManagerView, showtimeManagerView };
    view.setVisible(true);
    initMenu();
    addEvent();
    User session = SessionManager.getInstance().getSession();
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
    MenuItem menuShowtime = new MenuItem("SHOWTIME", "Showtime Management");
    sidebarController.addMenu(menuUser);
    sidebarController.addMenu(menuMovie);
    sidebarController.addMenu(menuAuditorium);
    sidebarController.addMenu(menuShowtime);
    sidebarController.addMenuEvent(this::onMenuChange);
  }

  // Tạo sự kiện
  private void addEvent() {
    view.getBtnLogout().addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent evt) {
        logger.debug("logout click");
        int confirm = JOptionPane.showConfirmDialog(view, "Do you want to log out?");
        if (confirm != JOptionPane.YES_OPTION) {
          return;
        }
        SessionManager.getInstance().clear();// Log out
        view.dispose();
        new LoginController(new LoginView());
      }
    });
  }

  private void onMenuChange(MenuItem item) {
    logger.debug("menu change" + item.getId());
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
      case "SHOWTIME":
        view.setPanel(showtimeManagerView);
        showtimeManagerController.setView(showtimeManagerView);
        showtimeManagerController.updateData();
        break;
    }
  }
}
