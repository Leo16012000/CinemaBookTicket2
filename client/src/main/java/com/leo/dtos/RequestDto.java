package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.leo.Client;
import com.leo.utils.Convert;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;

public class RequestDto {
    private String serviceName;
    @JacksonXmlProperty(localName = "Payload")
    private Object payload;

    public String getServiceName() {
        return serviceName;
    }

    public Object getPayload() {
        return payload;
    }

    private Socket socket;

    private Convert convert = new Convert();

    public RequestDto(String serviceName, Object payload) {
        this.serviceName = serviceName;
        this.payload = payload;
    }

    public String sendRequest(JFrame view) throws IOException {
            socket = Client.getSocket();
            System.out.println("Connected to server");

            // Send the payload to the server
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(this);
            System.out.println(xml);
            OutputStream os = socket.getOutputStream();
            os.write(xml.getBytes());
            os.flush();

            // Read the response from the server
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String response = new String(buffer, 0, bytesRead);
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
}
