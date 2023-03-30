package com.leo;

import com.leo.controllers.LoginController;
import com.leo.utils.PingPongThread;
import com.leo.utils.Sockets;
import com.leo.views.LoginView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
  private static final Logger logger = LogManager.getLogger(Client.class);

  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
      System.out.println("Khởi tạo look and feel thành công!");
      new LoginController(new LoginView());
      Sockets.getSocket();
      System.out.println("Connected to server");

//      Object LOCK_OBJECT = new Object();
//      Thread ping = new Thread(new PingPongThread(LOCK_OBJECT, "Ping"));
//      Thread pong = new Thread(new PingPongThread(LOCK_OBJECT, "Pong"));
//      ping.start();
//      pong.start();

      // Runtime.getRuntime().addShutdownHook(new Thread());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
