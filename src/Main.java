import processing.core.PApplet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main extends PApplet{
    InetAddress sendTo;
    DatagramSocket socket;
    public static void main(String[] args) {
        PApplet.main("Main");
    }

    @Override
    public void settings() {
        size(700,700);

    }

    @Override
    public void setup() {
        try {
            sendTo= InetAddress.getByName("");
            socket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Receiver().start();
    }

    @Override
    public void draw() {
        if(mousePressed) {
            ellipse(mouseX,mouseY,10,10);
            String message = mouseX + "," + mouseY;
            DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(),sendTo,4040);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
