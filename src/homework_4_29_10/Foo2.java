package homework_4_29_10;

public class Foo2 {
    public static void main(String[] args) {
        Base a = new First();
        /*
        int x = a.i;
        a.text();
        */
        a.foo();
        Base b = new Second();
        b.foo();
        First c = new Second();
        c.foo();
        System.out.println(c.first);
        //int z = c.second;
        //Second d = new First();
    }
}

interface Base{
    public void foo();
}

class First implements Base{
    public String first = "first";
    @Override
    public void foo() {
        System.out.println("First");
    }
    public void text(){

    }
}

class Second extends First{

    public String first = "second";
    public int second;
    @Override
    public void foo() {
        System.out.println("Second");
    }
}
