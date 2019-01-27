package patterns.decorator.Example;

public class Main {
    public static void main(String[] args) {
        IService service =
                new CleaningDecorator(new WashDecorator(new TO()));
        System.out.println(service.getPrice());
        System.out.println(service.getDescription());

        IService service1 =
                new WashDecorator(new TO());
        System.out.println(service1.getPrice());
        System.out.println(service1.getDescription());
    }
}
