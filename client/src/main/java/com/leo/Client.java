package com.leo;

import com.leo.controllers.LoginController;
import com.leo.utils.Sockets;
import com.leo.views.LoginView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
  private static final Logger logger = LogManager.getLogger(Client.class);

  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
      logger.info("Khởi tạo look and feel thành công!");
      new LoginController(new LoginView());
      Sockets.getSocket();
      logger.info("Connected to server");
      // Runtime.getRuntime().addShutdownHook(new Thread());
    } catch (Exception e) {
      logger.error(e);
    }
  }
}
