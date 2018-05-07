package be.lsinf1225gr12.minipoll.minipollapp;

import java.util.Set;

public class PollAnswer {
    Set<AssociationEval> associationEval;

    private String description;
    private int position;
    public PollAnswer(User author, String date,String description, int position){
        this.description=description;
        this.position=position;

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
