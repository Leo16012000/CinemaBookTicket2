package com.leo.views;

public interface IView<T> {
  void init() throws Exception;

  void initUI();

  void registerEvent();

  void refresh() throws Exception;

  void refreshUI();

  T getModel();

  void handleError(Exception e);
}
