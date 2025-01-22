package com.np.Network_Sockets;

import java.util.Scanner;

public class main {
    static final int PORT_NUMBER = 8080;

    public static void main(String[] args) {

        try(var scanner = new Scanner(System.in)) {
            System.out.println("Is this server? (Y/N) : ");

            if(scanner.next().toLowerCase().equals("y")) {
                new MyServer().start(PORT_NUMBER);
            } else {
                System.out.println("Client todo");
            }
        }
    }
}