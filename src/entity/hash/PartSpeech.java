package entity.hash;

import db.ReadableDbHash;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartSpeech implements ReadableDbHash {

    private String titel;
    private int id;

    public PartSpeech(){}

    public PartSpeech(ResultSet rs) throws SQLException{
        titel = rs.getString(2);
        id = rs.getInt(1);

    }

    public String getTitel() {
        return titel;
    }

    public PartSpeech setResultSet(ResultSet rs) throws SQLException {
        PartSpeech partSpeech = new PartSpeech(rs);
        return partSpeech;
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
    public int hashCode(){return id;}
}
