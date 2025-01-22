package com.np.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
    public static void main(String[] args) {
        String multicastGroup = "230.0.0.0";
        int port = 4446;
        String message = "Hello, multicast group!";

        try (MulticastSocket socket = new MulticastSocket()) {
            InetAddress group = InetAddress.getByName(multicastGroup);
            byte[] buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
            socket.send(packet);

            System.out.println("Message sent to multicast group: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}