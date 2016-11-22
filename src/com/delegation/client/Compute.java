package com.delegation.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Compute extends Thread{
    int result;
    int input;
    int ID;
    DatagramSocket socket;
    InetSocketAddress returnAddress;
    public Compute(int ID, int input, DatagramSocket socket, InetSocketAddress returnAddress) {
        this.ID = ID;
        this.input = input;
        this.socket = socket;
        this.returnAddress = returnAddress;
    }

    @Override
    public void run() {
        /*try {
            sleep((long)input*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        result = 0;
        for(int i = 0; i< input;i++){
            result++;
            while(!isPrime(result)){
                result++;
            }

        }
        String message = ID+":"+result;
        try {
            socket.send(new DatagramPacket(message.getBytes(),message.length(),returnAddress));
            System.out.println("Sent: "+ message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isPrime(int n) {
        if (n%2==0) return false;
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
