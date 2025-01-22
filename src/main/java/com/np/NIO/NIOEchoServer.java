package com.np.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOEchoServer {
    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            // Create a Selector
            Selector selector = Selector.open();

            // Create a ServerSocketChannel
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.socket().bind(new InetSocketAddress(PORT));

            // Configure the channel to non-blocking mode
            serverSocket.configureBlocking(false);

            // Register the ServerSocketChannel with the Selector
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Echo Server started on port " + PORT);

            while (true) {
                // Wait for events
                selector.select();

                // Get the selected keys
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    // Handle different types of events
                    if (key.isAcceptable()) {
                        // Accept new connection
                        acceptConnection(selector, serverSocket);
                    } else if (key.isReadable()) {
                        // Read data from client
                        readData(key);
                    }

                    // Remove the key to prevent processing it again
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void acceptConnection(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        // Accept the client connection
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);

        // Register the client socket channel for reading
        client.register(selector, SelectionKey.OP_READ);
        System.out.println("New client connected: " + client);
    }

    private static void readData(SelectionKey key) throws IOException {
        // Get the channel from the key
        SocketChannel client = (SocketChannel) key.channel();

        // Create a buffer for reading
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        // Read data from the channel
        int bytesRead = client.read(buffer);

        if (bytesRead == -1) {
            // Client closed connection
            client.close();
            System.out.println("Client disconnected");
            return;
        }

        if (bytesRead > 0) {
            // Prepare buffer for writing
            buffer.flip();

            // Echo the data back to the client
            client.write(buffer);

            // Clear the buffer for next read
            buffer.clear();
        }
    }
}