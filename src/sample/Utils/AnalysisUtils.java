package sample.Utils;

import sample.Entities.Log;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysisUtils {
    private static Pattern datePattern = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");

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
        Matcher m = datePattern.matcher(lines.get(0));

        Calendar cal;

        if(m.find()){
            String[] date = m.group().split("-");
            cal = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1])-1, Integer.parseInt(date[2]));
        }else{
            cal = Calendar.getInstance();
        }

        Log current;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).charAt(0) == '[') {
                current = parseLine(lines.get(i), cal);
                logs.add(current);
            }
        }

        return logs;
    }

    public static boolean isCapsLocked(String comment){
        for(char c: comment.toCharArray()){
            if(Character.isAlphabetic(c)){
                if(!Character.isUpperCase(c)){
                    return false;
                }
            }
        }
        return true;
    }
}
