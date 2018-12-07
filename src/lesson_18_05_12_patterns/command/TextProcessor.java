package lesson_18_05_12_patterns.command;

import java.util.Scanner;

public class TextProcessor {
    private CommandHistory history = new CommandHistory();
    //перечислить все комманды

    public void executeCommand(Command command){
        if(command.execute()){
            history.add(command);
        }
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);
        while(true){
//            String in = scanner.nextLine();
            switch (scanner.nextLine()){
                case "process":
                    executeCommand(new ProcessCommand(this));
                    break;
                case "exit":
                    executeCommand(new ExitCommand(this));
                    break;
            }
            /*if(in.equals("process")){
                executeCommand(new ProcessCommand(this));
            }*/
        }
    }
}
