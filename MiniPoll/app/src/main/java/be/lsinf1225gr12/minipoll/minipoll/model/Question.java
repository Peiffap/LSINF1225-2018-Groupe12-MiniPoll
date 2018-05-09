package be.lsinf1225gr12.minipoll.minipoll.model;

/**
 * Created by augus on 09/05/2018.
 */

public class Question {
    private String title;
    private int rightAnswerPosition;

    public Question(String title, int rightAnswerPosition)
    {
        this.title = title;
        this.rightAnswerPosition = rightAnswerPosition;
    }
}
