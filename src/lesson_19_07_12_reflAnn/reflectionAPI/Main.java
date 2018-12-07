package lesson_19_07_12_reflAnn.reflectionAPI;

import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
            throws
            NoSuchFieldException,
            IllegalAccessException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException {
//      каждый загруженный файл имеет соответствующий
//      java.lang.Class объект, который дает доступ к структуре класса
        // У каждого типа есть свой литерал
        System.out.println(String.class);
        System.out.println(int.class);

        Child child = new Child();
        //строковой литерал объекта
        System.out.println(child.getClass());
        //строковой литерал класса наследника
        Class cls;
        cls = child.getClass();
        System.out.println("child name "+ cls.getName());

        cls = cls.getSuperclass();
        System.out.println("parent extends "+ cls.getName());

        //доступ к компонентам

        //класс объекта
        Class<Child> childClass = Child.class;

        //доступ к public полям
        Field[] fields = childClass.getFields();
        System.out.println(Arrays.toString(fields));

        //доступ к declared полям (включая private)
        Field[] declaredFields = childClass.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));

        //доступ к public методам включая метода родителя
        Method[] methods = childClass.getMethods();
        System.out.println(Arrays.toString(methods));

        //доступ к declared методам  (включая private) не влючая методы родителя
        Method[] declaredMethods = childClass.getDeclaredMethods();
        System.out.println(Arrays.toString(declaredMethods));

        //доступ к declared конструкторам  (включая private)
        Constructor<?>[] declaredConstructors = childClass.getDeclaredConstructors();
        System.out.println(Arrays.toString(declaredConstructors));

        //доступ к конкретному полю, методу, конструктору
        Field nameField = childClass.getDeclaredField("name");

        //получить доступ к полю
        nameField.setAccessible(true);
        nameField.set(child,"CHILD");
        System.out.println((String)nameField.get(child));
        System.out.println(child);

        //получить доступ к методу
        Method method =childClass.getDeclaredMethod("getInfo");
        method.setAccessible(true);
        String data = (String) method.invoke(child);
        System.out.println(data);

        //получить доступ к пустому конструктору
        Constructor<Child> childConstructor = childClass.getDeclaredConstructor();
        //создание объекта
        Child child1 = childConstructor.newInstance();
        System.out.println(child1);

        //получить доступ к конструктору с параметрами
        Constructor<Child> childParameterConstructor = childClass.getDeclaredConstructor(String.class,int.class);
        //создание объекта
        Child child2 = childParameterConstructor.newInstance("name",0);
        System.out.println(child2);

        boolean isFinal = Modifier.isFinal(nameField.getModifiers());
        boolean isPrivate = Modifier.isPrivate(nameField.getModifiers());

        Class<?>[] interfaces = childClass.getInterfaces();


    }
}
