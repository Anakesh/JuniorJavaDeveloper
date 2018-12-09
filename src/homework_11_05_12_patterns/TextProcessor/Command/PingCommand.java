package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;

public class PingCommand extends Command {
    public PingCommand(ChatServer server) {
        super(server);
    }

    @Override
    public boolean execute(String userName) {
        try{
            server.getUsersConnection().get(userName).send(new Message(server.getName(),null));
            log(userName,"ping");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "/ping";
    }
}
