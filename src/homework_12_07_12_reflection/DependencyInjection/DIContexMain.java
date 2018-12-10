package homework_12_07_12_reflection.DependencyInjection;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class DIContexMain {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        DIContext ctx = new DIContext(B.class.getName());

        B b = (B) ctx.get(); // используйте generics, чтобы не делать cast

        String randomString = b.getI().getValue();
        DIContext cti = new DIContext(I.class.getName());
        I i = (I) cti.get();
    }
}

class DIContext<T> {
    private Class<?> objectClass;

    public DIContext(String className) throws ClassNotFoundException {
        this.objectClass = Class.forName(className);
    }

    public Object get() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Constructor<?> objectConstructor = objectClass.getDeclaredConstructor();
        objectConstructor.setAccessible(true);
        Object object = objectConstructor.newInstance();
        Field[] objectFields = objectClass.getDeclaredFields();
        for (Field field : objectFields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Resource.class)) {
                Class fieldResourceClass = field.getAnnotation(Resource.class).type();
                DIContext fieldValue;
                if(fieldResourceClass.equals(Object.class)){
                    fieldValue = new DIContext(field.getType().getName());
                }else{
                    fieldValue = new DIContext(fieldResourceClass.getName());
                }
                field.set(object, fieldValue.get());
            }
        }
        return object;
    }
}

interface I {
    String getValue();
}

class Impl implements I {
    public String getValue() {
        return String.valueOf(Math.random());
    }
}

class A {
    private String str;

    @ Resource (type = Impl.class)
    private I i;

    public I getI() {
        return i;
    }
}

class B {
    private int val;

    @Resource
    private A aVal;

    public I getI() {
        return aVal.getI();
    }
}