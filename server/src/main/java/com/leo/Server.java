package com.leo;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leo.controllers.ServiceRegistry;
import com.leo.dtos.ResponseDto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080); // change the port as per your requirement
            System.out.println("Server started");

            // Accept incoming connections
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Receive the XML payload from the client
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String request = new String(buffer, 0, bytesRead);
            // Parse the payload to extract the service name and XML payload
//            LinkedHashMap object = convert.XMLToObject(request);
//            System.out.println("object : "+ object.toString());

            // Process the XML data as needed
            ServiceRegistry serviceRegistry = new ServiceRegistry();
            ResponseDto responseDto = serviceRegistry.handleRequest(request);
            // Send the payload to the server
            XmlMapper xmlMapper = new XmlMapper();
            String response = xmlMapper.writeValueAsString(responseDto);
            OutputStream os = socket.getOutputStream();
            os.write(response.getBytes());
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


