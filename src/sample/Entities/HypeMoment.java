package sample.Entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

public class HypeMoment {
    public static final int MIN_LOGS = 4;

    private static SimpleDateFormat timestampFormat = new SimpleDateFormat("[kk:mm:ss]");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ArrayList<Log> logs;

    public HypeMoment(ArrayList<Log> logs) {
        this.logs = logs;
        this.logs.sort(Comparator.comparing(Log::getCal));
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
        return "Date: "+ dateFormat.format(getMinTimestamp().getTime()) + "\nRange: " + timestampFormat.format(getMinTimestamp().getTime()) + " - " + timestampFormat.format(getMaxTimestamp().getTime()) + "\nCount: " + logs.size();
    }
}
