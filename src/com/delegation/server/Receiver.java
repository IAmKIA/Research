package com.delegation.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver extends Thread{
    String[] returns;
    DatagramSocket socket;
    public Receiver(int length,DatagramSocket socket) {
        super("Receiver");
        returns = new String[length];
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            while (!done){
                DatagramPacket packet = new DatagramPacket(new byte[16],16);
                socket.receive(packet);
                String message = new String(packet.getData()).trim();
                System.out.println("Received: "+message+ " from "+packet.getSocketAddress().toString());
                returns[Integer.parseInt(message.split(":")[0])] = message.split(":")[1];
                done = true;
                for(String s:returns){
                    if(s==null){
                        done = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
