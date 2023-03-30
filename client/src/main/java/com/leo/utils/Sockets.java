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

  public static Socket getSocket() throws IOException {
    if (socket == null) {
      synchronized (Socket.class) {
        if (socket == null) {
          int count = 0;
          while (true) {
            try {
              socket = new Socket();
              socket.connect(new InetSocketAddress(LoadConfigs.getInstance().getProperty("server.host"),
                  Integer.parseInt(LoadConfigs.getInstance().getProperty("server.port"))), 10000);
              Thread pingThread = new Thread(() -> {
                try {
                  while (true) {
                    logger.info("Sent: " + "PING");
                    ResponseDto<String> response = serviceHandler.sendRequest(socket, "PING", null,
                        new TypeReference<ResponseDto<String>>() {
                        });
                    logger.info("Received: " + response.getPayload());
                    if (response.getPayload().equals("PONG"))
                      Thread.sleep(3000);
                    else {
                      JOptionPane.showInternalMessageDialog(null, "Server is disconnected, try again...");
                      Thread.sleep(3000);
                      // if (socket.isClosed() == false) {
                      // socket.close();
                      // }
                      // socket = new Socket(LoadConfig.getProperty("server.host"),
                      // Integer.parseInt(LoadConfig.getProperty("server.port")));
                    }
                  }
                  // } catch (IOException | InterruptedException | ConnectException e) {
                } catch (Exception e) {
                  e.printStackTrace();
                }
              });
              pingThread.start();
              break;
            } catch (IOException e) {
              count++;
              logger.info("Failed to connect to server after: " + count + " times");
              JOptionPane.showInternalMessageDialog(null, "Server is disconnected, try again...");
            }
            try {
              Thread.sleep(RETRY_INTERVAL); // Wait for the specified interval before retrying
            } catch (InterruptedException e) {
              // Handle the exception if the thread is interrupted
              e.printStackTrace();
              break; // Exit the loop if the thread is interrupted
            }
          }
        }
      }
    }
    return socket;
  }
}
