package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.Entities.AnalyzedWord;
import sample.Entities.Log;

public class Word {
    @FXML
    Label wordHeader = new Label();

    @FXML
    Label wordCount = new Label();

    @FXML
    ListView<Log> wordInstances = new ListView<>();

    public void initData(AnalyzedWord word){
        wordHeader.setText(word.getWord());
        wordCount.setText(word.getCount() + "");
        wordInstances.setItems(FXCollections.observableArrayList(word.getLogsContainingWord()));
    }
}
