package sample.Entities;

import java.util.ArrayList;

public class AnalyzedWord {
    private String word;
    private int count;
    private AnalyzedLogFile file;

    private ArrayList<Log> logsContainingWord;

    public AnalyzedWord(String word, int count, AnalyzedLogFile file) {
        this.word = word;
        this.count = count;
        this.file = file;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AnalyzedLogFile getFile() {
        return file;
    }

    public void setFile(AnalyzedLogFile file) {
        this.file = file;
    }

    public ArrayList<Log> getLogsContainingWord() {
        if(logsContainingWord == null){
            logsContainingWord = new ArrayList<>();

            for (Log log : this.file.getLogs()) {
                if(log.getComment().contains(this.word)){
                    logsContainingWord.add(log);
                }
            }
        }

        return logsContainingWord;
    }
}
