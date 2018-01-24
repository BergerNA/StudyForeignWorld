package entityList;

import db.ReadableDbHash;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * Коллекция позволяющая хранить данные наследников ReadableDB в виде: "ключ - значение".
 */
public class EntityHashMap<T extends ReadableDbHash> {

    private ObservableMap<Integer,T> hashList= FXCollections.observableHashMap();

    public EntityHashMap(){}

    public void setHashList(ObservableMap<Integer,T> list){
        hashList = list;
    }

    public ObservableMap<Integer,T> getHashList(){
        return hashList;
    }

    public void add(T obj){
        hashList.put(obj.hashCode(),obj);
    }

    public void remover(T obj){
        hashList.remove(obj.hashCode());
    }

    public T getIndex(int index) { return hashList.get(index); }
}
