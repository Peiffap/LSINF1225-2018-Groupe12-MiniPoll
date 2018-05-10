package be.lsinf1225gr12.minipoll.minipoll.model;

public class Questionchoice {
    private int position;
    private String text;

    public Questionchoice(int position, String text) {
        this.position= position;
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public String getText() {
        return text;
    }
}
