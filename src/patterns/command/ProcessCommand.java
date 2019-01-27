package patterns.command;

public class ProcessCommand extends  Command{
    public ProcessCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "ProcessCommand";
    }

    @Override
    boolean execute() {
        System.out.println("реализация");
        return true;
    }
}
