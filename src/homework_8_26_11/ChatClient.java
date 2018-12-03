package homework_8_26_11;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatClient {
    private SocketAddress socketAddress;
    private Scanner scanner;
    private Set<String> commands;

    public ChatClient(SocketAddress address, Scanner scanner){
        this.socketAddress = address;
        this.scanner = scanner;
        commands = new HashSet<>();
        commands.add("/list_users");
        commands.add("/server_time");
        commands.add("/ping");
    }

    private void start(){
        System.out.println("Enter your name");
        String name = scanner.nextLine();

        while(true){
            System.out.println("Enter your message");
            String msg = scanner.nextLine();

            parseMessage(name, msg);
        }
    }

    private void parseMessage(String name, String msgText) {
        Pattern comPattern =  Pattern.compile("^/.*");
        Matcher comMatcher = comPattern.matcher(msgText);
        Message message = new Message(name,msgText);
        try(Socket socket = new Socket()) {
            socket.connect(socketAddress);
            if (comMatcher.matches() && commands.contains(msgText)) {
                try {
                    doCommand(message,socket);
                } catch (ClassNotFoundException e) {
                    System.out.println("Сервер не отвечает");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println((String)sendMessage(message,socket));
            }
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void doCommand(Message message, Socket socket) throws InterruptedException, IOException, ClassNotFoundException {
        switch (message.getMessText()) {
            case "/list_users":
                Set<String> users = (Set<String>) sendMessage(message, socket);
                System.out.println("Список пользователей заходивших на сервер:");
                for (String user : users) {
                    System.out.println(user);
                }
                break;
            case "/server_time":
                Date date = (Date) sendMessage(message,socket);
                System.out.println("Время на сервере: " + date);
                break;
            case "/ping":
                Date sendDate = new Date();
                sendMessage(message,socket);
                Date responseDate = new Date();
                int ping = (int) (responseDate.getTime() - sendDate.getTime());
                System.out.println("Пинг равен: " + ping+" мс");
                break;
        }
    }


    private Object sendMessage(Message message, Socket socket) throws IOException, InterruptedException, ClassNotFoundException {
        try (
                ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());
        ) {
            objOut.writeObject(message);
            objOut.flush();
//            int i = 0;
//            while (objIn.available() <= 0) {
//                Thread.sleep(1);
//                i++;
//                if (i > 5000)
//                    break;
//            }
            return objIn.readObject();
        }
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
        Scanner scanner = new Scanner(System.in);
        if(address  == null){
            System.out.println("Enter server IP and port");
            //address = scanner.nextLine();
            address = "127.0.0.1:8080";
        }

//        "127.0.0.1:8080"
        ChatClient client = new ChatClient(
                parseAddress(address),
                scanner
        );
        client.start();
    }
}
