package lesson_17_03_12_patterns.builder;

abstract class ComputerBuilder {
    Computer computer;
    public Computer getComputer(){
        return computer;
    }
    public void creadComputer(){
        computer = new Computer();
    }
    public abstract void buildDisplay();
    public abstract void buildSystemBlock();
    public abstract void buildManipulators();
}
