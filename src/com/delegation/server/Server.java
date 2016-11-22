package com.delegation.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    String[] clients;
    String[] tasks;
    DatagramSocket socket;
    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        clients = new String[]{"localhost"};
        tasks = new String[]{"1","2","3","4","5","6"};
        try {
            socket = new DatagramSocket(4040);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Receiver receiver = new Receiver(tasks.length,socket);
        receiver.start();
        for(int i = 0; i< tasks.length;i++) {
            try {
                String message = i+":"+tasks[i];
                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(clients[i % clients.length]),4041);
                System.out.println("Sent: "+ message);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
