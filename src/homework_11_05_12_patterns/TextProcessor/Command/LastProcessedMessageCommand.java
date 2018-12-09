package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;

public class LastProcessedMessageCommand extends Command {
    public LastProcessedMessageCommand(ChatServer server) {
        super(server);
    }

    @Override
    public boolean execute(String userName) {
        try {
            Message message = server.getUsersLastMessageFromFile(userName);
            String answer;
            if (message == null)
                answer = "\nYour don't have any messages";
            else
                answer = "\nYour last processed message was:\n" + message.getMessText();
            if (sendAnswer(userName, answer)) {
                return true;
            } else
                return false;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "/last_processed_message";
    }
}
