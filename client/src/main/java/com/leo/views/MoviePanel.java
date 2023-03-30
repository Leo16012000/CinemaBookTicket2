package com.leo.views;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.StringUtils;

import com.leo.controllers.MoviePanelController;
import com.leo.models.Movie;
import com.leo.models.MoviePanelModel;
import com.leo.utils.ErrorPopup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoviePanel extends JPanel implements IView<MoviePanelModel> {
  private JTable movieTable;
  private JTextField searchTextField;
  private JButton btnSearch;
  private MoviePanelModel model;
  private JComboBox<String> searchKeywordComboBox;
  private MoviePanelController controller;
  private List<Movie> movies;
  private TableButtonEditor bookingBtnEditor;
  private MovieBookingFrame movieBookingFrame;

  public MoviePanelModel getModel() {
    return model;
  }

  public MoviePanel(MoviePanelController controller) {
    super();
    try {
      this.controller = controller;
      init();
      initUI();
      registerEvent();
    } catch (Exception e) {
      handleError(e);
    }
  }

  public MoviePanel() {
    this(new MoviePanelController());
  }

  public String getSearchTerm() {
    return searchTextField.getText().trim();
  }

  @Override
  public void registerEvent() {
    btnSearch.addActionListener(this::doSearchMovie);
    searchTextField.addActionListener(this::doSearchMovie);
    bookingBtnEditor.addActionListener(e -> {
      try {
        JButton button = (JButton) e.getSource();
        Integer row = (Integer) button.getClientProperty("row");
        JTable table = (JTable) button.getClientProperty("table");
        Integer movieId = Integer.valueOf((String) table.getValueAt(row, 0));

        Optional.ofNullable(movieBookingFrame).ifPresent(it -> it.dispose());
        this.movieBookingFrame = new MovieBookingFrame(SwingUtilities.getWindowAncestor(this), movieId);
        movieBookingFrame.setLocationRelativeTo(this);
        movieBookingFrame.setVisible(true);
        movieBookingFrame.pack();
      } catch (Exception ex) {
        handleError(ex);
      }
    });
  }

  public void doSearchMovie(ActionEvent evt) {
    try {
      String keyword = ((String) searchKeywordComboBox.getSelectedItem()).trim();
      String term = getSearchTerm();
      this.movies = controller.searchMovie(keyword, term);
      refreshUI();
    } catch (IOException e) {
      handleError(e);
    }
  }

  @Override
  public void init() throws IOException {
    this.movies = controller.getAllMovies();
  }

  @Override
  public void initUI() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    JPanel searchPanel = new JPanel();
    searchTextField = new JTextField();
    searchPanel.add(searchTextField);
    searchTextField.setColumns(20);
    this.searchKeywordComboBox = new JComboBox<>();
    String[] searchKeywords = new String[] { "Title", "Country", "Duration time", "Ticket price" };
    for (String keyword : searchKeywords) {
      searchKeywordComboBox.addItem(keyword);
    }
    searchPanel.add(searchKeywordComboBox);
    btnSearch = new JButton("Search");
    searchPanel.add(btnSearch);
    add(searchPanel);

    model = new MoviePanelModel();
    // Add columns to the table
    model.addColumn("Id");
    model.addColumn("Title");
    model.addColumn("Country");
    model.addColumn("Duration time");
    model.addColumn("Ticket price");
    model.addColumn("Booking");

    // Add rows to the table
    for (Movie movie : movies) {
      model.addRow(getRowData(movie));
    }
    movieTable = new JTable();
    movieTable.setBounds(30, 212, 375, 112);
    movieTable.setModel(model);
    movieTable.getColumn("Booking").setCellRenderer(new TableButtonRenderer());
    this.bookingBtnEditor = new TableButtonEditor(new JCheckBox());
    movieTable.getColumn("Booking").setCellEditor(bookingBtnEditor);

    // Add the JTable to a JScrollPane
    // Add the JTable to a JScrollPane with horizontal scrolling
    JScrollPane scrollPane = new JScrollPane(movieTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    add(scrollPane);
  }

  @Override
  public void refresh() throws Exception {
    String term = getSearchTerm();
    if (StringUtils.isBlank(term)) {
      this.movies = controller.getAllMovies();
    }
    refreshUI();
  }

  @Override
  public void refreshUI() {
    DefaultTableModel model = (DefaultTableModel) movieTable.getModel();
    for (int i = model.getRowCount() - 1; i >= 0; i--) {
      model.removeRow(i);
    }
    for (Movie movie : movies) {
      model.addRow(getRowData(movie));
    }
  }

  @Override
  public void handleError(Exception e) {
    ErrorPopup.show(e);
  }

  private String[] getRowData(Movie movie) {
    return new String[] { Integer.toString(movie.getId()), movie.getTitle(), movie.getCountry(),
        Integer.toString(movie.getDurationTime()) + "phut", Integer.toString(movie.getPrice()) + "VND", "Reserve" };

  }
}
