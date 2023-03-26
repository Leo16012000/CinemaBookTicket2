package com.leo.views.admin;

import javax.swing.DefaultComboBoxModel;

import com.leo.models.User;

public class MovieManagerView extends ManagerPaneView<User> {
  String[] list = { "id", "title", "country", "duration_time", "ticket_price" };

  public MovieManagerView() {
    super();
    setTableModel();
    renderTable();
  }

  @Override
  public void setTableModel() {
    tableModel.addColumn("ID");
    tableModel.addColumn("Title");
    tableModel.addColumn("Country");
    tableModel.addColumn("Duration time");
    tableModel.addColumn("Ticket price");
    tableModel.addColumn("Created at");
    this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
  }
}
