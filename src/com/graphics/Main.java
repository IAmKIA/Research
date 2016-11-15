package com.graphics;

import processing.core.PApplet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main extends PApplet{
    InetAddress sendTo;
    DatagramSocket socket;
    Receiver reciever;
    public static void main(String[] args) {
        PApplet.main("com.graphics.Main");
    }

    @Override
    public void settings() {
        size(700,700);

    }

    @Override
    public void setup() {
        try {
            sendTo= InetAddress.getByName("172.25.90.69");
            socket = new DatagramSocket(4040);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reciever = new Receiver();
        reciever.start();
        noStroke();
        background(150);
    }

    @Override
    public void draw() {
        fill(0);
        ellipse(reciever.other.x,reciever.other.y,10,10);
        fill(255);
        if(mousePressed) {
            ellipse(mouseX,mouseY,10,10);
            String message = mouseX + "," + mouseY;
            DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(),sendTo,4041);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
