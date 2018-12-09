package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;
import java.util.List;

public class CancelCommand extends Command {
    public CancelCommand(ChatServer server) {
        super(server);
    }

    @Override
    public boolean execute(String userName) {
        try {
            List<Message> messages = server.readFile();

            Message lastMessage = server.getUsersLastMessage().get(userName);
            if (lastMessage == null||messages==null) {
                sendAnswer(userName, "\nYou don't have any messages in the file");
                return false;
            }
            int deleteIndex = messages.lastIndexOf(lastMessage);
            if (deleteIndex > -1) {
                messages.remove(deleteIndex);
                int previousMessageIndex = -1;
                for (int i = 0; i < deleteIndex - 1; i++) {
                    if (messages.get(i).getSender().equals(userName))
                        previousMessageIndex = i;
                }
                if (previousMessageIndex > -1) {
                    server.getUsersLastMessage().put(userName, messages.get(previousMessageIndex));
                } else {
                    server.getUsersLastMessage().put(userName, null);
                }
                server.writeFile(messages);
                sendAnswer(userName, "\nLast message successfully deleted from file.");
                server.getUsersCommandHistory().get(userName).deleteLast(server.getCommands().get("/process"));
                return true;
            } else {
                sendAnswer(userName, "\nUnexpected error.");
                return false;
            }

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            sendAnswer(userName,"\nError reading/writing file.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "/cancel";
    }
}
