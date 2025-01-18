package com.np.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8080);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String message;
        while (true) {
            System.out.print("Enter message for server (type 'exit' to quit): ");
            message = userInput.readLine();
            out.println(message);
            if (message.equalsIgnoreCase("exit")) {
                break;
            }
            String serverResponse = in.readLine();
            System.out.println("Server says: " + serverResponse);
        }

        socket.close();
    }
}