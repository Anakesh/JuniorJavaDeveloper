package lesson_18_05_12.decorator.Example;

public class CleaningDecorator extends ServiceDecorator {
    public CleaningDecorator(IService service) {
        super(service);
    }

    @Override
    public double getPrice() {
        return super.getPrice()+500;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" and wash";
    }
}
