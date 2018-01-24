package entityList;

import db.DataBase;
import db.ReadableDB;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Коллекция позволяющая хранить данные наследников ReadableDB.
 * Реализует считывание с БД.
 */
public class EntityList<T extends ReadableDB> implements Iterator{

    private DataBase db;
    private ObservableList<T> list = FXCollections.observableArrayList();
    private int size;
    private int index;
    private int prevIndex;

    public EntityList(){
    }

    public void add(T obj){
        list.add(obj);
    }

    public void remove(T obj){
        list.remove(obj);
    }

    public ObservableList<T> getList(){
        return list;
    }

    public void setList(ObservableList<T> list){
        this.list = list;
    }

    public  void setFromDB(T obj,DataBase db){
        list = obj.readDB(db);
    }

    @Override
    public boolean hasNext() {
        return index != list.size();
    }

    @Override
    public T next() {
        int i = index;
        if (i >= list.size())
            throw new NoSuchElementException();
        index = i + 1;
        return list.get(prevIndex = i);
    }
}
