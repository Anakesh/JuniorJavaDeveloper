package homework_9_26_11;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;


public class IOConnection {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public IOConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message mess) throws IOException {
        out.writeObject(mess);
        out.flush();
    }

    public Message receive() throws IOException, ClassNotFoundException {
        return (Message) in.readObject();
    }
    public void close() throws IOException {
        this.in.close();
        this.out.close();
    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        out.close();
        in.close();
    }
}
