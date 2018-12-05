package lesson_18_05_12.decorator.Example;

public class WashDecorator  extends  ServiceDecorator{
    public WashDecorator(IService service){
        super(service);
    }

    @Override
    public double getPrice() {
        return super.getPrice()+1000;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" and wash";
    }
}
