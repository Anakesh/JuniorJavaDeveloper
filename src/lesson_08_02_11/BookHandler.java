package lesson_08_02_11;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookHandler {
    public static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Book (" +   //создание и название таблицы
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +      //название тип главенство модификаторы
                "`title` TEXT NOT NULL," +
                "`pages` INTEGER NOT NULL);";
        //register driver
        DriverManager.registerDriver(new JDBC());
        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:lesson_08_02_11.db")) {
            Statement statement = connection.createStatement();
            //executeUpdate - create delete update
            int row = statement.executeUpdate(sql);
            // 0 when created or deleted table overwhise number of changed rows
            System.out.println(row);
        }
    }

    public static void insertIntoTable(Book book) throws SQLException {
        String sql = "INSERT INTO Book (title,pages)" +
                "VALUES (?, ?);";
        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:lesson_08_02_11.db")) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPages());

            int row = statement.executeUpdate();
            System.out.println(row);
        }


    }

    //получить данные из таблицы
    public static List<Book> selectData(String targetTitle) throws SQLException {
        String sql = "SELECT * FROM Book;";//все строки со всеми столбцами
        sql = "SELECT * FROM Book WHERE title = ?";
        List<Book> books;
        try (Connection connection =
                           DriverManager.getConnection("jdbc:sqlite:lesson_08_02_11.db")) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, targetTitle);
            ResultSet resultSet = statement.executeQuery();

            books = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int pages = resultSet.getInt("pages");
                Book book = new Book(title, pages);
                books.add(book);
            }
        }
        return books;
    }

    public static void main(String[] args) {
        Book book1 = new Book("War and peace", 10000);
        Book book2 = new Book("War", 10000);
        Book book3 = new Book("Peace", 10000);
        try {
            BookHandler.createTable();
            BookHandler.insertIntoTable(book2);
            BookHandler.insertIntoTable(book3);
            List<Book> books = BookHandler.selectData("War");
            for (Book book : books)
                System.out.println(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
