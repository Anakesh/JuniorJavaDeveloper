package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;

public class ListUsersCommand extends Command {
    public ListUsersCommand(ChatServer server) {
        super(server);
    }

    @Override
    public boolean execute(String userName) {
        StringBuilder answer = new StringBuilder();
        answer.append("\nUser list:");
        for (String user : server.getUsers()) {
            answer.append('\n').append(user);
        }
        try {
            server.getUsersConnection().get(userName).send(new Message(server.getName(), answer.toString()));
            log(userName,"list of users");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "/list_users";
    }
}
