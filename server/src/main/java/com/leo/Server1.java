package com.leo;

// Server class
//public class Server {
//    public static void main(String[] args) {
//        try (ServerSocket serverSocket = new ServerSocket(8080)) {
//            System.out.println("Server started");
//
//            while (true) {
//                Socket socket = serverSocket.accept();
//                System.out.println("Client connected " + socket.getLocalAddress() + " " + socket.getPort());
//
//                // Start a new thread to handle the client connection
//                Thread thread = new Thread(() -> {
//                    try {
//                        ServiceRegistry serviceRegistry = new ServiceRegistry();
//                        // Read the payload from the client
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        String payload = reader.readLine();
//
//                        // Parse the payload to extract the service name, request type, and XML payload
//                        String[] parts = payload.split(":");
//                        String serviceName = parts[0];
//                        String xmlPayload = parts[2];
//
//                        // Handle the request using the appropriate service and request type
//                        String response = serviceRegistry.handleRequest(serviceName, xmlPayload);
//                        System.out.println(payload);
//                        // Send the response back to the client
//                        OutputStream os = socket.getOutputStream();
//                        os.write(response.getBytes());
//                        os.flush();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            socket.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                thread.start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8080);
      System.out.println("Server started");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        // Read the payload from the client
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("run into here");
        String payload = reader.readLine();

        // Parse the payload to extract the service name, request type, and XML payload
        String[] parts = payload.split(":");
        String serviceName = parts[0];
        String requestType = parts[1];
        String xmlPayload = parts[2];

        System.out.println("print parts: " + parts);
        // // Get the appropriate controller for the service
        // ServiceController controller = ControllerRegistry.getController(serviceName);
        //
        // // Handle the request using the appropriate service and request type
        // String response = controller.handleRequest(requestType, xmlPayload);
        //
        // // Send the response back to the client
        // OutputStream os = socket.getOutputStream();
        // os.write(response.getBytes());
        // os.flush();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
