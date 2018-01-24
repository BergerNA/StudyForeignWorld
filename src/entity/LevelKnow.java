package entity;

import db.ReadableDbHash;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelKnow implements ReadableDbHash {

    private String titel;
    private int id;

    public LevelKnow(){}

    public LevelKnow(ResultSet rs) throws SQLException{
        id = rs.getInt(1);
        titel = rs.getString(2);
    }

    public LevelKnow(String titel, int id){
        this.titel = titel;
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Object setResultSet(ResultSet rs) throws SQLException {
        return new LevelKnow(rs);
    }

    public int hashCode(){ return id;}

    @Override
    public String toString(){return titel;}
}
