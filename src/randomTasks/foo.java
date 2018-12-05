package randomTasks;

public class foo {
    public static void main(String[] args) {
        X foo = new Y(1);
        System.out.println(foo.doWork());
        foo.setValue2(5);
        System.out.println(foo.doWork());

    }
}

abstract class X{
    public int value1;
    public X(int value1){
        this.value1 = value1;
    }
    abstract void setValue2(int value2);
    abstract int doWork();
}

class Y extends X{
    public int value2;

    public Y(int value1) {
        super(value1);
    }

    @Override
    void setValue2(int value2) {
        this.value2 = value2;
    }

    @Override
    int doWork() {
        return value1+value2;
    }
}
