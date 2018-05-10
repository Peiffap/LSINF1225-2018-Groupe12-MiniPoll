package be.lsinf1225gr12.minipoll.minipoll.model;

public class Questionchoice {
    /**
     * Position de la question dans laquelle elle appartient
     */
    private int position;
    /**
     * description du choix
     */
    private String text;
    /**
     * position de la question dans laquelle elle se trouve
     */
    private int positionquestion;

    public Questionchoice(int position, String text,int positionquestion) {
        this.position= position;
        this.text = text;
        this.positionquestion=positionquestion;
    }

    public int getPosition() {
        return position;
    }

    public String getText() {
        return text;
    }

    public int getPositionquestion() {
        return positionquestion;
    }
}
