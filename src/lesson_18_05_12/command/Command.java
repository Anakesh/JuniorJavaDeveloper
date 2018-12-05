package lesson_18_05_12.command;

abstract public class Command {
    abstract String name();
    abstract boolean execute();

    protected TextProcessor processor;
    public Command(TextProcessor processor){
        this.processor = processor;
    }
}
