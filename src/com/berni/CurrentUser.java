package com.berni;

import db.DataBase;
import entity.*;
import entity.CommonWord;
import entity.ForeignWord;
import entity.hash.PartSpeech;
import entity.Translate;
import entityList.EntityHashMap;
import entityList.EntityList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;

/**
 * Класс инициирующий получение данных для выбранного пользователя
 */
public class CurrentUser implements Listener{

    ArrayList<Listener> listListner = new ArrayList<>();
    DataBase db;
    private User user = new User();
    private EntityList<Lesson> userLessons = new EntityList<>();
    private EntityHashMap<PartSpeech> partSpeech = new EntityHashMap<>();
    private EntityList<Translate> translate = new EntityList<>();
    private EntityList<CommonWord> commonWord = new EntityList<>();
    private EntityHashMap<LevelKnow> levelKnow = new EntityHashMap<>();
    private EntityList<ForeignWord> foreignWord = new EntityList<>();
    private ObservableMap<Integer, EntityList<LessonWord>> lessonWord = FXCollections.observableHashMap();
    private ObservableMap<Integer, EntityList<ForeignWord>> commonWords = FXCollections.observableHashMap();
    private ObservableList<LevelKnow> levelKnowList = FXCollections.observableArrayList();

    private Lesson selectLesson = new Lesson();

    public CurrentUser(){}
    public CurrentUser(DataBase db) {
        this.db = db;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EntityList<Lesson> getUserLessons() {
        return userLessons;
    }

    public void addLesson(String titleLesson){
        db.setAddLesson(user, titleLesson);
        for(Lesson lesson: db.getUserLesson(user)){
            if(lesson.getTitle().equals(titleLesson) && lesson.getId_user() == user.getId()) {
                userLessons.add(lesson);
                break;
            }
        }
    }

    public void delLesson(){
        if(selectLesson != null)
            db.setDelLesson(selectLesson);
        userLessons.remove(selectLesson);
    }

    public void updLesson(String newTitle){
        if(selectLesson != null) {
            selectLesson.setTitle(newTitle);
            db.setUpdLesson(selectLesson);
        }
    }

    private void setPartSpeech(){partSpeech.setHashList(db.getPartSpeech());}

    private void setTranslate() {translate.setList(db.getTranslate());}

    //public void setCommonWord() {commonWord.setHashList(db.getCommonWord());}
    private void setCommonWord() {commonWord.setList(db.getCommonWord());}

    private void setLevelKnow() {levelKnow.setHashList(db.getLevelKnow());}

    private void setForingWord() {
        foreignWord.setList(db.getForingWord(this));}

    public EntityList<ForeignWord> getForeignWord() {return foreignWord;}

    public EntityHashMap<LevelKnow> getLevelKnow() {return levelKnow;}

    public void setUserLessons(EntityList<Lesson> userLessons) {
        this.userLessons = userLessons;
    }

    private void setLessonWord(){
        for(Lesson lesson: userLessons.getList()){
            lessonWord.put(lesson.getId(), new EntityList<>());
            lessonWord.get(lesson.getId()).setList(db.getLessonWord(lesson,this));
        }
    }

    private void setCommonWords(){
        for(CommonWord commonW: commonWord.getList()){
            commonWords.put(commonW.getId(), new EntityList<>());
            for (ForeignWord foringW: foreignWord.getList()){
                if(foringW.getCommonWord() == commonW.getId())
                    commonWords.get(commonW.getId()).add(foringW);
            }
        }
    }

    @Override
    public void OnAction() {
        userLessons.setList(db.getUserLesson(user));
        setPartSpeech();
        setCommonWord();
        setLevelKnow();
        setTranslate();
        setForingWord();
        setLessonWord();
        setCommonWords();
    }

    public EntityList<LessonWord> getLessonWord(Lesson lesson) {return  lessonWord.get(lesson.getId());}

    public EntityList<ForeignWord> getCommonForingWords(CommonWord commonWord) {return  commonWords.get(commonWord.getId());}

    public EntityList<Translate> getTranslate() {return translate;}

   // public EntityHashMap<CommonWord> getCommonWord() {return commonWord;}
    public EntityList<CommonWord> getCommonWord() {return commonWord;}

    //public EntityHashMap<LevelKnow> getLevelKnow() {return levelKnow;}

    public Lesson getSelectLesson() {
        return selectLesson;
    }

    public void setSelectLesson(Lesson selectLesson) {
        this.selectLesson = selectLesson;
    }

}
