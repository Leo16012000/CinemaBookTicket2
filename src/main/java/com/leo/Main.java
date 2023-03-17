package com.leo;

import com.leo.controllers.LoginController;
import com.leo.views.LoginView;

public class Main {
  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
      System.out.println("Khởi tạo look and feel thành công!");
      new LoginController(new LoginView());
      Runtime.getRuntime().addShutdownHook(new Thread());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
