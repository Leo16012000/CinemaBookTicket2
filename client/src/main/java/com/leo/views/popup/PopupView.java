package com.leo.views.popup;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface PopupView {
  JButton getBtnOK();

  JButton getBtnCancel();

  JLabel getLbTitle();

  void dispose();

  void setVisible(boolean b);

  void showError(String message);

  void showError(Exception e);

  void showMessage(String message);
}
