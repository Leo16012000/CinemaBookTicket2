package com.leo.models;

import javax.swing.table.DefaultTableModel;

public class MoviePanelModel extends DefaultTableModel {
  @Override
  public boolean isCellEditable(int row, int column) {
    String columnName = getColumnName(column);
    if (columnName.equalsIgnoreCase("Booking")) {
      return true;
    }
    return false;
  }
}
