package com.leo;

import javax.swing.UIManager;

import com.leo.views.LoginView;

public class Main {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
      System.out.println("Khởi tạo look and feel thành công!");
      LoginView view = new LoginView();
      view.setVisible(true);
      Runtime.getRuntime().addShutdownHook(new Thread());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
