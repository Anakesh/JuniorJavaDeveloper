package dao;

import org.sqlite.JDBC;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

abstract class BaseDao<T> implements IDao<T> {
    Connection connection;
    private T badCode;
    public BaseDao() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:lesson_08_02_11.db");
    }

    protected void createTable(String tableName, String parameterStr) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS "+tableName+parameterStr;
        //register driver
        DriverManager.registerDriver(new JDBC());
        Statement statement = connection.createStatement();
        //executeUpdate - create delete update
        int row = statement.executeUpdate(sql);
        System.out.println(sql);
    }

    @Override
    public void add(T object) throws SQLException {

        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder commStr = new StringBuilder();
        commStr.append("INSERT INTO ");
        commStr.append(object.getClass().getSimpleName());
        commStr.append(" (");
        StringBuilder valuesStr = new StringBuilder();
        valuesStr.append(" VALUES (");
        List<Object> objects = new ArrayList<>();
        for(Field field:fields){
            if(field.isAnnotationPresent(DatabaseField.class)){
                commStr.append(field.getName()).append(",");
                try {
                    objects.add(field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                valuesStr.append("?,");
            }
        }
        commStr.delete(commStr.length()-1,commStr.length());
        commStr.append(")");
        valuesStr.delete(valuesStr.length()-1,valuesStr.length());
        commStr.append(valuesStr);
        commStr.append(");");
        System.out.println(commStr);
//        String sql = "INSERT INTO "+currentClass.getName()+" (firstColumn,secondColumn)" +
//                "VALUES ('Value 1', 123);";
//            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(commStr.toString());
            for(int i = 0;i<objects.size();i++){
                if(isNumber(objects.get(i).getClass())){
                    preparedStatement.setInt(i+1,(int)objects.get(i));
                } else if(isText(objects.get(i).getClass())){
                    preparedStatement.setString(i+1,(String) objects.get(i));
                } else if(isBoll(objects.get(i).getClass())){
                    preparedStatement.setBoolean(i+1,(boolean)objects.get(i));
                }
            }
        System.out.println(object);
        System.out.println(objects);
            //executeUpdate - create delete update
            int row = preparedStatement.executeUpdate(commStr.toString());
            // 0 when created or deleted table overwhise number of changed rows
            System.out.println(row);

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id, T object) {

    }

    @Override
    public List<T> getAll() throws SQLException {
        Class currentClass = badCode.getClass();
        String sql = "SELECT * FROM "+currentClass.getSimpleName();
        List<T> answer = getData(sql);
        return answer;
    }
    @Override
    public T getById(int id) throws SQLException {

        Class currentClass = badCode.getClass();
        String sql = "SELECT * FROM "+currentClass.getSimpleName()+" WHERE id="+id;
        List<T> answer = getData(sql);
        return answer.get(0);
    }

    private List<T> getData(String sql) throws SQLException {
        Class currentClass = badCode.getClass();
        Field[] fields = currentClass.getDeclaredFields();
        try {
            Constructor<T> currentConstructor = currentClass.getDeclaredConstructor();
            List<T> objects = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                T object = currentConstructor.newInstance();
                for(Field field:fields){
                    Object obj = resultSet.getObject(field.getName());
                    field.set(object,obj);
                }
                objects.add(object);
                System.out.println(object);
            }
            return objects;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    private boolean isNumber(Class clas){
        if(clas.equals(int.class)||clas.equals(double.class)||clas.equals(float.class)||clas.equals(short.class)||clas.equals(byte.class)||
            clas.equals(Integer.class)||clas.equals(Double.class)||clas.equals(Float.class)||clas.equals(Short.class)||clas.equals(Byte.class)){
            return true;
        } else
            return false;
    }
    private boolean isText(Class clas){
        if(clas.equals(String.class)||clas.equals(char.class)||clas.equals(Character.class))
            return true;
        else
            return false;
    }
    private boolean isBoll(Class clas){
        if(clas.equals(boolean.class)||clas.equals(Boolean.class))
            return true;
        else
            return false;
    }
}
