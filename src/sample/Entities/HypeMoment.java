package sample.Entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HypeMoment {
    public static final int MIN_LOGS = 4;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("[hh:mm:ss]");

    private ArrayList<Log> logs;

    public HypeMoment(ArrayList<Log> logs) {
        this.logs = logs;
        this.logs.sort((o1, o2) -> o2.getCal().compareTo(o1.getCal()));
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }

    public Calendar getMinTimestamp(){
        return logs.get(0).getCal();
    }

    public Calendar getMaxTimestamp(){
        return logs.get(logs.size()-1).getCal();
    }

    @Override
    public String toString(){
        return "Count: " + logs.size() + "\nRange: " + dateFormat.format(getMinTimestamp().getTime()) + " - " + dateFormat.format(getMaxTimestamp().getTime());
    }
}
