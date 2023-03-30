package com.leo.views;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.leo.controllers.MovieBookingPanelController;
import com.leo.models.Auditorium;
import com.leo.models.Showtime;
import com.leo.utils.ErrorPopup;
import com.leo.utils.GridBagConstraintsBuilder;

public class MovieBookingFrame extends JFrame implements IView<Object> {
  private int movieId;
  private Object model;
  private List<Showtime> showtimes;
  private Map<Integer, Auditorium> auditoriumGroupByShowtime;
  private Map<Integer, Auditorium> auditoriumGroupById;
  private Map<Timestamp, List<Showtime>> showtimeGroupByStartTime;
  private MovieBookingPanelController controller;
  private JComboBox<String> startTimeComboBox;
  private JComboBox<String> auditoriumComboBox;
  private Timestamp selectedStartTime;
  private Auditorium selectedAuditorium;
  private Showtime selectedShowtime;
  private SeatAuditoriumPanel seatAuditoriumPanel;
  private JPanel startTimePanel;
  private JPanel auditoriumPanel;
  private GridBagConstraintsBuilder defaultGbcBuilder = GridBagConstraintsBuilder.builder().gridx(0).gridy(0)
      .weightx(1.).anchor(GridBagConstraints.LINE_START).fill(GridBagConstraints.HORIZONTAL);

  public Window parent;

  public MovieBookingFrame(Window parent, int movieId, MovieBookingPanelController controller) {
    try {
      this.movieId = movieId;
      this.controller = controller;
      this.parent = parent;
      init();
      initUI();
      registerEvent();
    } catch (Exception e) {
      handleError(e);
    }
  }

  public MovieBookingFrame(Window parent, int movieId) {
    this(parent, movieId, new MovieBookingPanelController());
  }

  public void getData() throws IOException {
    this.showtimes = controller.getMovieShowtimes(movieId);
    this.showtimeGroupByStartTime = new HashMap<>();
    for (Showtime showtime : showtimes) {
      List<Showtime> showtimeList = showtimeGroupByStartTime.getOrDefault(showtime.getStartTime(), new ArrayList<>());
      showtimeList.add(showtime);
      showtimeGroupByStartTime.put(showtime.getStartTime(), showtimeList);
    }
    this.auditoriumGroupByShowtime = showtimes.stream().collect(Collectors.toMap(it -> it.getId(), it -> {
      try {
        return controller.getAuditorium(it);
      } catch (Exception e) {
        handleError(e);
        return null;
      }
    }));
    this.auditoriumGroupById = auditoriumGroupByShowtime.values().stream()
        .collect(Collectors.toMap(it -> it.getId(), it -> it));
  }

  @Override
  public void init() throws Exception {
    getData();
  }

  @Override
  public void initUI() {
    setLayout(new GridBagLayout());

    this.startTimePanel = new JPanel();
    this.auditoriumPanel = new JPanel();
    add(startTimePanel, defaultGbcBuilder.clone().weighty(0.).build());
    add(auditoriumPanel, defaultGbcBuilder.clone().weighty(0.).gridy(1).build());

    this.startTimeComboBox = new JComboBox<>();
    JLabel startTimeLabel = new JLabel("Select start time");
    startTimePanel.setLayout(new GridLayout(2, 1));
    startTimePanel.add(startTimeLabel);
    startTimePanel.add(startTimeComboBox);

    this.auditoriumComboBox = new JComboBox<>();
    JLabel auditoriumLabel = new JLabel("Select auditorium");
    auditoriumPanel.setLayout(new GridLayout(2, 1));
    auditoriumPanel.add(auditoriumLabel);
    auditoriumPanel.add(auditoriumComboBox);

    startTimePanel.setVisible(true);
    auditoriumPanel.setVisible(false);

    setPreferredSize(new Dimension(1000, 800));
  }

  @Override
  public void registerEvent() {
    startTimeComboBox.addItemListener(e -> {
      SwingUtilities.invokeLater(() -> {
        this.selectedStartTime = Timestamp.valueOf((String) startTimeComboBox.getSelectedItem());
        refreshAuditoriumComboBox();
        auditoriumPanel.setVisible(true);
      });
    });
    auditoriumComboBox.addItemListener(e -> {
      SwingUtilities.invokeLater(() -> {
        this.selectedAuditorium = auditoriumGroupById
            .get(Integer.valueOf((String) auditoriumComboBox.getSelectedItem()));
        this.selectedShowtime = showtimes.stream().filter(it -> it.getAuditoriumId() == selectedAuditorium.getId())
            .findAny().orElseThrow(NullPointerException::new);
        showSeatAuditoriumPanel();
      });
    });
    refreshStartTimeComboBox();
  }

  private void showSeatAuditoriumPanel() {
    Optional.ofNullable(seatAuditoriumPanel).ifPresent(this::remove);
    this.seatAuditoriumPanel = new SeatAuditoriumPanel(selectedShowtime.getId());
    add(seatAuditoriumPanel, defaultGbcBuilder.clone().weighty(1.).gridy(2).fill(GridBagConstraints.BOTH)
        .insets(new Insets(5, 5, 5, 5)).build());
    seatAuditoriumPanel.setVisible(true);
  }

  private void refreshAuditoriumComboBox() {
    List<Showtime> showtimes = showtimeGroupByStartTime.get(selectedStartTime);
    List<Auditorium> auditoriums = showtimes.stream().map(it -> it.getId()).map(it -> auditoriumGroupByShowtime.get(it))
        .collect(Collectors.toList());

    auditoriumComboBox.removeAllItems();
    for (Auditorium auditorium : auditoriums) {
      auditoriumComboBox.addItem(String.valueOf(auditorium.getId()));
    }
    if (selectedAuditorium != null) {
      auditoriumComboBox.setSelectedItem(selectedAuditorium);
    }
  }

  private void refreshStartTimeComboBox() {
    Timestamp now = Timestamp.from(Instant.now());
    startTimeComboBox.removeAllItems();
    showtimeGroupByStartTime.entrySet().stream().map(it -> it.getKey())
        .forEach(startTime -> {
          startTimeComboBox.addItem(startTime.toString());
        });
  }

  @Override
  public void refresh() throws Exception {
    getData();
    refreshUI();
  }

  @Override
  public void refreshUI() {
    refreshStartTimeComboBox();
    if (auditoriumPanel.isVisible()) {
      refreshAuditoriumComboBox();
    }
  }

  @Override
  public Object getModel() {
    return model;
  }

  @Override
  public void dispose() {
    super.dispose();
    parent.dispose();
  }

  @Override
  public void handleError(Exception e) {
    ErrorPopup.show(e);
  }
}
