package entity;

import db.DataBase;
import db.ReadableDB;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonWord implements ReadableDB{

    private String titel;
    private int id;

    public CommonWord(){}

    public  CommonWord(ResultSet rs){
        try {
            titel = rs.getString(2);
            id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object setResultSet(ResultSet rs) throws SQLException {
        return new CommonWord(rs);
    }

    @Override
    public ObservableList readDB(DataBase db) {
        return null;
    }

    public String getTitle() {
        return titel;
    }

    public void setTitle(String title) {
        this.titel = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {return  titel;}

    @Override
    public int hashCode(){return id;}
}
