package homework_11_05_12_patterns.TextProcessor.Command;

import homework_11_05_12_patterns.TextProcessor.ChatServer;
import homework_11_05_12_patterns.TextProcessor.ConsoleHelper;
import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.IOException;

abstract public class Command {

    public abstract boolean execute(String userName);
    public abstract String toString();

    protected ChatServer server;
    public Command(ChatServer server){
        this.server = server;
    }
    public String name() {
        return this.getClass().getSimpleName();
    }
    public boolean sendAnswer(String userName, String message){
        try{
            server.getUsersConnection().get(userName).send(new Message(server.getName(), message));
            log(userName,message.trim());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void log(String userName, String message){
        ConsoleHelper.writeString("From Server to '"+userName+"' :"+ message);
    }



}
