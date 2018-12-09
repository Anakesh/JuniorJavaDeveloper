package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessCommand extends Command {
    public ProcessCommand(ChatServer server) {
        super(server);
    }

    @Override
    public String toString() {
        return "/process";
    }

    @Override
    public boolean execute(String userName) {
        String messText = server.getUsersBuffer().get(userName).toString();
        Message message = new Message(userName,messText);
        try {
            List<Message> messages = server.readFile();
            if(messages ==null)
                messages = new ArrayList<>();
            messages.add(message);
            server.writeFile(messages);
            server.getUsersLastMessage().put(userName,message);
            server.getUsersBuffer().put(userName,new StringBuilder());
            sendAnswer(userName,"\nMessage successfully added to file.");
            return true;
        } catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
            sendAnswer(userName,"\nError reading/writing file.");
            return false;
        }
    }
}
