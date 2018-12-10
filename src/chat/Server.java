package chat;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;

public class Server {
    private int port;
    private final Set<IOConnection> connections = new CopyOnWriteArraySet<>();
    private final BlockingDeque<Message> messageBlockingDeque = new LinkedBlockingDeque<>();

    public Server(int port){
        this.port = port;
    }

    private void start() throws IOException {
        new Thread(new Writer()).start();

        try(ServerSocket serverSocket = new ServerSocket(port)){
            ConsoleHelper.writeString("Server started on:" +serverSocket);
            IOConnection ioConnection;
            while(true){
                Socket socket = serverSocket.accept();
                ioConnection = new IOConnection(socket);
                connections.add(ioConnection);
                new Thread(new Reader(ioConnection)).start();
            }
        }
    }
    private class Writer implements Runnable{

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Message message = messageBlockingDeque.take();
                    for(IOConnection connection:connections){
                        connection.send(message);
                    }
                } catch (InterruptedException | IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }
    private class Reader implements Runnable{
        private IOConnection connection;
        private Reader(IOConnection connection){
            this.connection = connection;
        }
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Message message = connection.recieve();
                    messageBlockingDeque.add(message);
                    ConsoleHelper.writeString(message.toString());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
