package com.berni;

import Controls.WinLogIn;
import Controls.WinMain;
import db.DataBase;
import entity.Listener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Окно авторизации пользователя
 */
public class Run extends Application implements Listener{

    ArrayList<Listener> listListener = new ArrayList<>();

    public static CurrentUser currentUser;
    public static DataBase db;
    private WinLogIn winLogIn = new WinLogIn(this);
    WinMain winMain = new WinMain(this);
    private WinMain winMainOld = null;

    public Run(){};

    Run(DataBase db){
        Run.db = db;
        currentUser = new CurrentUser(db);
        listListener.add(currentUser);
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        winLogIn.start(primaryStage);
    }

    public CurrentUser getCurrentUser(){
        return currentUser;
    }

    public void getCurrentUser(WinMain winMain) throws Exception {
        start(new Stage());
        winMainOld = winMain;
    }

    @Override
    public void OnAction(){
        Stage stage = new Stage();

        try {
            if(winMainOld != null){
                winMainOld.close();
                winMainOld = null;
            }
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("/WinMain2.fxml"));
            stage.setTitle("English study");
            //stage.setMinHeight(400);
            //stage.setMinWidth(600);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
