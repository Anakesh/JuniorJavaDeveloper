package homework_9_26_11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChatServer {
    private int port;
    private final String name = "Server";
    private Set<String> commands;
    private Set<String> users;
    private IOConnection connection;


    public ChatServer(int port) {
        this.port = port;
        this.users = new HashSet<>();
        commands = getCommands();
    }
    public Set<String> getCommands(){
        Set<String> comms = new HashSet<>();
        comms.add("/list_users");
        comms.add("/server_time");
        comms.add("/ping");
        return comms;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeString("Server started on:" +
                    serverSocket);
            while (true) {
                Socket socket = serverSocket.accept();
                connection = new IOConnection(socket);
                Message message = connection.receive();
                ConsoleHelper.writeString(message.toString());
                Message answer = handleMessage(message);
                connection.send(answer);
                connection.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Message handleMessage(Message message) throws IOException {
        Pattern comPattern = Pattern.compile("^/.*");
        Matcher comMatcher = comPattern.matcher(message.getMessText());
        users.add(message.getSender());
        if (comMatcher.matches()) {
            if (commands.contains(message.getMessText())) {
                return doCommand(message.getMessText());
            } else
                return new Message(name, "\nНеизвестная команда");
        } else
            return new Message(name, "\nСервер получил следующее сообщение: '" + message.getMessText() + "'");
    }

    private Message doCommand(String messText) throws IOException {
        Message message = null;
        switch(messText){
            case "/list_users":
                StringBuilder answer = new StringBuilder();
                answer.append("\nСписок пользователей заходивших на сервер:\n");
                for (String user : users) {
                    answer.append(user).append('\n');
                }
                message = new Message(name,answer.toString());
                break;
            case "/server_time":
                Date currentDate = new Date();

                message =  new Message(name,currentDate.toString());
                break;
            case "/ping":
                message = new Message(name,"");
                break;
        }
        return message;
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        ChatServer server = new ChatServer(port);
        server.start();
    }
}
