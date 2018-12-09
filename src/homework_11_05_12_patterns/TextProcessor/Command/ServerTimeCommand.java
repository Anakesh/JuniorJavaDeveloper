package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;
import java.util.Date;

public class ServerTimeCommand extends Command {
    public ServerTimeCommand(ChatServer server) {
        super(server);
    }

    @Override
    public boolean execute(String userName) {
        Date currentDate = new Date();
        try {
            server.getUsersConnection().get(userName).send(new Message(server.getName(), currentDate.toString()));
            log(userName,"server time");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "/server_time";
    }
}
