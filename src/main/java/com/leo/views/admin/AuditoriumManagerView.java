package com.leo.views.admin;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.apache.commons.lang3.StringUtils;

import com.leo.controllers.admin.AuditoriumManagerController;
import com.leo.models.Auditorium;
import com.leo.views.IView;

public class AuditoriumManagerView extends ManagerPaneView<Auditorium> implements IView {
  private AuditoriumManagerController controller;
  private String[] list = { "id", "auditorium_num", "seats_row_num", "seats_column_num" };

  public AuditoriumManagerView() {
    super();
    setTableModel();
    renderTable();
    this.controller = new AuditoriumManagerController(this);
  }

  @Override
  public void setTableModel() {
    tableModel.addColumn("id");
    tableModel.addColumn("Number");
    tableModel.addColumn("Number of rows");
    tableModel.addColumn("Number of columns");
    tableModel.addColumn("Created at");
    this.getCboSearchField().setModel(new DefaultComboBoxModel<String>(list));
  }

  @Override
  public void refresh() {
    String key = getCboSearchField().getSelectedItem().toString();
    String word = getTxtSearch().getText();
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
    List<Auditorium> auditoriums = controller.search(
      getCboSearchField().getSelectedItem().toString(), String.valueOf(getTxtSearch().getText()));
    setTableData(auditoriums);
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
