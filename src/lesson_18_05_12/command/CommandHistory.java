package lesson_18_05_12.command;

import java.util.Stack;

public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void add(Command command){
        history.push(command);
    }
    public Command delete(){
        return history.pop();
    }

    public boolean isEmpty(){
        return history.isEmpty();
    }
}
