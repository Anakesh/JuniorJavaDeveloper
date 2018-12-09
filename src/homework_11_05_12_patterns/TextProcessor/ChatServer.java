package homework_11_05_12_patterns.TextProcessor;

import homework_11_05_12_patterns.TextProcessor.Command.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChatServer {
    private int port;
    private final String name = "Server";
    private Map<String, Command> commands;
    private Set<String> users = new HashSet<>();
    private File serverFile;
    private Map<String, IOConnection> usersConnection = new HashMap<>();
    private Map<String, CommandHistory> usersCommandHistory = new HashMap<>();
    private Map<String, StringBuilder> usersBuffer = new HashMap<>();
    private Map<String, Message> usersLastMessage = new HashMap<>();


    public ChatServer(int port) {
        this.port = port;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        serverFile = new File(dateFormat.format(new Date()) + ".txt");
        commands = getCommandMap();
        try {
            serverFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeString("Server started on:" + serverSocket);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    IOConnection connection = new IOConnection(socket);
                    Message message = connection.receive();
                    updateUsers(message.getSender());
                    usersConnection.put(message.getSender(), connection);
                    handleMessage(message);
                    ConsoleHelper.writeString(message.toString());
                    connection.close();
                }catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(Message message) throws IOException {
        String sender = message.getSender();
        String text = message.getMessText();
        if (isCommand(text)) {
            if (commands.keySet().contains(text)) {
                if (commands.get(text).execute(sender))
                    usersCommandHistory.get(sender).add(commands.get(text));
            } else {
                usersConnection.get(sender).send(new Message(name, "\nНеизвестная команда"));
            }
        } else {
            usersBuffer.get(sender).append(text);
            usersConnection.get(sender).send(new Message(name, "\nСервер получил следующее сообщение: '" + message.getMessText() + "'"));
        }
    }

    private boolean isCommand(String string) {
        Pattern pattern = Pattern.compile("^/.+");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public Message getUsersLastMessageFromFile(String userName) throws IOException, ClassNotFoundException {
        List<Message> messages = readFile();
        if(messages==null)
            return null;
        Message lastMessage = null;
        for (Message message : messages) {
            if (message.getSender().equals(userName))
                lastMessage = message;
        }
        return lastMessage;
    }

    public void writeFile(List<Message> messageList) throws IOException {
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(serverFile))) {
            ous.writeObject(messageList);
        }
    }

    public List<Message> readFile() throws IOException, ClassNotFoundException {
        if(serverFile.length()==0)
            return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serverFile))) {
            return (List<Message>) ois.readObject();
        }
    }


    private void updateUsers(String userName) {
        if (!users.contains(userName)) {
            users.add(userName);
            usersBuffer.put(userName, new StringBuilder());
            Message lastMessage = null;
            try{
                 lastMessage= getUsersLastMessageFromFile(userName);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            usersLastMessage.put(userName, lastMessage);
            usersCommandHistory.put(userName, new CommandHistory());
        }
    }

    private Map<String, Command> getCommandMap() {
        Map<String, Command> commandSet = new HashMap<>();
        Command help = new HelpCommand(this);
        commandSet.put(help.toString(), help);
        Command cancel = new CancelCommand(this);
        commandSet.put(cancel.toString(), cancel);
        Command exit = new ExitCommand(this);
        commandSet.put(exit.toString(), exit);
        Command listUsers = new ListUsersCommand(this);
        commandSet.put(listUsers.toString(), listUsers);
        Command ping = new PingCommand(this);
        commandSet.put(ping.toString(), ping);
        Command process = new ProcessCommand(this);
        commandSet.put(process.toString(), process);
        Command serverTime = new ServerTimeCommand(this);
        commandSet.put(serverTime.toString(), serverTime);
        Command getCommandSet = new CommandSetCommand(this);
        commandSet.put(getCommandSet.toString(),getCommandSet);
        Command lastProcessedMessage = new LastProcessedMessageCommand(this);
        commandSet.put(lastProcessedMessage.toString(),lastProcessedMessage);

        return commandSet;
    }

    public String getName() {
        return name;
    }

    public Set<String> getUsers() {
        return users;
    }

    public Map<String, StringBuilder> getUsersBuffer() {
        return usersBuffer;
    }

    public Map<String, Message> getUsersLastMessage() {
        return usersLastMessage;
    }

    public Map<String, IOConnection> getUsersConnection() {
        return usersConnection;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        ChatServer server = new ChatServer(port);
        server.start();
    }
}
