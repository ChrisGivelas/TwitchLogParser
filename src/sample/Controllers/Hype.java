package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.Entities.AnalyzedWord;
import sample.Entities.HypeMoment;
import sample.Entities.Log;

public class Hype {
    @FXML
    Label hypeHeader = new Label();

    @FXML
    ListView<Log> hypeLogs = new ListView<>();

    public void initData(HypeMoment hypeMoment){
        hypeHeader.setText(hypeMoment.toString());
        hypeLogs.setItems(FXCollections.observableArrayList(hypeMoment.getLogs()));
    }
}
