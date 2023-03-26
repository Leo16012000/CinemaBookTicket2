package com.leo;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.StringReader;
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
            String xmlPayload = new String(buffer, 0, bytesRead);
            System.out.println(xmlPayload);
            // Parse the XML payload into a Document object
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlPayload)));

            // Process the XML data as needed
            // ...

            socket.close(); // close the socket when done
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


