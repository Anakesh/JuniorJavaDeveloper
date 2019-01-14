package dao;

import java.sql.SQLException;
import java.util.List;

public class ArticleDao extends BaseDao<Article>{

    public ArticleDao() throws SQLException {
        super();
        String paramStr = " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "title TEXT NOT NULL," +
                "idUser INTEGER NOT NULL);";
        createTable("Userrrrrr", paramStr);
    }
}
