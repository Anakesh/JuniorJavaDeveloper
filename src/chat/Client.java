package chat;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private IOConnection ioConnection;

    public Client(IOConnection ioConnection){
        this.ioConnection = ioConnection;
    }

    public void start() throws IOException {
        ConsoleHelper.writeString("Enter your name");
        String name = ConsoleHelper.readString();

        Thread reader = new Thread(new Reader(ioConnection));
        reader.start();

        ConsoleHelper.writeString("Enter message");

        while(true){
            String msg = ConsoleHelper.readString();

            //реализовать возможность выхода()
            //реализовать возможность сменить имя пользователя(/nick)
            if(msg != null && !msg.isEmpty()){
                Message message = new Message(name,msg);
                ioConnection.send(message);
            }
        }
    }

    private class Reader implements Runnable{
        private final IOConnection connection;
        private Reader(IOConnection ioConnection){
            this.connection = ioConnection;
        }
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Message message = connection.recieve();
                    ConsoleHelper.writeString(message.toString());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(
                new IOConnection(new Socket("127.0.0.1",8080))
        );
        try {
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
