package com.leo.views.popup;

import java.sql.SQLException;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.leo.dao.Transaction.TxFunction;

public abstract class AbstractCrudPopupView<T, K> extends JFrame
    implements ICrudPopup<T> {
  protected K controller;
  protected T model;
  protected T editingModel;
  protected boolean isInit = false;
  protected Consumer<Exception> errorHandler;
  protected Consumer<T> successHandler;
  protected Runnable confirmHandler;
  protected Runnable cancelHandler;
  protected boolean isUpdating = false;

  protected AbstractCrudPopupView() {
    isUpdating = false;
    init();
    bindModel(null);
    setLocationRelativeTo(null);
  }

  protected AbstractCrudPopupView(T model) {
    isUpdating = true;
    init();
    bindModel(model);
    setLocationRelativeTo(null);
  }

  @Override
  public void popup() {
    if (isOpened()) {
      return;
    }
    refresh();
    setVisible(true);
  }

  @Override
  public void close() {
    setVisible(false);
    dispose();
    isInit = false;
  }

  @Override
  public void cancel() {
    if (cancelHandler != null) {
      cancelHandler.run();
    }
    close();
  }

  @Override
  public void confirm() {
    try {
      onConfirm();
      onSuccess();
      close();
    } catch (Exception e) {
      if (errorHandler != null) {
        errorHandler.accept(e);
      }
      return;
    }
  }

  @Override
  public boolean isOpened() {
    return isVisible();
  }

  protected abstract void addEvent();
  protected abstract void onSuccess();
  protected abstract void onError(Exception e);
  protected abstract void onConfirm() throws SQLException, Exception;
}
