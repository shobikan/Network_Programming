package com.np.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOEchoClient {
    public static void main(String[] args) {
        try {
            // Create a SocketChannel
            SocketChannel client = SocketChannel.open();
            client.connect(new InetSocketAddress("localhost", 8080));

            // Prepare message
            String message = "Hello, NIO Echo Server!";
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));

            // Write to server
            client.write(buffer);

            // Prepare to read response
            buffer.clear();
            buffer = ByteBuffer.allocate(1024);

            // Read response
            int bytesRead = client.read(buffer);
            buffer.flip();

            // Convert and print response
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            System.out.println("Server response: " + new String(bytes, StandardCharsets.UTF_8));

            // Close the channel
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}