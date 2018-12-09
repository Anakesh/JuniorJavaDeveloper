package homework_11_05_12_patterns.TextProcessor;

import homework_11_05_12_patterns.TextProcessor.Command.Command;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatClient {
    private String name;
    private SocketAddress socketAddress;
    private IOConnection connection;
    private Set<String> commands;

    public ChatClient(SocketAddress address) throws IOException, ClassNotFoundException {
        this.socketAddress = address;
    }

    private void start() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeString("Enter your name");
        name = ConsoleHelper.readString();
        commands = getCommandSet();
        while (true) {
            String msg = ConsoleHelper.readString();
            if(isOK(msg.trim())) {
                try (Socket socket = new Socket()) {
                    socket.connect(socketAddress);
                    connection = new IOConnection(socket);
                    Message answer = parseMessage(msg);
                    ConsoleHelper.writeString(answer.toString());
                    connection.close();
                }
                if (msg.trim().equals("/exit"))
                    break;
            } else
                ConsoleHelper.writeString("Unknown command, write '/help' to know command list");
        }
    }

    private Message parseMessage(String strMessage) throws IOException, ClassNotFoundException {
        Message answer;
        String trimmedMess = strMessage.trim();
        if (isCommand(trimmedMess)) {
            Message message = new Message(name, trimmedMess);
            answer = doCommand(message);
        } else {
            Message message = new Message(name, strMessage);
            answer = sendMessage(message);
        }
        return answer;
    }

    private Message doCommand(Message message) throws IOException, ClassNotFoundException {
        Message answer;
        if (message.getMessText().equals("/ping")) {
            Date sendDate = new Date();
            answer = sendMessage(message);
            Date responseDate = new Date();
            int ping = (int) (responseDate.getTime() - sendDate.getTime());
            answer.setMessText("\nПинг равен: " + ping + " мс");
        } else
            answer = sendMessage(message);
        return answer;
    }

    private boolean isOK(String message){
        if (isCommand(message) && !commands.contains(message))
            return false;
        else
            return true;
    }


    private Message sendMessage(Message message) throws IOException, ClassNotFoundException {
        connection.send(message);
        Message answer = connection.receive();
        return answer;
    }

    private Set<String> getCommandSet() throws IOException, ClassNotFoundException {
        Set<String> commandSet;
        try (Socket socket = new Socket()) {
            socket.connect(socketAddress);
            connection = new IOConnection(socket);
            Message answer = sendMessage(new Message(name, "/command_set"));
            commandSet = new HashSet<>(Arrays.asList(answer.getMessText().split("\\s+")));
            connection.close();
        }
        return commandSet;
    }

    private boolean isCommand(String string) {
        Pattern pattern = Pattern.compile("^/.+");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }


    private static SocketAddress parseAddress(String address) {
        String[] strings = address.split(":");
        return new InetSocketAddress(strings[0], Integer.parseInt(strings[1]));
    }

    public static void main(String[] args) {

        String address = null;
        if (args != null && args.length > 0) {
            address = args[0];
        }
        if (address == null) {
//            ConsoleHelper.writeString("Enter server IP and port");
//            address = ConsoleHelper.readString();
            address = "127.0.0.1:8080";
        }

//        "127.0.0.1:8080"
        try {
            ChatClient client = new ChatClient(parseAddress(address));
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

