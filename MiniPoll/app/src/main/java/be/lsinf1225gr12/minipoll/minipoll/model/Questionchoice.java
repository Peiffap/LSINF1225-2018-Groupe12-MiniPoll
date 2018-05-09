package be.lsinf1225gr12.minipoll.minipoll.model;

public class Questionchoice {
    private int qPos;
    private String text;

    public Questionchoice(int qPos, String text) {
        this.qPos = qPos;
        this.text = text;
    }

    public int getqPos() {
        return qPos;
    }

    public String getText() {
        return text;
    }
}
