package com.np.Network_Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;


public class MyServer {
    public void start(final int port) {
        //This approach uses the try-with-resources statement,
        // which ensures that the ServerSocket is automatically closed
        // when the try block is exited, whether due to normal execution or an exception.
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            var socket = serverSocket.accept();

            var clientIp = socket.getInetAddress().getHostAddress();
            var clientPort = socket.getPort();
            var clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Waiting for client...");
            for(String inputLine; (inputLine = clientInput.readLine()) != null; ) {
                System.out.println(clientIp + ":" + clientPort + " : " + inputLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}