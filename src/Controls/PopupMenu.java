package Controls;

import com.berni.Run;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.awt.*;

/**
 * Всплывающее меню, Rename + Delete
 */
public class PopupMenu<T> extends ContextMenu {
    Run run;

    PopupMenu(Run run) {
        this.run = run;
        getItems().addAll(MenuItemBuilder
                        .create()
                        .text("Rename")
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                rename();
                            }
                        })
                        .build(),
                MenuItemBuilder
                        .create()
                        .text("Delete")
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                delete();
                            }
                        })
                        .build());
    }

    public void delete(){
       // run.getCurrentUser().delLesson();
    }

    public void rename(){

    }

    public void show(Node anchor, MouseEvent event){
        if (event.getButton() == MouseButton.SECONDARY) {
            super.show(anchor, event.getScreenX(), event.getScreenY());
        }
    }
}
