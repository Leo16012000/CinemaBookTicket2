package com.leo.views.admin;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.apache.commons.lang3.StringUtils;

import com.leo.controllers.admin.UserManagerController;
import com.leo.models.User;
import com.leo.views.IView;

public class UserManagerView extends ManagerPaneView<User> implements IView {
  private String[] list = { "id", "name" };
  private UserManagerController controller;

  public UserManagerView() {
    super();
    setTableModel();
    renderTable();
    this.controller = new UserManagerController(this);
  }

  @Override
  public void setTableModel() {
    tableModel.addColumn("ID");
    tableModel.addColumn("Full name");
    tableModel.addColumn("Username");
    tableModel.addColumn("Permission");
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
    List<User> users = controller.search(
      getCboSearchField().getSelectedItem().toString(), String.valueOf(getTxtSearch().getText()));
    setTableData(users);
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
