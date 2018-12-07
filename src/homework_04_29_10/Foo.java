package homework_04_29_10;

public class Foo {
    public static void main(String[] args) {
        A a = new A();
        A aa = new Aa();

        B b= new B();
        B bb = new Bb();

        a.foo(b);
        a.foo(bb);

        aa.foo(b);
        aa.foo(bb);

    }
}

class A{
    public void foo(B b){
        System.out.println("1");
    }
    public void foo(Bb b){
        System.out.println("2");
    }
}

class Aa extends A{
    public void foo(B b){
        System.out.println("3");
    }
    public void foo(Bb b){
        System.out.println("4");
    }
}

class B{}
class Bb extends B{}