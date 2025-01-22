package com.np.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MulticastSender_2 {
    public static void main(String[] args) {
        String multicastGroup = "230.0.0.0";
        int port = 4446;
        String message = "Hello, multicast group!";

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable sendTask = () -> {
            try (MulticastSocket socket = new MulticastSocket()) {
                InetAddress group = InetAddress.getByName(multicastGroup);
                byte[] buffer = message.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
                socket.send(packet);

                System.out.println("Message sent to multicast group: " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        scheduler.scheduleAtFixedRate(sendTask, 0, 1, TimeUnit.SECONDS);
    }
}