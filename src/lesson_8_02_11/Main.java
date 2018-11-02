package lesson_8_02_11;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            JDBCExample.createTable();
            JDBCExample.insertIntoTable();
            JDBCExample.selectData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
