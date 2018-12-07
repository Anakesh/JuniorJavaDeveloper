package lesson_17_03_12_patterns.builder;

public class SomeComputerBuilder extends ComputerBuilder {
    @Override
    public void buildDisplay() {
        computer.setDisplay("Some Display");
    }

    @Override
    public void buildSystemBlock() {
        computer.setSystemBlock("Some System Block");
    }

    @Override
    public void buildManipulators() {
        computer.setManipulators("Some Manipulators");
    }
}
