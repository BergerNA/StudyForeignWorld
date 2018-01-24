package entity;

import com.berni.CurrentUser;
import db.DataBase;
import db.ReadableDB;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LessonWord implements ReadableDB {

    private ForeignWord word;
    private int id;
    private LevelKnow levelKnow;
    private int count;
    private Timestamp dateTime;
    private String title;
    private String translate = "";
    private boolean select = false;
    static CurrentUser currentUser = new CurrentUser();

    public LessonWord(){}

    public LessonWord(ResultSet rs) throws SQLException{
        title = "sdsds";
        int num = rs.getInt(1);
        for (ForeignWord foreignWord : currentUser.getForeignWord().getList()){
            if(foreignWord.getId() == num){
                word = foreignWord;
                break;
            }
        }
        id = rs.getInt(10);
        levelKnow = currentUser.getLevelKnow().getHashList().get(rs.getInt(5));
        count = rs.getInt(8);
        if(rs.getTimestamp(9) != null)
            dateTime = rs.getTimestamp(9);
        else dateTime = null;

    }

    public LessonWord(CurrentUser currentUser){
        this.currentUser = currentUser;
    }

    @Override
    public Object setResultSet(ResultSet rs) throws SQLException {
        return new LessonWord(rs);
    }

    @Override
    public ObservableList readDB(DataBase db) {
        return null;
    }

    @Override
    public void setTitle(String title) {
    }

    @Override
    public String getTitle() {
        return title;
    }

    public ForeignWord getWord() {
        return word;
    }

    public void setWord(ForeignWord word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LevelKnow getLevelKnow() {
        return levelKnow;
    }

    public void setLevelKnow(LevelKnow levelKnow) {
        this.levelKnow = levelKnow;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString(){
        if(word != null)
            return "ssssss";
    else return "sds";}

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
