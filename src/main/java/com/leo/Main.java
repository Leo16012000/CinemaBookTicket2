package com.leo;

import com.leo.controllers.LoginController;
import com.leo.views.LoginView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
  private static final Logger logger = LogManager.getLogger(Main.class);
  public static void main(String[] args) {
//    logger.debug("Debug Message Logged !!!");
//    logger.info("Info Message Logged !!!");
//    logger.error("Error Message Logged !!!", new NullPointerException("NullError"));
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
