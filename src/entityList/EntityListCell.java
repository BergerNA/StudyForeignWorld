package entityList;

import db.ReadableDB;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Реализация TextFiel в полях ListView
 * Не задействовано
 */
public class EntityListCell<T extends ReadableDB> extends ListCell<T> {
    private final TextField textField = new TextField();

    public EntityListCell(){
        textField.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
        textField.setOnAction(e -> {
            getItem().setTitle(textField.getText());
            setText((textField.getText()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        });
        setGraphic(textField);
    }

    @Override
    protected void updateItem(T lesson, boolean empty){
        super.updateItem(lesson, empty);
        if(isEditing()){
            textField.setText(lesson.getTitle());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else {
            setContentDisplay(ContentDisplay.TEXT_ONLY);
            if(empty)
                setText(null);
            else setText(lesson.getTitle());
        }
    }

    @Override
    public void startEdit(){
        super.startEdit();
        textField.setText(getItem().getTitle());
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.requestFocus();
        textField.selectAll();
    }

    @Override
    public  void cancelEdit(){
        super.cancelEdit();
        setText(getItem().getTitle());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
        System.out.println("NOT MAIN");
    }
}


