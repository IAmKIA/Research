package com.tron;

import processing.core.PApplet;
import processing.core.PVector;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main extends PApplet{
    Player player1;
    Player player2;
    Receiver receiver;
    InetAddress sendTo;
    DatagramSocket socket;
    public static void main(String[] args) {
        PApplet.main("com.tron.Main");
    }

    @Override
    public void settings() {
        size(700,700);
    }

    @Override
    public void setup() {
        noStroke();
        player1 = new Player(color(100,100,200),new PVector(150,150),new PVector(1,0));
        player2 = new Player(color(200,100,100),new PVector(550,550),new PVector(-1,0));
        try {
            sendTo= InetAddress.getByName("172.25.46.247");
            socket = new DatagramSocket(4040);
        } catch (Exception e) {
            e.printStackTrace();
        }
        receiver = new Receiver();
        receiver.start();
    }

    @Override
    public void draw() {
        background(200);
        for(int i = 0 ; i < 300;i++){
            fill(player1.color);
            ellipse(player1.trail[i].x,player1.trail[i].y,5,5);
            fill(player2.color);
            ellipse(player2.trail[i].x,player2.trail[i].y,5,5);
        }
        if(receiver.other!=null) {
            player1.dir = receiver.other;
        }
        player1.shiftTrail();
        player2.shiftTrail();
    }

    @Override
    public void keyPressed() {
        switch (key){
            case 'w': player2.dir = new PVector(0,-1);break;
            case 'a': player2.dir = new PVector(-1,0);break;
            case 's': player2.dir = new PVector(0,1);break;
            case 'd': player2.dir = new PVector(1,0);
        }
        try {
            socket.send(new DatagramPacket((key+"").getBytes(),1,sendTo,4041));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
