package database.object;
import java.util.Hashtable;


public class Poll {
    private int number_top;
    private int number_answer;
    private String date;
    private int id;
    private String name;
    private boolean isChoise;
    private String question;
    private boolean closedStatus;
    private Hashtable pollAnswer ;

    public Poll() {

    }

    public Poll(int number_top, int number_answer, String date, int id, String name, boolean isChoise, String question, boolean closedStatus, Hashtable pollAnswer) {
        this.number_top = number_top;
        this.number_answer = number_answer;
        this.date = date;
        this.id = id;
        this.name = name;
        this.isChoise = isChoise;
        this.question = question;
        this.closedStatus = closedStatus;
        this.pollAnswer= pollAnswer;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsChoise() {
        return isChoise;
    }

    public void setIsChoise(boolean isChoise) {
        this.isChoise = isChoise;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getClosedStatus() {
        return closedStatus;
    }

    public void setClosedStatus(boolean closedStatus) {
        this.closedStatus = closedStatus;
    }

    public void setPollAnswer(Hashtable pollAnswer, int i, String p) {
        pollAnswer.put(i,p);
    }
}