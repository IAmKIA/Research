package com.graphics;

import com.sun.javafx.geom.Vec2f;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver extends Thread{
    DatagramSocket socket;
    Vec2f other;
    public Receiver() {
        super("com.graphics.Receiver");
        other = new Vec2f(0,0);
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
            other = new Vec2f(Float.parseFloat(message.split(",")[0]),Float.parseFloat(message.split(",")[1]));
        }
    }
}
