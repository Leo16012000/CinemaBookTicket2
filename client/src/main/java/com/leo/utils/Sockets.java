package com.leo.utils;

import java.net.Socket;

import com.leo.component.LoadConfig;

public class Sockets {
  private static Socket socket;
  private static LoadConfig loadConfig = LoadConfig.getInstance();

  public static Socket getSocket() {
    try {
      if (socket == null) {
        synchronized (Socket.class) {
          if (socket == null) {
            socket = new Socket(loadConfig.getProperty("server.host"),
                Integer.valueOf(loadConfig.getProperty("server.port")));
          }
        }
      }
      return socket;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
