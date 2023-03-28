package com.leo;

import com.leo.controllers.LoginController;
import com.leo.views.LoginView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Socket;

public class Client {
  private static final Logger logger = LogManager.getLogger(Client.class);
  private static Socket socket;
  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
      System.out.println("Khởi tạo look and feel thành công!");
      new LoginController(new LoginView());
      socket = new Socket("localhost", 8080);
      System.out.println("Connected to server");
//      Runtime.getRuntime().addShutdownHook(new Thread());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Socket getSocket() {
    return socket;
  }
  public static Logger getLogger() {
    return logger;
  }
}
