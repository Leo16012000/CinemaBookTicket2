package com.leo.controllers;

import com.leo.Client;
import com.leo.utils.Convert;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;

public class Payload {
    private String serviceName;
    private String xmlPayload;
    private Socket socket;

    private Convert convert = new Convert();

    public Payload(String serviceName, String xmlPayload) {
        this.serviceName = serviceName;
        this.xmlPayload = xmlPayload;
    }

    public String sendPayload(JFrame view) throws IOException {
            socket = Client.getSocket();
            System.out.println("Connected to server");

            // Send the payload to the server
            OutputStream os = socket.getOutputStream();
            os.write(this.toString().getBytes());
            os.flush();

            // Read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            String[] parts = response.split(":");
            String status = parts[0];
            String xmlPayload = parts[1];
            LinkedHashMap object = convert.XMLToObject(xmlPayload);
            System.out.println(status + " " + object);
            if(status == "ERROR"){
                JOptionPane.showMessageDialog(null, "Error: " + object.get("message"));
            } else {
                view.dispose();
                JOptionPane.showMessageDialog(null, "Successfully");
            }
            return response;
    }

    public String toString(){
        return serviceName + ":" + xmlPayload;
    }
}
