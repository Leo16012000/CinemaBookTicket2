package com.leo.views.popup;

import java.util.function.Consumer;

import javax.swing.JFrame;

import com.leo.dao.Transaction.TxFunction;

public abstract class AbstractCrudPopupView<T, K> extends JFrame
    implements ICrudPopup<T> {
  protected K controller;
  protected T model;
  protected T editingModel;
  protected boolean isInit = false;
  protected Consumer<Exception> errorHandler;
  protected Consumer<T> confirmHandler;
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
  public void show() {
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

  public void confirm(TxFunction<T, T> saveFn, Consumer<ThreadGroup> successHandler, Consumer<Exception> errorHandler) {
    try {
      T updatedModel = saveFn.apply(editingModel);
      if (confirmHandler != null) {
        confirmHandler.accept(updatedModel);
      }
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

  @Override
  public void registerErrorHandler(Consumer<Exception> errorHandler) {
    this.errorHandler = errorHandler;
  }

  @Override
  public void registerConfirmHandler(Consumer<T> confirmHandler) {
    this.confirmHandler = confirmHandler;
  }

  @Override
  public void registerCancelHandler(Runnable cancelHandler) {
    this.cancelHandler = cancelHandler;
  }
}
