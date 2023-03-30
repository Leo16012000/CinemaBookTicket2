package com.leo.utils;

import com.leo.dtos.ResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Sockets {
  private static Socket socket;
  private static final int RETRY_INTERVAL = 2000; // 5 seconds
  private static final Logger logger = LogManager.getLogger(Sockets.class);
  private static ServiceHandler serviceHandler = ServiceHandler.getInstance();
  private static Thread pingThread;

  public static Socket getSocket() throws IOException {
    if (socket == null) {
      synchronized (Sockets.class) {
        if (socket == null) {
          socket = new Socket();
        }
      }
    }
    return socket;
  }

  public static void init() {
    if (pingThread != null) {
      return;
    }
    synchronized(Sockets.class) {
      if (pingThread == null) {
       pingThread = new Thread(() -> {
        boolean isConnected = false;
        int count = 0;
        while (true) {
          while (!isConnected) {
            try {
              socket = new Socket();
              socket.connect(new InetSocketAddress(LoadConfigs.getInstance().getProperty("server.host"),
              Integer.parseInt(LoadConfigs.getInstance().getProperty("server.port"))), 10000);
              isConnected = true;
              count = 0;
            } catch (IOException e) {
              count++;
              logger.info("Failed to connect to server after: " + count + " times");
              JOptionPane.showInternalMessageDialog(null, "Server is disconnected, try again...");
            }
          }
          try {
            while(true) {
              logger.info("Sent: " + "PING");
              ResponseDto<String> response = serviceHandler.sendRequest(socket, "PING", null,
                  new TypeReference<ResponseDto<String>>() {
                  });
              logger.info("Received: " + response.getPayload());
              Thread.sleep(RETRY_INTERVAL);
            }
          } catch (IOException e) {
            if (!socket.isClosed()) {
              try {
                socket.close();
              } catch (IOException e1) {
                logger.error(e1);
                throw new RuntimeException(e1);
              }
            }
            logger.error(e);
            JOptionPane.showInternalMessageDialog(null, "Server is disconnected, try again...");
          } catch (InterruptedException e) {
            logger.error(e);
          }
        }
      });
      pingThread.start();
    }}
  }
}
