package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends Command {
    public HelpCommand(ChatServer server) {
        super(server);
    }

    @Override
    public String toString() {
        return "/help";
    }

    @Override
    public boolean execute(String userName) {
        StringBuilder answer = new StringBuilder();
        List<String> commandList = new ArrayList<>(server.getCommands().keySet());
        answer.append("\nServer support next commands:");
        for(String command:commandList){
            answer.append('\n').append(command);
        }
        try{
            server.getUsersConnection().get(userName).send(new Message(server.getName(),answer.toString()));
            log(userName," help, list of commands");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
