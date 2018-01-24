package entity;

import db.DataBase;
import db.ReadableDB;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements ReadableDB{
    private int id;
    private String login;
    private String password;

    public User(){}

    public User(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        login = rs.getString(2);
        password = rs.getString(3);
    }

    public User(int i, String l, String p){
        id = i;
        login = l;
        password = p;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null) return false;
        if(obj.getClass() != getClass()) return false;
        User other = (User) obj;
        if(other.login != login) return false;
        if(other.password != password) return false;
        return true;
    }

    @Override
    public User setResultSet(ResultSet rs) throws SQLException {
        return new User(rs);
    }

    @Override
    public ObservableList<User> readDB(DataBase db){
        return db.getUser();
    }

    @Override
    public void setTitle(String title) {
        setLogin(title);
    }

    @Override
    public String getTitle() {
        return login;
    }
}
