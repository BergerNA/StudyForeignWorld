package entity;

import com.berni.CurrentUser;
import db.DataBase;
import db.ReadableDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForeignWord implements ReadableDB{

    private int id;
    private String titel;

    private int commonWord;

    static CurrentUser currentUser = new CurrentUser();

    private ObservableList<Translate> translateList = FXCollections.observableArrayList();

    //private EntityList<SentenceExample> sentenceExamplesList = new EntityList<SentenceExample>();

    //private EntityList<Word> synonymList = new EntityList<>();

    //private EntityList<Word> antonymList = new EntityList<>();

    public ForeignWord(){}

    public ForeignWord(CurrentUser cr){
        currentUser = cr;
    }

    public ForeignWord(ResultSet rs) throws SQLException{
        id = rs.getInt(1);
        commonWord = rs.getInt(2);
        titel = rs.getString(3);
        addTranslate(rs.getInt(4));
    }

    public ForeignWord ForingWord(int num){
        for (ForeignWord foreignWord : currentUser.getForeignWord().getList()){
            if(foreignWord.getId() == num){
                return foreignWord;
            }
        }
        return null;
    }

    @Override
    public Object setResultSet(ResultSet rs) throws SQLException {
        return new ForeignWord(rs);
    }

    @Override
    public ObservableList readDB(DataBase db) {
        return null;
    }

    @Override
    public int hashCode(){return id;}

    public String getTitle() {
        return titel;
    }

    public void setTitle(String title) {
        this.titel = title;
    }

    public int getId(){return id;}

    public void setId(int id) {this.id = id;}

    public ObservableList<Translate> getTranslateList() {return translateList;}

//    public String getTranslateList() {
//        String d = "";
//        for (Translate str: translateList)
//            d+=str + " ";
//        return d;
//    }

    @Override
    public String toString(){return titel;}

    public void addTranslate(int num){
        for (Translate translate: currentUser.getTranslate().getList()){
            if(translate.getId() == num){
                translateList.add(translate);
                break;
            }
        }
    }

    public int getCommonWord() {
        return commonWord;
    }

    public void setCommonWord(int commonWord) {
        this.commonWord = commonWord;
    }
}
