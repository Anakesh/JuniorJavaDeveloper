package homework_8_26_11;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private int port;
    private Set<String> commands;
    private Set<String> users;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public ChatServer(int port) {
        this.port = port;
        this.users = new HashSet<>();
        commands = new HashSet<>();
        commands.add("/list_users");
        commands.add("/server_time");
        commands.add("/ping");
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on:" +
                    serverSocket);
            while (true) {
                Socket socket = serverSocket.accept();
                this.out = new ObjectOutputStream(socket.getOutputStream());
                this.in = new ObjectInputStream(socket.getInputStream());
                getMessage();
                this.out.close();
                this.in.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getMessage() throws IOException, ClassNotFoundException {

            Object object = in.readObject();
            handleMessage((Message) object);
    }

    private void handleMessage(Message message) throws IOException {
        users.add(message.getSender());
        printMessage(message);
        if(commands.contains(message.getMessText()))
            handleCommand(message.getMessText());
        else
            sendResponse("Message '"+message.getMessText()+"' has been received");

    }

    private void handleCommand(String messText) throws IOException {
        switch(messText){
            case "/list_users":
                sendResponse(users);
                break;
            case "/server_time":
                Date date = new Date();
                sendResponse(date);
                break;
            case "/ping":
                short s = 0;
                sendResponse(s);
                break;
        }
    }

    public void sendResponse(Object object) throws IOException {

            out.writeObject(object);
            out.flush();
    }

    private void printMessage(Message message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        ChatServer server = new ChatServer(port);
        server.start();
    }
}
