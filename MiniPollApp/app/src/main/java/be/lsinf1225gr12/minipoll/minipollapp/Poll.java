package database.object;


import be.lsinf1225gr12.minipoll.minipollapp.User;


public class Poll {
    private int number_top;
    private int number_answer;
    private String date;
    private User author;
    private String name;
    private String format;
    private boolean isChoice;
    private String question;
    private boolean closedStatus;

    public Poll() {

    }

    public Poll(int number_top, int number_answer, String date, User author, String name, boolean isChoice, String question,String format ) {
        this.number_top = number_top;
        this.number_answer = number_answer;
        this.date = date;
        this.author = author;
        this.name = name;
        this.isChoice = isChoice;
        this.question = question;
        this.closedStatus = false;
        this.format=format;
    }

    public int getNumber_top() {
        return number_top;
    }

    public void setNumber_top(int number_top) {
        this.number_top = number_top;
    }

    public int getNumber_answer() {
        return number_answer;
    }

    public void setNumber_answer(int number_answer) {
        this.number_answer = number_answer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isClosedStatus() {
        return closedStatus;
    }

    public void setClosedStatus(boolean closedStatus) {
        this.closedStatus = closedStatus;
    }
}