package Controls;

import com.berni.Run;
import entity.CommonWord;
import entity.ForeignWord;
import entity.Translate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Окно поиска иностранного слова
 * В реализации...
 */
public class WinFindWord {

    @FXML
    private Button btAddWord;

    @FXML
    private Button btNewWord;

    private Run run = new Run();
    private CommonWord selectCommonWord = null;

    @FXML
    private TextField searchWord;

    @FXML
    private javafx.scene.control.ListView<CommonWord> findWord;

    @FXML
    private TableView<ForeignWord> tableWord;

    @FXML
    private TableColumn<ForeignWord, String> tableWordWord;

    @FXML
    private TableColumn<ForeignWord,Translate> tableWordTranslate;

    @FXML
    private TableColumn tableWordPathSpeech;

    @FXML
    private TextArea textAreWordExample;

    void init(WinMain winMain) throws IOException {
        run = WinMain.run;
        Stage stage1 = winMain.getStage();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(stage1);
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/WinFindWord.fxml"));
        stage.setTitle("Add word to lesson");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void initialize() {

        /*
         * Фильтр листа
         */
        FilteredList<CommonWord> filterCommonWord = new FilteredList<CommonWord>(run.getCurrentUser().getCommonWord().getList(),p -> true);

        searchWord.textProperty().addListener((observable, oldValue, newValue) -> {
            filterCommonWord.setPredicate(commonWord -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(commonWord.getTitle().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<CommonWord> sortCommonWord = new SortedList<CommonWord>(filterCommonWord);

        findWord.setItems(sortCommonWord);

        //*********************************************************************************************************

        MultipleSelectionModel<CommonWord> commonSelectModel = findWord.getSelectionModel();

        commonSelectModel.selectedItemProperty().addListener(new ChangeListener<CommonWord>() {
            @Override
            public void changed(ObservableValue<? extends CommonWord> observable, CommonWord oldValue, CommonWord newValue) {
                selectCommonWord = newValue;
                tableWord.setItems((ObservableList<ForeignWord>) run.getCurrentUser().getCommonForingWords(newValue).getList());
                tableWordWord.setCellValueFactory(new PropertyValueFactory<ForeignWord, String>("titel"));
            }
        });

    }

    public void OnAction_btAddWord(ActionEvent actionEvent) {
    }

    public void OnAction_btNewWord(ActionEvent actionEvent) {
    }
}
