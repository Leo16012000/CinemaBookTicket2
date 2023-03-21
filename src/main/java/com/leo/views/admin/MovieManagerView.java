package com.leo.views.admin;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.apache.commons.lang3.StringUtils;

import com.leo.controllers.admin.MovieManagerController;
import com.leo.models.Movie;
import com.leo.views.IView;

public class MovieManagerView extends ManagerPaneView<Movie> implements IView {
  private String[] list = { "id", "title", "country", "duration_time", "ticket_price" };
  private MovieManagerController controller;

  public MovieManagerView() {
    super();
    setTableModel();
    renderTable();
    this.controller = new MovieManagerController(this);
  }

  @Override
  public void setTableModel() {
    tableModel.addColumn("ID");
    tableModel.addColumn("Title");
    tableModel.addColumn("Country");
    tableModel.addColumn("Duration time");
    tableModel.addColumn("Ticket price");
    tableModel.addColumn("Created at");
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
    List<Movie> movies = controller.search(
      getCboSearchField().getSelectedItem().toString(), String.valueOf(getTxtSearch().getText()));
    setTableData(movies);
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
