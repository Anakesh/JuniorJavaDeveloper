package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;
import java.util.Set;

public class CommandSetCommand extends Command {
    public CommandSetCommand(ChatServer server) {
        super(server);
    }

    @Override
    public boolean execute(String userName) {
        StringBuilder answer = new StringBuilder();
        Set<String> commands = server.getCommands().keySet();
        for(String command:commands)
            answer.append(command).append('\n');
        try{
            server.getUsersConnection().get(userName).send(new Message(server.getName(),answer.toString().trim()));
            log(userName," list of command");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "/command_set";
    }
}
