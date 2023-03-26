package com.leo.views.admin;

import javax.swing.DefaultComboBoxModel;

import com.leo.models.Auditorium;

public class AuditoriumManagerView extends ManagerPaneView<Auditorium> {
  String[] list = { "id", "auditorium_num", "seats_row_num", "seats_column_num" };

  public AuditoriumManagerView() {
    super();
    setTableModel();
    renderTable();
  }

  @Override
  public void setTableModel() {
    tableModel.addColumn("ID");
    tableModel.addColumn("Number");
    tableModel.addColumn("Number of rows");
    tableModel.addColumn("Number of columns");
    tableModel.addColumn("Created at");
    this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
  }
}
