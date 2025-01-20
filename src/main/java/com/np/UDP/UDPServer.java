package com.np.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8080);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Receive packet from client
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received from client: " + received);

            // Simulate processing delay (3 seconds)
            System.out.println("Processing... (3 second delay)");
            Thread.sleep(3000);

            // Send response to client
            String response = "Hello from server";
            byte[] responseData = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);
            System.out.println("Sent response to client");

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
