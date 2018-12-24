package lesson_26_24_12_logger.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer extends Thread{
    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(12345)){
            try(Socket socket = serverSocket.accept()){
                InputStream in = socket.getInputStream();
                byte data = (byte) in.read();
                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
