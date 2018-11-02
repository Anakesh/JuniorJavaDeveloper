package lesson_8_02_11;


import org.sqlite.JDBC;

import java.sql.*;

public class JDBCExample {
    //создвть таблицу
    public static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Example (" +   //создание и название таблицы
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +      //название тип главенство модификаторы
                "firstColumn TEXT NOT NULL," +
                "secondColumn INTEGER NOT NULL);";
        //register driver
        DriverManager.registerDriver(new JDBC());
        try(Connection connection =
                    DriverManager.getConnection("jdbc:sqlite:lesson_8_02_11.db")){
            Statement statement = connection.createStatement();
            //executeUpdate - create delete update
            int row = statement.executeUpdate(sql);
            // 0 when created or deleted table overwhise number of changed rows
            System.out.println(row);
        }
    }
    //вставить данные в таблицу
    public static void insertIntoTable() throws SQLException{
        String sql = "INSERT INTO Example (firstColumn,secondColumn)" +
                "VALUES ('Value 1', 123);";
        try(Connection connection =
                    DriverManager.getConnection("jdbc:sqlite:lesson_8_02_11.db")){
            Statement statement = connection.createStatement();
            //executeUpdate - create delete update
            int row = statement.executeUpdate(sql);
            // 0 when created or deleted table overwhise number of changed rows
            System.out.println(row);
        }


    }
    //получить данные из таблицы
    public static void selectData() throws SQLException {
        String sql = "SELECT * FROM Example;";//все строки со всеми столбцами
        sql = "SELECT * FROM Example WHERE id<3";
        try(Connection connection =
                    DriverManager.getConnection("jdbc:sqlite:lesson_8_02_11.db")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String str = resultSet.getString("firstColumn");
                int i = resultSet.getInt("secondColumn");
                System.out.println("FirstColumn = "+str+"\t"+"SecondColumn = "+i+"\n");
            }
        }
    }
}
