package lesson_7_31_10.Local;

public class Local {
    public static void main(String[] args) {
        class LocalClass{
            String name = "LocalClass";
            String getName(){
                return this.name;
            }
        }
        LocalClass localClass = new LocalClass();
        System.out.println(localClass.getName());
    }
}
