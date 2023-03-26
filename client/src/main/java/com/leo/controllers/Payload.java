package com.leo.controllers;

import com.leo.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Payload {
    private String serviceName;
    private String xmlPayload;
    private Socket socket;

    public Payload(String serviceName, String xmlPayload) {
        this.serviceName = serviceName;
        this.xmlPayload = xmlPayload;
    }

    public String sendPayload() throws IOException {
            socket = Client.getSocket();
            System.out.println("Connected to server");

            // Send the payload to the server
            OutputStream os = socket.getOutputStream();
            os.write(this.toString().getBytes());
            os.flush();

            // Read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            System.out.println("Response from server: " + response);
            return response;
    }

    public String toString(){
        return serviceName + ":" + xmlPayload;
    }
}
