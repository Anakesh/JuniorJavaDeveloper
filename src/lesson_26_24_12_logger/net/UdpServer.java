package lesson_26_24_12_logger.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer extends Thread{
    @Override
    public void run() {
        try{
            DatagramSocket datagramSocket = new DatagramSocket(12345);
            byte[] buf = new byte[1];
            DatagramPacket packet = new DatagramPacket(buf,buf.length);
            datagramSocket.receive(packet);
            System.out.println(buf[0]+ " "+packet.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
