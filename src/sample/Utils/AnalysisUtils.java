package sample.Utils;

import sample.Entities.AnalyzedLogFile;
import sample.Entities.AnalyzedWord;
import sample.Entities.Log;

import java.util.*;

public class AnalysisUtils {
    public static HashMap<String, Integer> countWords(ArrayList<Log> logs) {
        HashMap<String, Integer> count = new HashMap<>();

        for (Log log : logs) {
            String[] wordsInComment = log.getComment().split(" ");

            for (String word : wordsInComment) {
                Integer wordCount = count.get(word);

                if (wordCount == null) {
                    count.put(word, 1);
                } else {
                    count.put(word, wordCount + 1);
                }
            }
        }
        return count;
    }

    public static Log parseLine(String line, Calendar cal) {
        int timestampClosingBracketIndex = line.indexOf(']');

        String[] timestamp = line.substring(1, timestampClosingBracketIndex).split(":");

        Calendar clone = (Calendar) cal.clone();

        clone.add(Calendar.HOUR, Integer.parseInt(timestamp[0]));
        clone.add(Calendar.MINUTE, Integer.parseInt(timestamp[1]));
        clone.add(Calendar.SECOND, Integer.parseInt(timestamp[2]));

        int usernameClosingBracketIndex = line.indexOf('>');

        String username = null;

        if(usernameClosingBracketIndex != -1){
            username = line.substring(timestampClosingBracketIndex + 3, usernameClosingBracketIndex);
        }

        String comment = line.substring(usernameClosingBracketIndex + 2);

        return new Log(line, clone, username, comment);

    }

    public static ArrayList<Log> parseLines(ArrayList<String> lines) {
        ArrayList<Log> logs = new ArrayList<>();
        String[] date = lines.get(0).substring(15, 25).split("-");

        Calendar cal = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));

        Log current;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).charAt(0) == '[') {
                current = parseLine(lines.get(i), cal);
                logs.add(current);
            }
        }

        return logs;
    }
}
