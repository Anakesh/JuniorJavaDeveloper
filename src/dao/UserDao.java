package dao;

import java.sql.SQLException;

public class UserDao extends BaseDao<Userrrrrr> {
    public UserDao() throws SQLException {
        super();
        String paramStr = " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "login TEXT NOT NULL);";
        createTable("Userrrrrr", paramStr);
    }
}
