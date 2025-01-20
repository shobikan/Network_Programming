package com.np.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

public class UDPClient {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(3000); // Set a timeout of 3 seconds for receiving a response
            String message = "Hello Server";
            byte[] buf = message.getBytes();

            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 8080);

            int attempts = 0;
            boolean receivedResponse = false;

            while (attempts < 3 && !receivedResponse) {
                try {
                    // Send packet to server
                    System.out.println("Sending message attempt " + (attempts + 1));
                    socket.send(packet);

                    // Prepare buffer to receive response from server
                    byte[] buffer = new byte[1024];
                    DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);

                    // Try to receive server's response (with timeout)
                    socket.receive(responsePacket);
                    String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                    System.out.println("Response from server: " + response);
                    receivedResponse = true; // If response is received, exit loop

                } catch (IOException e) {
                    attempts++;
                    System.out.println("No response, attempt " + attempts + " failed.");
                }
            }

            if (!receivedResponse) {
                System.out.println("Failed to communicate with the server after 3 attempts.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

