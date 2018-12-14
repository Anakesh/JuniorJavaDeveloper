package homework_12_07_12_reflection.ReflectionToString;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class ReflectionMethods {
    public static String toString(Object o){
        if(o==null)
            return "null";
        Class<?> objectClass = o.getClass();
        return getString(o,objectClass,"");
    }
    private static String getString(Object o,Class<?> objectClass,String tabulation){
        if(o==null)
            return "Class:'"+objectClass.getSimpleName()+"', Value:{null}";
        StringBuilder output = new StringBuilder();
        output.append("Class:'").append(objectClass.getSimpleName()).append("', ");
        if(isPrimitive(objectClass)){
            output.append("Value:{").append(String.valueOf(o)).append("}");
        }else if(objectClass.isArray()){
            output.append(arrayToString(o,tabulation));
        } else if(Collection.class.isAssignableFrom(objectClass)){
            output.append(collectionToString(o,tabulation));
        }else if(Map.class.isAssignableFrom(objectClass)) {
            output.append(mapToString(o,tabulation));
        }
        else{
            output.append(classToString(o,objectClass,tabulation));
        }
        return output.toString().trim();

    }
    private static String arrayToString(Object o,String tabulation){
        StringBuilder output = new StringBuilder();
        output.append("Values:{");
        for(int i = 0; i< Array.getLength(o); i++) {
            output.append('\n').append(tabulation).append('\t')
                    .append(getString(Array.get(o, i),Array.get(o, i)==null?Object.class:Array.get(o, i).getClass(), tabulation + '\t'));
        }
        output.append("\n").append(tabulation).append("}");
        return output.toString();
    }
    private static String collectionToString(Object o,String tabulation){
        StringBuilder output = new StringBuilder();
        output.append("Values:{");
        Collection coll = (Collection) o;
        for(Object colObj:coll){
            output.append('\n').append(tabulation).append('\t')
                    .append(getString(colObj,colObj==null?Object.class:colObj.getClass(),tabulation+'\t'));
        }
        output.append('\n').append(tabulation).append("}");
        return output.toString();
    }
    private static String mapToString(Object o,String tabulation){
        StringBuilder output = new StringBuilder();
        output.append("Values:{");
        Map map = (Map) o;
        for (Object entry : map.entrySet()) {
            Map.Entry in = (Map.Entry) entry;
            output.append('\n').append(tabulation).append('\t').append("Collection key: ")
                    .append(getString(in.getKey(),in.getKey()==null?Object.class:in.getKey().getClass(), tabulation + '\t'))
                    .append('\n').append(tabulation).append('\t')
                    .append("Collection value: ").append(getString(in.getValue(),in.getValue()==null?Object.class:in.getValue().getClass(), tabulation+'\t')).append('\n');
        }
        output.append('\n').append(tabulation).append("}");
        return output.toString();
    }
    private static String classToString(Object o, Class<?> objectClass,String tabulation){
        StringBuilder output = new StringBuilder();
        output.append("Fields:{");
        Field[] objectFields = objectClass.getDeclaredFields();
        for(Field field : objectFields){
            field.setAccessible(true);
            if(!field.isAnnotationPresent(Exclude.class)){
                try {
                    Object fieldObject = field.get(o);
                    output.append("\n").append(tabulation).append('\t').append("Name:'").append(field.getName()).append("', ")
                            .append(getString(fieldObject,field.getType(),tabulation+'\t'));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        output.append("\n").append(tabulation).append("}");
        return output.toString();
    }
    private static boolean isPrimitive(Class<?> clas){
        return clas.isPrimitive()||clas.equals(String.class)||clas.equals(Integer.class)
                ||clas.equals(Long.class)||clas.equals(Byte.class)||clas.equals(Boolean.class)
                ||clas.equals(Short.class)||clas.equals(Float.class)||clas.equals(Double.class)
                ||clas.equals(Character.class);
    }
}
