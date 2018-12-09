package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(ChatServer server) {
        super(server);
    }

    @Override
    public String toString() {
        return "/exit";
    }

    @Override
    public boolean execute(String userName) {
        server.getUsersBuffer().remove(userName);
        server.getUsersLastMessage().remove(userName);
        server.getUsers().remove(userName);

        try{
            server.getUsersConnection().get(userName).send(new Message(server.getName(),"\nSuccessfully exited"));
            log(userName, "Successfully exited");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
