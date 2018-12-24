package lesson_26_24_12_logger.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpClient extends Thread{
    @Override
    public void run() {
        DatagramPacket packet =
                new DatagramPacket(new byte[]{10},1,
                        new InetSocketAddress("localhost",12345));
        try{
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
