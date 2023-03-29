package com.leo;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leo.controllers.ServiceRegistry;
import com.leo.dtos.ResponseDto;
import com.leo.utils.LoadConfig;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
  private static LoadConfig config = LoadConfig.getInstance();

  public static void main(String[] args) throws IOException {
    // change the port as per your requirement
    try (ServerSocket serverSocket = new ServerSocket(Integer.valueOf(config.getProperty("server.port")))) {
      System.out.println("Server started");
      while (true) {
        // Accept incoming connections
        Socket socket = serverSocket.accept();
        System.out.println("Client connected " + socket.getLocalAddress() + " " + socket.getPort());
        Thread thread = new Thread(() -> {
          try {
            // Receive the XML payload from the client
            DataInputStream is = new DataInputStream(socket.getInputStream());
            String request = is.readUTF();

            // Process the XML data as needed
            ServiceRegistry serviceRegistry = new ServiceRegistry();
            ResponseDto<?> responseDto = serviceRegistry.handleRequest(request);
            // Send the payload to the server
            XmlMapper xmlMapper = new XmlMapper();
            String response = xmlMapper.writeValueAsString(responseDto);
            OutputStream os = socket.getOutputStream();
            os.write(response.getBytes());
            os.flush();
          } catch (IOException | SQLException e) {
            e.printStackTrace();
          }
        });
        thread.start();
      }
    }
  }
}
