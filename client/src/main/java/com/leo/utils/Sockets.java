package com.leo.utils;

import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sockets {
  private static Socket socket;
  private static Logger logger = LogManager.getLogger(Sockets.class);

  public static Socket getSocket() {
    try {
      if (socket == null) {
        synchronized (Socket.class) {
          LoadConfig loadConfig = LoadConfig.getInstance();
          if (socket == null) {
            socket = new Socket(loadConfig.getProperty("server.host"),
                Integer.valueOf(loadConfig.getProperty("server.port")));
          }
        }
      }
      return socket;
    } catch (Exception e) {
      logger.fatal(e);
      System.exit(1);
      return null;
    }
  }
}
