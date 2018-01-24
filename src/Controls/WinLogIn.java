package Controls;

import com.berni.*;
import com.berni.Main.*;
import db.DataBase;
import entity.Listener;
import entity.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Окно авторизации пользователя
 */
public class WinLogIn extends Application{

    private static Run run;

    public WinLogIn(Run run){
        WinLogIn.run = run;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/WinLogIn.fxml"));
        primaryStage.setTitle("Logging in");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private TextField tbLogin;

    @FXML
    private TextField tbPassword;

    @FXML
    private Label labelErrorUser;

    @FXML
    private Label labelErrorPassword;

    public void tbLogin_OnAction(ActionEvent actionEvent) {
    }

    public void tb_OnAction(ActionEvent actionEvent) {
    }

    public void btCancel_OnAction(ActionEvent actionEvent) {
    }

    public void btNew_OnAction(ActionEvent actionEvent) {
    }

    /**
     * Авторизация, сверка введенных данных пользователя.
     * @param actionEvent
     */
    public void btLoginIn_OnAction(ActionEvent actionEvent) {
        String login = tbLogin.getText();
        String password = tbPassword.getText();
        User userCheck = null;
        if(login != null){
            for (User usr: Run.db.getUser()){
                if (usr.getLogin().equals(login)) {
                    userCheck = usr;
                    break;
                }
            }
        }

        if (userCheck != null){
            labelErrorUser.setVisible(false);
           if(userCheck.getPassword() == null) {
               run.getCurrentUser().setUser(userCheck);
               run.getCurrentUser().OnAction();
               run.OnAction();
               close(actionEvent);
           }
           else if(userCheck.getPassword().equals(password)){
               run.getCurrentUser().setUser(userCheck);
               run.getCurrentUser().OnAction();
               run.OnAction();
               close(actionEvent);
           }
                else labelErrorPassword.setVisible(true);
        }
        else {
            labelErrorUser.setVisible(true);
            labelErrorPassword.setVisible(false);
        }
    }

    private void close(ActionEvent actionEvent){
            Stage d = (Stage) labelErrorPassword.getScene().getWindow();
            d.close();
    }

    /**
     * Проверка нажатия клавиши ENTER
     * При выполненнии условия вызывает метод проверки
     * введенного юзера с юзерами в БД
     * @param event - событие
     */
    public void onKeyReleased(Event event) {
        if(((KeyEvent) event).getCode() == KeyCode.ENTER)
            btLoginIn_OnAction(new ActionEvent());

    }
}
