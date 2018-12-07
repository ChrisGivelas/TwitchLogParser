package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import sample.Entities.AnalyzedLogFile;
import sample.Entities.AnalyzedWord;
import sample.Entities.HypeMoment;
import sample.Entities.Log;
import sample.Utils.AnalysisUtils;
import sample.Utils.FileUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    private HashMap<String, AnalyzedLogFile> analyzedLogFiles = new HashMap<>();

    private AnalyzedLogFile currentlySelectedFile;

    @FXML
    ListView<String> fileList = new ListView<>();

    @FXML
    ListView<Map.Entry<String, Integer>> wordList = new ListView<>();

    @FXML
    Button upload = new Button();

    @FXML
    MenuButton analyzedFileMenu = new MenuButton();

    @FXML
    ListView<HypeMoment> hypeList = new ListView<>();

    @FXML
    private void onMenuChange(ActionEvent e) {
        MenuItem selected = (MenuItem) e.getSource();

        analyzedFileMenu.setText(selected.getText());

        if (selected.getText().equalsIgnoreCase("Word Count")) {
            wordList.setVisible(true);
            hypeList.setVisible(false);

        } else {
            wordList.setVisible(false);
            hypeList.setVisible(true);
        }

    }

    @FXML
    private void onUpload() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Twitch log files (.txt /.log)", "*.txt", "*.log")
        );
        File f = fc.showOpenDialog(upload.getScene().getWindow());

        String fileDisplayName = f.getName() + " (" + f.getAbsolutePath() + ")";

        AnalyzedLogFile analyzedLogFile = analyzedLogFiles.get(fileDisplayName);

        if (analyzedLogFile == null) {
            ArrayList<String> logLines = FileUtils.readLines(f);
            ArrayList<Log> logs = AnalysisUtils.parseLines(logLines);

            HashMap<String, Integer> wordCounts = AnalysisUtils.countWords(logs);

            analyzedLogFile = new AnalyzedLogFile(logs, wordCounts);

            analyzedLogFiles.put(fileDisplayName, analyzedLogFile);

            fileList.getItems().add(fileDisplayName);
        }

        currentlySelectedFile = analyzedLogFile;

        fileList.getSelectionModel().select(fileDisplayName);

        onSelectFile();
    }

    @FXML
    private void onSelectFile() {
        String file = fileList.getSelectionModel().getSelectedItem();
        currentlySelectedFile = analyzedLogFiles.get(file);
        wordList.setItems(FXCollections.observableArrayList(currentlySelectedFile.getSortedWordCounts()));
        hypeList.setItems(FXCollections.observableArrayList(currentlySelectedFile.getHypeMoments()));
    }

    @FXML
    private void onSelectWord() {
        Map.Entry<String, Integer> wordCount = wordList.getSelectionModel().getSelectedItem();

        AnalyzedWord currentlySelectedWord = new AnalyzedWord(wordCount.getKey(), wordCount.getValue(), currentlySelectedFile);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/sample/word.fxml"
                )
        );

        Parent root;
        try {
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Word Analysis");
            stage.setScene(new Scene(root, 600, 400));

            Word controller = loader.getController();
            controller.initData(currentlySelectedWord);

            stage.show();
        } catch (IOException i) {
            System.out.println("Error!");
        }
    }

    @FXML
    private void onSelectHype() {
        HypeMoment hypeMoment = hypeList.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/sample/hype.fxml"
                )
        );

        Parent root;
        try {
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Hype Moment");
            stage.setScene(new Scene(root, 600, 400));

            Hype controller = loader.getController();
            controller.initData(hypeMoment);

            stage.show();
        } catch (IOException i) {
            System.out.println("Error!");
        }
    }
}
