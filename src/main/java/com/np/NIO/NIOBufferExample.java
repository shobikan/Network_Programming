package com.np.NIO;

import java.nio.ByteBuffer;

public class NIOBufferExample {
    public static void main(String[] args) {
        // Step 1: Create a ByteBuffer with a capacity of 32 bytes
        ByteBuffer buffer = ByteBuffer.allocate(32);

        System.out.println("Step 1: New buffer created");
        printBufferDetails(buffer);

        // Step 2: Write data into the buffer
        String data = "Hello, NIO!";
        buffer.put(data.getBytes()); // Writing bytes into the buffer

        System.out.println("\nStep 2: Data written into buffer");
        printBufferDetails(buffer);

        // Step 3: Flip the buffer to prepare for reading
        buffer.flip(); // Switch to reading mode
        System.out.println("\nStep 3: Buffer flipped for reading");
        printBufferDetails(buffer);

        // Step 4: Read data from the buffer
        byte[] readData = new byte[buffer.remaining()]; // Allocate a byte array to hold data
        buffer.get(readData); // Read data into the array
        System.out.println("\nStep 4: Data read from buffer");
        System.out.println("Data: " + new String(readData));
        printBufferDetails(buffer);

        // Step 5: Clear the buffer to reuse
        buffer.clear(); // Reset the buffer to write mode
        System.out.println("\nStep 5: Buffer cleared");
        printBufferDetails(buffer);
    }

    // method to print buffer details
    private static void printBufferDetails(ByteBuffer buffer) {
        System.out.println("Capacity: " + buffer.capacity());
        System.out.println("Position: " + buffer.position());
        System.out.println("Limit: " + buffer.limit());
    }
}
