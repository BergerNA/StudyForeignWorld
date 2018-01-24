package Controls;

import com.berni.Run;
import com.sun.org.apache.xpath.internal.operations.String;
import entity.Lesson;
import entity.LessonWord;
import entity.LevelKnow;
import entity.ForeignWord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;

/**
 * Главное окно программы
 */
public class WinMain {

    static Run run;

    public WinMain() {
    }

    public WinMain(Run run) {
        WinMain.run = run;
    }

    @FXML
    private TextField tfAddLesson;

    @FXML
    private TableView<LessonWord> wordTable;

    @FXML
    private TableColumn<LessonWord, ForeignWord> wordTableWord;

    @FXML
    private TableColumn<LessonWord, Integer> wordTableNum;

    @FXML
    private TableColumn<LessonWord, String> wordTableTranslate;

    @FXML
    private TableColumn<LessonWord, Boolean> wordTableSelect;

    @FXML
    private TableColumn<LessonWord, LevelKnow> wordTableKnow;

    @FXML
    private javafx.scene.control.ListView<Lesson> lvLessons;

    public void btAddLesson_OnAction(ActionEvent actionEvent) {
        if (tfAddLesson.getText().equals(""))
            new WinErrorModal().show("Введите название урока.");
        else {
            boolean ok = true;
            for (Lesson lesson : Run.currentUser.getUserLessons().getList()) {
                if (lesson.getTitle().equals(tfAddLesson.getText())) {
                    new WinErrorModal().show("Урок с текущим названием \"" +
                            tfAddLesson.getText() + "\", существует.");
                    ok = false;
                    break;
                }
            }
            if (ok) {
                Run.currentUser.addLesson(tfAddLesson.getText());
            }
            tfAddLesson.clear();
        }
    }

    private boolean findError(java.lang.String value) {
        boolean findErr = false;
        if (value.equals("")) {
            new WinErrorModal().show("Введите название урока.");
            findErr = true;
        } else {
            for (Lesson lesson : Run.currentUser.getUserLessons().getList()) {
                if (lesson.getTitle().equals(value)) {
                    new WinErrorModal().show("Урок с текущим названием \"" +
                            value + "\", существует.");
                    findErr = true;
                    break;
                }
            }
        }
        return findErr;
    }

    @FXML
    private void initialize() {
        // Добавляем в ListViewer список уроков юзера
        lvLessons.setItems(run.getCurrentUser().getUserLessons().getList());
        lvLessons.setEditable(true);
        MultipleSelectionModel<Lesson> lvSelModel = lvLessons.getSelectionModel();
        // Редактирование названия урока
        lvLessons.setCellFactory(TextFieldListCell.forListView(new StringConverter<Lesson>() {
            @Override
            public java.lang.String toString(Lesson lesson) {
                return lesson.getTitle();
            }

            @Override
            public Lesson fromString(java.lang.String string) {
                Lesson lesson = lvLessons.getSelectionModel().getSelectedItem();
                if (!lesson.getTitle().equals(string)) {
                    if (findError(string)) {
                        lesson.setTitle(lesson.getTitle());
                    } else {
                        lesson.setTitle(string);
                        run.getCurrentUser().updLesson(string);
                    }
                }
                return lesson;
            }
        }));

        // Добавляем слушателей каждому уроку
        wordTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        wordTable.setEditable(true);
        ObservableList<LevelKnow> lvn = FXCollections.observableArrayList();
        lvn.addAll(new LevelKnow("Знаю", 1), new LevelKnow("Не знаю", 2)); // Тестовые значения

        // Выбор урока
        lvSelModel.selectedItemProperty().addListener(new ChangeListener<Lesson>() {
            @Override
            public void changed(ObservableValue<? extends Lesson> observable, Lesson oldValue, Lesson newValue) {
                Run.currentUser.setSelectLesson(newValue);
                wordTable.setItems(run.getCurrentUser().getLessonWord(newValue).getList());
                wordTableWord.setCellValueFactory(new PropertyValueFactory<LessonWord, ForeignWord>("word"));
                wordTableNum.setCellValueFactory(new PropertyValueFactory<LessonWord, Integer>("id"));
                wordTableTranslate.setCellValueFactory(new PropertyValueFactory<LessonWord, String>("translate"));
                wordTableKnow.setCellValueFactory(new PropertyValueFactory<LessonWord, LevelKnow>("levelKnow"));
                wordTableKnow.setCellFactory(ComboBoxTableCell.forTableColumn(lvn));
                wordTableSelect.setCellValueFactory(new PropertyValueFactory<LessonWord, Boolean>("select"));
                wordTableSelect.setCellFactory(CheckBoxTableCell.forTableColumn(wordTableSelect));
            }
        });

        // Событие для всплывающего меню: удалить, переименовать
        PopupMenu popupMenu = new PopupMenu(run) {
            @Override
            public void delete() {
                run.getCurrentUser().delLesson();
            }

            @Override
            public void rename() {
            }
        };

        lvLessons.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
            popupMenu.show(lvLessons, event.getScreenX(), event.getScreenY());
            event.consume();

        });

        // Событие нажатия, скрывает всплывающее меню
        lvLessons.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            popupMenu.hide();
        });
    }

    private void tableInitialize() {
    }

    /**
     * Смена пользователя = Вызов модального окна для ввода пользователя.
     *
     * @param actionEvent
     * @throws Exception
     */
    public void menu_New_OnAction(ActionEvent actionEvent) throws Exception {
        run.getCurrentUser(this);
    }

    /**
     * Возвращает сцену.
     *
     * @return
     */
    public Stage getStage() {
        return (Stage) lvLessons.getScene().getWindow();
    }

    public void close() {
        getStage().close();
    }

    /**
     * Открывает окно поиска слова.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void wordAdd_OnAction(ActionEvent actionEvent) throws IOException {
        WinFindWord winFindWord = new WinFindWord();
        winFindWord.init(this);
    }

    public void wordChange_OnAction(ActionEvent actionEvent) {
    }

    public void wordLevelKnowHi_OnAction(ActionEvent actionEvent) {
    }

    public void wordLevelKnowMidll_OnAction(ActionEvent actionEvent) {
    }

    public void wordLevelKnowLow_OnAction(ActionEvent actionEvent) {
    }

    public void wordMove_OnAction(ActionEvent actionEvent) {
    }

    public void wordClear_OnAction(ActionEvent actionEvent) {
    }
}
