package entity;

import db.DataBase;
import db.ReadableDB;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Translate implements ReadableDB{

    private String titel;
    private int id;
    private int partSpeech;

    public Translate(){}

    public Translate(ResultSet rs)throws SQLException{
        id = rs.getInt(1);
        partSpeech = rs.getInt(3);
        titel = rs.getString(4);
    }

    public String getTitle() {
        return titel;
    }

    @Override
    public Object setResultSet(ResultSet rs) throws SQLException {
        Translate translate = new Translate(rs);
        return translate;
    }

    @Override
    public ObservableList readDB(DataBase db) {
        return null;
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
    public int hashCode() {return id;}
}
