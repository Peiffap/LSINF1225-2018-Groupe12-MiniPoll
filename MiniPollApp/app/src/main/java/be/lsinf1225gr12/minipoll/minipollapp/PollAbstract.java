package be.lsinf1225gr12.minipoll.minipollapp;

public abstract class PollAbstract {
    boolean ClosedStatus;
    String Format;
    String Name;
    User Author;
    String Date;

    public PollAbstract(String format, String name, User author, String date) {
        ClosedStatus = false;
        Format = format;
        Name = name;
        Author=author;
        Date = date;
    }

    public boolean isClosedStatus() {
        return ClosedStatus;
    }

    public void setClosedStatus(boolean closedStatus) {
        ClosedStatus = closedStatus;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
