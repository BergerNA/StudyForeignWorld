package db;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс, данные в класс считываются из Базы данных.
 */
public interface ReadableDB<T>{
    T setResultSet(ResultSet rs) throws SQLException;
    ObservableList<T> readDB(DataBase db);

    public void setTitle(String title);
    public String getTitle();
}