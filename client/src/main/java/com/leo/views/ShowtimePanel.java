package com.leo.views;

import com.leo.models.Showtime;

import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ShowtimePanel extends JPanel {
  private JTable showtimeTable = new JTable();
  private DefaultTableModel model = new DefaultTableModel();

  /**
   * Create the panel.
   */
  public ShowtimePanel(List<Showtime> showtimes) {
    model = new DefaultTableModel();
    // Add columns to the table
    model.addColumn("Id");
    model.addColumn("Start time");
    model.addColumn("End time");
    model.addColumn("Screen");
    // Add rows to the table
    for (Showtime obj : showtimes) {
      String[] row = { Integer.toString(obj.getId()), obj.getStartTime().toString(), obj.getEndTime().toString() };
      model.addRow(row);
    }
    showtimeTable.setBounds(30, 212, 375, 112);
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
    showtimeTable.setModel(model);
    showtimeTable.getColumnModel().getColumn(1).setPreferredWidth(360);
    showtimeTable.getColumnModel().getColumn(2).setPreferredWidth(360);
    showtimeTable.getColumnModel().getColumn(3).setPreferredWidth(360);
    showtimeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    // Set the custom cell editor for the "Gender" column
    String[] genders = { "Screen1", "Screen2", "Screen3" };
    JComboBox<String> genderComboBox = new JComboBox<>(genders);
    TableColumn genderColumn = showtimeTable.getColumnModel().getColumn(3);
    genderColumn.setCellEditor(new DefaultCellEditor(genderComboBox));
    // Add a MouseListener to the JTable

    // Add the JTable to a JScrollPane
    JScrollPane scrollPane = new JScrollPane(showtimeTable);
    this.add(showtimeTable);
  }
}
