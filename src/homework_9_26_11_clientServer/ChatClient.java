package homework_9_26_11_clientServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ChatClient {
    private SocketAddress socketAddress;
    private IOConnection connection;
    private Set<String> commands;

    public ChatClient(SocketAddress address){
        this.socketAddress = address;
        commands = new HashSet<>();
        commands.add("/list_users");
        commands.add("/server_time");
        commands.add("/ping");
    }

    private void start(){
        ConsoleHelper.writeString("Enter your name");
        String name = ConsoleHelper.readString();

        while(true){
            ConsoleHelper.writeString("Enter your message");
            String msg = ConsoleHelper.readString().trim();
            Message message = new Message(name, msg);
            try(Socket socket = new Socket()) {
                socket.connect(socketAddress);
                connection = new IOConnection(socket);
                Message answer = parseMessage(message);
                ConsoleHelper.writeString(answer.toString());
                connection.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ChatException e) {
                ConsoleHelper.writeString(e.getMessage());
                //e.printStackTrace();
            }
        }
    }

    private Message parseMessage(Message message) throws ChatException {
        Message answer;
        try {
            if (commands.contains(message.getMessText())) {
                answer = doCommand(message);
            } else {
                answer = sendMessage(message);
            }
        } catch (IOException e) {
            throw new ChatException("Ошибка соединения", e);
        } catch (ClassNotFoundException e) {
            throw new ChatException("Сервер не отвечает", e);
        }
        return answer;
    }

    private Message doCommand(Message message) throws IOException, ClassNotFoundException, ChatException {
        Message answer;
        switch (message.getMessText()) {
            case "/list_users":
                answer = sendMessage(message);
                break;
            case "/server_time":
                answer = sendMessage(message);
                break;
            case "/ping":
                Date sendDate = new Date();
                answer = sendMessage(message);
                Date responseDate = new Date();
                int ping = (int) (responseDate.getTime() - sendDate.getTime());
                answer.setMessText("\nПинг равен: " + ping + " мс");
                break;
            default:
                throw new ChatException("Команда не найдена");

        }
        return answer;
    }


    private Message sendMessage(Message message) throws IOException, ClassNotFoundException {
        connection.send(message);
        Message answer = connection.receive();
        return answer;
    }



    private static SocketAddress parseAddress(String address) {
        String[] strings = address.split(":");
        return new InetSocketAddress(strings[0],Integer.parseInt(strings[1]));
    }

    public static void main(String[] args) {

        String address = null;
        if(args!= null&&args.length>0){
            address = args[0];
        }
        if(address  == null){
            ConsoleHelper.writeString("Enter server IP and port");
            address = ConsoleHelper.readString();
        }

//        "127.0.0.1:8080"
        ChatClient client = new ChatClient(parseAddress(address));
        client.start();
    }
}

