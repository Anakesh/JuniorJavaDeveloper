package patterns.adapter;

public class TstAdapter {

    public static void main(String[] args) {

        FirstInterface first = SomeFactory.get();
        SecondInterface second = new Adapter(first);

        SecondUtils.saveData(second);
    }
}
interface  FirstInterface {
    void add(int i);
}
class SomeFactory{
    public static FirstInterface get(){
        return new FirstInterface() {
            @Override
            public void add(int i) {

            }
        };
    }
}
interface SecondInterface{
    int get(int i);
}
class SecondUtils{
    public static void saveData(SecondInterface second){

    }
}
class Adapter implements SecondInterface{
    private FirstInterface first;
    public Adapter(FirstInterface first){
        this.first = first;
    }

    @Override
    public int get(int i) {
        this.first.add(i);
        return 0;
    }
}

