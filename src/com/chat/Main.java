package com.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {
    static InetAddress sendTo;
    static DatagramSocket socket;
    Receiver reciever;

    public static void main(String[] args) {
        try {
            sendTo= InetAddress.getByName("172.25.90.69");
            socket = new DatagramSocket(4040);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Receiver().start();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while(!input.equals("end")){
            DatagramPacket packet = new DatagramPacket(input.getBytes(),input.length(),sendTo,4041);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            input = scanner.next();
        }
    }
}
