package sample.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnalyzedLogFile {
    private ArrayList<Log> logs;
    private HashMap<String, Integer> wordCounts;

    private ArrayList<Map.Entry<String,Integer>> sortedWordCounts;

    public AnalyzedLogFile(ArrayList<Log> logs, HashMap<String, Integer> wordCounts) {
        this.logs = logs;
        this.wordCounts = wordCounts;
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }

    public HashMap<String, Integer> getWordCounts() {
        return wordCounts;
    }

    public void setWordCounts(HashMap<String, Integer> wordCounts) {
        this.wordCounts = wordCounts;
    }

    public ArrayList<Map.Entry<String,Integer>> getSortedWordCounts(){
        if(sortedWordCounts == null){
            sortedWordCounts = new ArrayList<>(wordCounts.entrySet());
            sortedWordCounts.sort((o1, o2) -> o2.getValue() - o1.getValue());
        }

        return sortedWordCounts;
    }
}
