package dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    void add(T object) throws SQLException;
    void delete(int id);
    void update(int id, T object);
    List<T> getAll() throws SQLException;
    T getById(int id) throws SQLException;
}
