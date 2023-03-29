package com.leo.views;

import com.leo.models.Movie;
import com.leo.service.IMovieService;
import com.leo.service.impl.MovieService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MoviesPanel extends JPanel {
  private IMovieService movieService = MovieService.getInstance();
  public List<Movie> movies;
  private JTextField textField;
  private JTable table_1;

  public MoviesPanel() throws IOException {
    // Create a JTable with a DefaultTableModel
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    // Add columns to the table
    model.addColumn("Title");
    model.addColumn("Country");
    model.addColumn("Duration time");
    model.addColumn("Ticket price");

    this.movies = movieService.getAll();

    // Add rows to the table
    for (Movie obj : movies) {
      String[] row = { obj.getTitle(), obj.getCountry(), Integer.toString(obj.getDurationTime()),
          Integer.toString(obj.getPrice()) };
      model.addRow(row);
    }

    JButton btnSearch = new JButton("Search");
    btnSearch.setBounds(218, 28, 117, 25);
    add(btnSearch);

    textField = new JTextField();
    textField.setBounds(68, 31, 114, 19);
    add(textField);
    textField.setColumns(10);

    table.setBounds(31, 73, 328, 204);
    add(table);
    setVisible(true);
  }

  // public void ArrayListTableExample(ArrayList<Movie> movies) {
  //
  // // Create a JTable with a DefaultTableModel
  // DefaultTableModel model = new DefaultTableModel();
  // JTable table = new JTable(model);
  //
  // // Add columns to the table
  // model.addColumn("Title");
  // model.addColumn("Country");
  // model.addColumn("Duration time");
  // model.addColumn("Ticket price");
  //
  // // Add rows to the table
  // for (Movie obj : movies) {
  // String[] row = {obj.getTitle(), obj.getCountry(),
  // Integer.toString(obj.getDurationTime()), Integer.toString(obj.getPrice())};
  // model.addRow(row);
  // }
  //
  // // Set the size of the JFrame and make it visible
  // setSize(400, 300);
  // setVisible(true);
  // }
}