package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReadableDbHash<T>{
    int hashCode();
    T setResultSet(ResultSet rs) throws SQLException;
}
