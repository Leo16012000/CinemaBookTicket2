package com.leo.views;

import com.leo.controllers.LoginController;
import com.leo.dao.ShowtimeDao;
import com.leo.main.SessionManager;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JTable;

import javax.swing.JLabel;
import java.sql.SQLException;
import com.leo.models.Showtime;
import com.leo.utils.ErrorPopup;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MainFrame extends JFrame {
  private static final Logger logger = LogManager.getLogger(MainFrame.class);

  private JTextField textField;
  private JTable movieTable;
  private JTable reservationTable;
  private HeaderPanel headerPanel;

  private MoviePanel moviePanel;
  private ShowtimePanel showtimePanel;

  /**
   * Launch the application.
   * 
   * @throws UnsupportedLookAndFeelException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws ClassNotFoundException
   */
  public static void main(String[] args)
      throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MainFrame frame = new MainFrame();
          frame.pack();
          frame.setVisible(true);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }
    });
  }

  /**
   * Create the frame.
   * 
   * @throws SQLException
   */
  public MainFrame() throws SQLException {
    super();

    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1280, 1024);

    headerPanel = new HeaderPanel();
    headerPanel.setBounds(1100, -16, 133, 120);
    add(headerPanel);

    displayMoviePanel();

    registerEvent();
  }

  private void registerEvent() {
    headerPanel.getBtnLogin().addActionListener(e -> {
      backToLogin();
    });
    headerPanel.getBtnLogout().addActionListener(e -> {
      try {
        SessionManager.update();
        backToLogin();
      } catch (SQLException e1) {
        ErrorPopup.show(e1);
      }
    });
    headerPanel.getBtnBack().addActionListener(e -> {
      backToLogin();
    });
  }

  private void backToLogin() {
    dispose();
    new LoginController(new LoginView());
  }

  public void displayMoviePanel() throws SQLException {
    moviePanel = new MoviePanel();
    add(moviePanel);
  }

  public void displayShowtimePanel(String movieName, int movieId) throws SQLException {
    List<Showtime> showtimes = ShowtimeDao.getInstance().searchByKey("movie_id", Integer.toString(movieId));
    showtimePanel = new ShowtimePanel(showtimes);
    showtimePanel.setBounds(114, 32, 553, 471);
    add(showtimePanel);
    showtimePanel.setLayout(null);

    JLabel lblMovieName = new JLabel(movieName);
    lblMovieName.setBounds(211, 28, 154, 15);
    showtimePanel.add(lblMovieName);
  }
}
