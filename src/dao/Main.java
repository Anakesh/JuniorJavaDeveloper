package dao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Userrrrrr user1 = new Userrrrrr("1");
        Userrrrrr user2 = new Userrrrrr("2");
        UserDao userDao = new UserDao();
        userDao.add(user1);
        userDao.add(user2);
    }
}
