package com.leo;

import com.leo.controllers.ServiceRegistry;
import com.leo.utils.Convert;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;

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
            String payload = new String(buffer, 0, bytesRead);
            // Parse the payload to extract the service name and XML payload
            String[] parts = payload.split(":");
            String serviceName = parts[0];
            String xmlPayload = parts[1];
            System.out.println(serviceName + " " + xmlPayload);
//            // Parse the XML payload into a Document object
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(new InputSource(new StringReader(xmlPayload)));
            Convert convert= new Convert();
            LinkedHashMap object = convert.XMLToObject(xmlPayload);
            System.out.println("object : "+ object.toString());

            // Process the XML data as needed
            ServiceRegistry serviceRegistry = new ServiceRegistry();
            String response = serviceRegistry.handleRequest(serviceName, object);
            OutputStream os = socket.getOutputStream();
            os.write(response.getBytes());
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


