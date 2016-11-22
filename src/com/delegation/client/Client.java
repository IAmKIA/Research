package com.delegation.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Client {
    DatagramSocket socket;
    public static void main(String[] args) {
        new Client();
    }
    public Client() {
        try {
            socket = new DatagramSocket(4041);
            while (true){
                DatagramPacket packet = new DatagramPacket(new byte[16],16);
                socket.receive(packet);
                System.out.println("Received: " + new String(packet.getData()));
                String[] message = new String(packet.getData()).trim().split(":");
                new Compute(Integer.parseInt(message[0]),Integer.parseInt(message[1]),socket, (InetSocketAddress) packet.getSocketAddress()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
