package be.lsinf1225gr12.minipoll.minipoll.model;

import java.util.List;

/**
 * Created by augus on 09/05/2018.
 */

public class Question {
    private String title;
    private int rightAnswerPosition;
    private int qPos;
    private List<Questionchoice> questionChoice;

    public Question(String title, int rightAnswerPosition, int qPos)
    {
        this.title = title;
        this.rightAnswerPosition = rightAnswerPosition;
        this.qPos=qPos;
    }

    public String getTitle() {
        return title;
    }

    public int getqPos() {
        return qPos;
    }
}
