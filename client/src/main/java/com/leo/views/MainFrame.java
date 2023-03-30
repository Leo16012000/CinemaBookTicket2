package com.leo.views;

import com.leo.controllers.LoginController;
import com.leo.main.SessionManager;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JTable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leo.models.Showtime;
import com.leo.service.IMovieService;
import com.leo.service.IShowtimeService;
import com.leo.service.impl.MovieService;
import com.leo.service.impl.ShowtimeService;

import lombok.Getter;

import java.util.List;

@Getter
public class MainFrame extends JFrame {
  private static final Logger logger = LogManager.getLogger(MainFrame.class);

  private JTextField textField;
  private JTable movieTable;
  private JTable reservationTable;
  private IMovieService movieService = MovieService.getInstance();
  private IShowtimeService showtimeService = ShowtimeService.getInstance();


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
   * @throws IOException
   */
  public MainFrame() throws IOException {
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
      SessionManager.getInstance().clear();
      backToLogin();
    });
    headerPanel.getBtnBack().addActionListener(e -> {
      backToLogin();
    });
  }

  private void backToLogin() {
    dispose();
    new LoginController(new LoginView());
  }

  public void displayMoviePanel() throws IOException {
    moviePanel = new MoviePanel();
    add(moviePanel);
  }

  public void displayShowtimePanel(String movieName, int movieId) throws IOException {
    List<Showtime> showtimes = showtimeService.searchByKey("movie_id", Integer.toString(movieId));
    showtimePanel = new ShowtimePanel(showtimes);
    showtimePanel.setBounds(114, 32, 553, 471);
    add(showtimePanel);
    showtimePanel.setLayout(null);

    JLabel lblMovieName = new JLabel(movieName);
    lblMovieName.setBounds(211, 28, 154, 15);
    showtimePanel.add(lblMovieName);
  }
}
