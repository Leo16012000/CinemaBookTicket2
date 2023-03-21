package com.leo.views.popup;

import java.util.function.Consumer;

import com.leo.views.IView;

public interface ICrudPopup<T> extends IPopup, IView {
  void cancel();

  void confirm() throws Exception;

  void bindModel(T model);
}
