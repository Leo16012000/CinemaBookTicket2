package com.leo.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Response {
    private String serviceName;
    private String xmlPayload;
    private Socket socket;

    public Response(Socket socket) {
        this.socket = socket;
    }

    public void sendResponse(String response) throws IOException {
        // Send the response back to the client
        OutputStream os = socket.getOutputStream();
        os.write(response.getBytes());
        os.flush();
    }

    public String toString(){
        return serviceName + ":" + xmlPayload;
    }
}
