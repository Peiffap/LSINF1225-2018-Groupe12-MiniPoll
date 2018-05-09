package be.lsinf1225gr12.minipoll.minipoll.model;

/**
 * Created by augus on 09/05/2018.
 */

public class Question {
    private String title;
    private int rightAnswerPosition;
    private int qpos;

    public Question(String title, int rightAnswerPosition, int qpos)
    {
        this.title = title;
        this.rightAnswerPosition = rightAnswerPosition;
        this.qpos=qpos;
    }

    public String getTitle() {
        return title;
    }

    public int getRightAnswerPosition() {
        return rightAnswerPosition;
    }

    public int getQpos() {
        return qpos;
    }
}
