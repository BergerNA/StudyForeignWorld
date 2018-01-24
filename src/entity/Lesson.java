package entity;

import db.DataBase;
import db.ReadableDB;
import entityList.EntityList;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lesson implements ReadableDB {

    private int id;
    private int id_user;
    private java.lang.String titel;

    public Lesson(){};
    public Lesson(int i, int user, String n){
        id = i;
        id_user = user;
        titel = n;
    }

    public String getTitle(){return titel;}

    public void setTitle(String title){this.titel = title;}

    @Override
    public Object setResultSet(ResultSet rs) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.id = rs.getInt(1);
        lesson.id_user = rs.getInt(2);
        lesson.titel = rs.getString(3);
        return lesson;
    }

    @Override
    public ObservableList<Lesson> readDB(DataBase db){
        return db.getLesson();
    }

    @Override
    public java.lang.String toString(){
        return titel;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
