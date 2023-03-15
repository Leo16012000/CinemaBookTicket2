/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.controllers;

import com.leo.dao.MovieDao;
import com.leo.main.SessionManager;
import com.leo.models.Movie;
import com.leo.views.HeaderPanel;
import com.leo.views.LoginView;
import com.leo.views.MainFrame;
import com.leo.views.MoviePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class UserHomeController {

  private MainFrame view;
  private HeaderPanel headerPanel;
  private MoviePanel moviePanel;
  private JTable movieTable;
  private JPanel contentPane;
  JPanel[] cards = { headerPanel };

  public UserHomeController() throws SQLException {
    view = new MainFrame();
    headerPanel = view.getHeaderPanel();
    moviePanel = view.getMoviePanel();
    movieTable = moviePanel.getMovieTable();
    contentPane = view.getContentPane();
    view.setVisible(true);
    addEvent();
  }

  private void addEvent() {
    headerPanel.getBtnLogout().addActionListener(evt -> {
      int confirm = JOptionPane.showConfirmDialog(view, "Do you want to log out?");
      if (confirm != JOptionPane.YES_OPTION) {
        return;
      }
      try {
        SessionManager.update();// Đăng xuất
      } catch (SQLException ex) {
        headerPanel.showError(ex);
      }
      view.dispose();
      new LoginController(new LoginView());
      System.out.println("click logout");
    });
    headerPanel.getBtnLogin().addActionListener(evt -> {
      view.dispose();
      new LoginController(new LoginView());
      System.out.println("click login");
    });
    // Add a MouseListener to the JTable
    movieTable.addMouseListener(new MyMouseListener());
    moviePanel.getBtnSearch().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArrayList<Movie> movies;
        try {
          contentPane.remove(moviePanel);
          movies = new ArrayList<>(
              MovieDao.getInstance().searchByKey("title", moviePanel.getTextField().getText()));
          moviePanel.updatePanel(movies, contentPane);
          moviePanel.setBounds(194, 32, 432, 379);
          contentPane.add(moviePanel);
          moviePanel.setLayout(null);
          SwingUtilities.updateComponentTreeUI(contentPane);
          System.out.println("trigger search " + moviePanel.getTextField().getText());
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    });
  }

  // ------------------------------------------------------------------------------
  public MainFrame getView() {
    return view;
  }

  public void setView(MainFrame view) {
    this.view = view;
  }

  private class MyMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      // Check if the event was a single left-click
      if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
        // Get the index of the clicked row
        int rowIndex = movieTable.rowAtPoint(e.getPoint());

        // Do something with the clicked row
        System.out.println("Clicked row: " + movieTable.getValueAt(rowIndex, 0));
        int movieId = Integer.valueOf((String) movieTable.getValueAt(rowIndex, 0));
        String movieName = (String) movieTable.getValueAt(rowIndex, 0);
        try {
          view.displayShowtimePanel(movieName, movieId);
          view.remove(moviePanel);
          view.repaint();
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }
  }
}
