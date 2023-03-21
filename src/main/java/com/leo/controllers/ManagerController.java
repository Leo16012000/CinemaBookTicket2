package com.leo.controllers;

import com.leo.models.Model;
import com.leo.views.admin.ManagerPaneView;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dang Tuan Anh
 */
// Form chung cho các panel kế thừa
public abstract class ManagerController<T extends Model, K extends ManagerPaneView<T>> {

  protected K view = null;

  public ManagerController(K view) {
    this.view = view;
  }

  public K getView() {
    return view;
  }

  public abstract void actionAdd() throws SQLException;

  public abstract List<T> search(String key, String word);

  public abstract void actionDelete();

  public abstract void actionEdit();

  public abstract List<T> getAllData();

}
