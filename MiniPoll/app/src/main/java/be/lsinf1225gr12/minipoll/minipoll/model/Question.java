package be.lsinf1225gr12.minipoll.minipoll.model;

import java.util.List;

/**
 * Created by augus on 09/05/2018.
 */

public class Question {
    private String title;
    private int rightAnswerPosition;
    private int position;
    private List<Questionchoice> questionChoice;

    public Question(String title, int rightAnswerPosition, int position)
    {
        this.title = title;
        this.rightAnswerPosition = rightAnswerPosition;
        this.position=position;
    }

    public String getTitle() {
        return title;
    }

    public int getPosition() {
        return position;
    }
}
