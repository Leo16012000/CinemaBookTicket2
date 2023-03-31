package com.leo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leo.controllers.ServiceRegistry;
import com.leo.dtos.ResponseDto;
import com.leo.utils.LoadConfig;
import com.leo.utils.ObjectMappers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
  private static ObjectMapper xmlMapper = ObjectMappers.getInstance();
  private static ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();
  private static Logger logger = LogManager.getLogger(Server.class);
  private static final int TIMEOUT = 2000;

  public static void main(String[] args) throws IOException {
    LoadConfig config = LoadConfig.getInstance();
    // change the port as per your requirement
    try (ServerSocket serverSocket = new ServerSocket(Integer.valueOf(config.getProperty("server.port")))) {
      logger.debug("Server started");
      while (true) {
        // Accept incoming connections
        Socket socket = serverSocket.accept();
        socket.setSoTimeout(TIMEOUT);
        logger.debug("Client connected " + socket.getLocalAddress() + " " + socket.getPort());
        Thread thread = new Thread(() -> {
          while (true) {
            try {
              // Receive the XML payload from the client
              DataInputStream is = new DataInputStream(socket.getInputStream());
              if (is.available() <= 0) {
                continue;
              }
              String request = is.readUTF();

              // Process the XML data as needed
              ResponseDto<?> responseDto = serviceRegistry.handleRequest(request);
              // Send the payload to the server
              String response = xmlMapper.writeValueAsString(responseDto);
              DataOutputStream os = new DataOutputStream(socket.getOutputStream());
              os.writeUTF(response);
              os.flush();
            } catch (SocketTimeoutException e) {
              try {
                socket.close();
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
              logger.info("Closing iddle socket");
            } catch (SQLException e) {
              throw new RuntimeException(e);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          }
        });
        thread.start();
      }
    }
  }
}
