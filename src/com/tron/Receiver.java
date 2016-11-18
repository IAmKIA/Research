package com.tron;

import processing.core.PVector;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver extends Thread{
    DatagramSocket socket;
    PVector other;
    public Receiver() {
        super("com.tron.Receiver");
        try {
            socket = new DatagramSocket(4041);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message = "";
        while(!message.contains("end")){
            DatagramPacket packet = new DatagramPacket(new byte[32],32);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            message = new String(packet.getData());
            switch (message.trim()){
                case "w": other = new PVector(0,-1);break;
                case "a": other = new PVector(-1,0);break;
                case "s": other = new PVector(0,1);break;
                case "d": other = new PVector(1,0);
            }
        }
    }
}
