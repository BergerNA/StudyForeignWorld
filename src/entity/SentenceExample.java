package entity;

import db.DataBase;
import db.ReadableDB;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SentenceExample implements ReadableDB{

    private String titel;
    private String translate;
    private int id;

    public String getTitle() {
        return titel;
    }

    @Override
    public Object setResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public ObservableList readDB(DataBase db) {
        return null;
    }

    public void setTitle(String title) {
        this.titel = title;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
