package com.leo.views;

import com.leo.models.Movie;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MoviePanel extends JPanel {
  private JTable movieTable = new JTable();
  private JTextField textField = new JTextField();
  private JButton btnSearch = new JButton("Search");
  private DefaultTableModel model = new DefaultTableModel();

  public DefaultTableModel getModel() {
    return model;
  }

  /**
   * Create the panel.
   */
  public MoviePanel(ArrayList<Movie> movies, JPanel contentPane) {
    this.updatePanel(movies, contentPane);
  }

  public void updatePanel(ArrayList<Movie> movies, JPanel contentPane) {
    model = new DefaultTableModel();
    // Add columns to the table
    model.addColumn("Id");
    model.addColumn("Title");
    model.addColumn("Country");
    model.addColumn("Duration time");
    model.addColumn("Ticket price");
    // Add rows to the table
    for (Movie obj : movies) {
      String[] row = { Integer.toString(obj.getId()), obj.getTitle(), obj.getCountry(),
          Integer.toString(obj.getDurationTime()) + "phut", Integer.toString(obj.getPrice()) + "VND" };
      model.addRow(row);
    }
    // movieTable.setPreferredSize(new Dimension(1400, 600));
    movieTable.setBounds(30, 212, 375, 112);
    // movieTable.setModel(new DefaultTableModel(
    // new Object[][] {
    // {1, 2, 3, null, null},
    // {null, null, null, null, null},
    // {null, null, null, null, null},
    // {null, null, null, null, null},
    // {null, null, null, null, null},
    // {null, null, null, null, null},
    // {null, null, null, null, null},
    // },
    // new String[] {
    // "New column", "New column", "New column", "New column", "New column"
    // }
    // ));
    movieTable.setModel(model);
    movieTable.getColumnModel().getColumn(1).setPreferredWidth(360);
    movieTable.getColumnModel().getColumn(2).setPreferredWidth(360);
    movieTable.getColumnModel().getColumn(3).setPreferredWidth(360);
    movieTable.getColumnModel().getColumn(4).setPreferredWidth(360);
    movieTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    // // Set the custom cell editor for the "Gender" column
    // String[] genders = {"Male", "Female", "Other"};
    // JComboBox<String> genderComboBox = new JComboBox<>(genders);
    // TableColumn genderColumn = movieTable.getColumnModel().getColumn(5);
    // genderColumn.setCellEditor(new DefaultCellEditor(genderComboBox));

    // Add the JTable to a JScrollPane
    // Add the JTable to a JScrollPane with horizontal scrolling
    JScrollPane scrollPane = new JScrollPane(movieTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    contentPane.add(scrollPane);

    // JScrollPane scrollPane = new JScrollPane(movieTable);
    this.add(movieTable);
    textField.setBounds(98, 125, 114, 19);
    this.add(textField);
    textField.setColumns(10);

    getBtnSearch().setBounds(217, 122, 117, 25);
    this.add(getBtnSearch());
  }

  public JButton getBtnSearch() {
    return btnSearch;
  }

  public void setBtnSearch(JButton btnSearch) {
    this.btnSearch = btnSearch;
  }

  public JTextField getTextField() {
    return textField;
  }

  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

  public JTable getMovieTable() {
    return movieTable;
  }
}
