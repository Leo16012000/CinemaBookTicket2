package com.leo.views.admin;

import com.leo.models.Auditorium;
import com.leo.models.Showtime;

import javax.swing.*;

public class ShowtimeManagerView extends ManagerPaneView<Showtime> {
  String[] list = {"start_time", "end_time", "auditorium_num", "title" };

  public ShowtimeManagerView() {
    super();
    setTableModel();
    renderTable();
  }

  @Override
  public void setTableModel() {
    tableModel.addColumn("Start time");
    tableModel.addColumn("End time");
    tableModel.addColumn("Auditorium number");
    tableModel.addColumn("Movie name");
    this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
  }
}
