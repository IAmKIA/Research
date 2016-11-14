import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver extends Thread{
    DatagramSocket socket;
    public Receiver() {
        super("Receiver");
        try {
            socket = new DatagramSocket();
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
            System.out.println(new String(packet.getData()));
        }
    }
}
