package be.lsinf1225gr12.minipoll.minipollapp;

public abstract class PollAbstract {
    boolean closedStatus;
    String format;
    String name;
    User author;
    String date;

    public PollAbstract(boolean closedStatus, String format, String name, User author, String date) {
        this.closedStatus =false;
        this.format = format;
        this.name = name;
        this.author = author;
        this.date = date;
    }

    public boolean isClosedStatus() {
        return closedStatus;
    }

    public void setClosedStatus(boolean closedStatus) {
        this.closedStatus = closedStatus;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
