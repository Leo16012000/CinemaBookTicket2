package com.leo.views.admin;

import com.leo.controllers.admin.ShowtimeManagerController;
import com.leo.models.Showtime;
import com.leo.views.IView;

import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;

public class ShowtimeManagerView extends ManagerPaneView<Showtime>
    implements IView {
  private String[] list = { "start_time", "end_time", "auditorium_num", "title" };
  private ShowtimeManagerController controller;

  public ShowtimeManagerView() {
    super();
    setTableModel();
    renderTable();
    this.controller = new ShowtimeManagerController(this);
  }

  @Override
  public void setTableModel() {
    tableModel.addColumn("ID");
    tableModel.addColumn("Start time");
    tableModel.addColumn("End time");
    tableModel.addColumn("Auditorium number");
    tableModel.addColumn("Movie name");
    this.getCboSearchField().setModel(new DefaultComboBoxModel<String>(list));
  }

  @Override
  public void refresh() {
    String key = getCboSearchField().getSelectedItem().toString();
    String word = String.valueOf(getTxtSearch().getText());
    if (StringUtils.isNotBlank(word) && StringUtils.isNotBlank(key)) {
      setTableData(controller.search(key.trim(), word.trim()));
    } else {
      setTableData(controller.getAllData());
    }
  }

  @Override
  public void init() {
    /** noop */
  }

  @Override
  public void actionAdd() throws SQLException {
    controller.actionAdd();
  }

  @Override
  public void actionSearch() {
    List<Showtime> showtimes = controller.search(
      getCboSearchField().getSelectedItem().toString(), String.valueOf(getTxtSearch().getText()));
    setTableData(showtimes);
  }

  @Override
  public void actionDelete() {
    controller.actionDelete();
  }

  @Override
  public void actionEdit() {
    controller.actionEdit();
  }

  @Override
  public void updateData() {
    refresh();
  }
}
