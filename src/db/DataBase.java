package db;

import com.berni.CurrentUser;
import com.berni.Main;
import entity.*;
import entity.hash.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private final String jdbc = "com.mysql.jdbc.Driver";
    private String urlDB;
    private String userDB;
    private String passwordDB;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DataBase(String url, String user, String password) {
        this.urlDB = url;
        this.userDB = user;
        this.passwordDB = password;
    }

    //-------------------------- Получение данных из Базы данных -------------------------------------

    public ObservableList<User> getUser() {
        return read(SQLquery.getUsers, new User());
    }

    public ObservableList<Lesson> getLesson() {
        return read(SQLquery.getLesson, new Lesson());
    }

    public ObservableList<LessonWord> getLessonWord(Lesson lesson, CurrentUser currentUser) {
        LessonWord lw = new LessonWord(currentUser);
        return read(SQLquery.getLessonWord_idSet + lesson.getId() + SQLquery.getLessonWord_finish, lw);
    }

    public ObservableList<Lesson> getUserLesson(User user) {
        if (Main.user != null) return read(SQLquery.getUserLesson + user.getId(), new Lesson());
        return null;
    }

    public ObservableMap<Integer, LevelKnow> getLevelKnow() {
        return read(SQLquery.getLevelKnow, new LevelKnow());
    }

    public ObservableMap<Integer, PartSpeech> getPartSpeech() {
        return read(SQLquery.getPartSpeech, new PartSpeech());
    }

    public ObservableList<Translate> getTranslate() {
        return read(SQLquery.getTranslateWord, new Translate());
    }

    public ObservableList<CommonWord> getCommonWord() {
        return read(SQLquery.getCommonWord, new CommonWord());
    }

    public ObservableList<ForeignWord> getForingWord(CurrentUser currentUser) {
        return read(SQLquery.getForingWord, new ForeignWord(currentUser));
    }

    /**
     * Добавляет перевод к иностранному слову.
     *
     * @param foreignWords Список иностранных слов
     * @param idFw        ID иностранного слова
     * @return
     * @throws SQLException
     */
    private boolean containForingWord(ObservableList<ForeignWord> foreignWords, int idFw) throws SQLException {
        for (ForeignWord foreignWord : foreignWords) {
            if (foreignWord.getId() == idFw) {
                foreignWord.addTranslate(resultSet.getInt(4));
                return true;
            }
        }
        return false;
    }

    /**
     * Читает из Базы двнных в ObservableList и возвращает его
     *
     * @return
     */
    private <T extends ReadableDB> ObservableList<T> read(String sqlQuery, ReadableDB<T> entity) {
        ObservableList<T> entityList = FXCollections.observableArrayList();
        try {
            Class.forName(jdbc).newInstance();
            connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            if (entity instanceof ForeignWord) {

                while (resultSet.next()) {
                    if (!containForingWord((ObservableList<ForeignWord>) entityList, resultSet.getInt(1))) // Добавляем перевод
                        entityList.add(entity.setResultSet(resultSet)); // Добавляем иностранное слово
                }
            } else {
                while (resultSet.next()) {
                    entityList.add(entity.setResultSet(resultSet)); // Добавляем сущность
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return entityList;
    }

    /**
     * Читает из Базы двнных в ObservableMфp и возвращает его
     *
     * @return
     */
    private <T extends ReadableDbHash> ObservableMap<Integer, T> read(String sqlQuery, ReadableDbHash<T> entity) {
        ObservableMap<Integer, T> entityList = FXCollections.observableHashMap();
        try {
            Class.forName(jdbc).newInstance();
            connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            if (entity instanceof ForeignWord) {
                while (resultSet.next()) {
                    Object sd = entity.setResultSet(resultSet);
                    if (entityList.containsKey(resultSet.getInt(1)))
                        ((ForeignWord) (entityList.get(resultSet.getInt(1)))).addTranslate(resultSet.getInt(4));
                    else entityList.put(sd.hashCode(), (T) sd);
                }
            } else {
                while (resultSet.next()) {
                    Object sd = entity.setResultSet(resultSet);
                    entityList.put(sd.hashCode(), (T) sd);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return entityList;
    }

    //-------------------------- Запись данных в Базу данных -------------------------------------

    public void setAddLesson(User user, String titelLesson) {
        write(SQLquery.AddLesson + user.getId() + ",'" + titelLesson + "');");
    }

    public void setDelLesson(Lesson delLesson) {
        write(SQLquery.delLesson + delLesson.getId() + ";");
    }

    public void setUpdLesson(Lesson updLesson) {
        write(SQLquery.updLesson1 + updLesson.getTitle() + SQLquery.updLesson2 + updLesson.getId() + ";");
    }

    /**
     * Запись в БД
     *
     * @param sqlQuery SQL запрос
     */
    private void write(String sqlQuery) {
        try {
            Class.forName(jdbc).newInstance();
            connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        } catch (IllegalAccessException | SQLException | InstantiationException | ClassNotFoundException e) {
            //TODO Обработать вызываемые исключения
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * Закрываем все подключения к БД
     */
    protected void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
