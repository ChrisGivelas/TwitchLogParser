package sample.Entities;

import java.util.Calendar;

public class Log {
    private String original;
    private Calendar cal;
    private String username;
    private String comment;

    public Log(String original, Calendar cal, String username, String comment) {
        this.original = original;
        this.cal = cal;
        this.username = username;
        this.comment = comment;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return original;
    }
}

