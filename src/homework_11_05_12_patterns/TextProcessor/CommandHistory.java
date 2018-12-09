package homework_11_05_12_patterns.TextProcessor;

import homework_11_05_12_patterns.TextProcessor.Command.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandHistory {
    private List<Command> history = new ArrayList<>();

    public void add(Command command){
        history.add(command);
    }
    public void deleteLast(Command command){
        int deleteIndex = history.lastIndexOf(command);
        if(deleteIndex!=-1)
            history.remove(deleteIndex);
    }

    public List<Command> getHistory(){
        return history;
    }

    public boolean isEmpty(){
        return history.isEmpty();
    }
}
