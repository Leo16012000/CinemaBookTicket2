package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.leo.Client;
import com.leo.utils.Convert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

    public ResponseDto sendRequest() throws IOException {
        socket = Client.getSocket();
        System.out.println("Connected to server");

        // Send the payload to the server
        XmlMapper xmlMapper = new XmlMapper();
        String request = xmlMapper.writeValueAsString(this);
        System.out.println(request);
        OutputStream os = socket.getOutputStream();
        os.write(request.getBytes());
        os.flush();

        // Read the response from the server
        InputStream is = socket.getInputStream();
        is = socket.getInputStream();
        byte[] buffer = new byte[1024 * 1024];
        int bytesRead = bytesRead = is.read(buffer);
        String response = new String(buffer, 0, bytesRead);
        ResponseDto responseDto
                = xmlMapper.readValue(response, ResponseDto.class);
        return responseDto;


    }
}
