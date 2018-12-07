package sample.Entities;

import sample.Utils.AnalysisUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnalyzedLogFile {
    private ArrayList<Log> logs;
    private HashMap<String, Integer> wordCounts;

    private ArrayList<Map.Entry<String,Integer>> sortedWordCounts;
    private ArrayList<HypeMoment> hypeMoments;

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

    public ArrayList<HypeMoment> getHypeMoments(){
        if(hypeMoments == null){
            hypeMoments = new ArrayList<>();
            for(int i = 0 ; i < logs.size() ; i++) {
                boolean isHypeMoment = true;
                ArrayList<Log> hypeLogs = new ArrayList<>();
                while (isHypeMoment && i < logs.size()) {
                    if (AnalysisUtils.isCapsLocked(logs.get(i).getComment())) {
                        hypeLogs.add(logs.get(i));
                        i++;
                    } else {
                        isHypeMoment = false;
                    }
                }

                if (hypeLogs.size() >= HypeMoment.MIN_LOGS) {
                    hypeMoments.add(new HypeMoment(hypeLogs));
                }
            }

            hypeMoments.sort((o1, o2) -> o2.getLogs().size() - o1.getLogs().size());
        }

        return hypeMoments;
    }
}
