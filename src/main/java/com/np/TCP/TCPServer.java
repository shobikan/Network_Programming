package com.np.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server listening on port 8080");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String message;
        while (true) {
            message = in.readLine();
            if (message == null || message.equalsIgnoreCase("exit")) {
                System.out.println("Client disconnected");
                break;
            }
            System.out.println("Client says: " + message);
            out.println("Server received: " + message); // Reply to client
        }

        clientSocket.close();
        serverSocket.close();
    }
}
