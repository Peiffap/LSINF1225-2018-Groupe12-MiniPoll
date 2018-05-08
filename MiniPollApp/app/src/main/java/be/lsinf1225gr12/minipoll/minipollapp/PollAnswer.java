package be.lsinf1225gr12.minipoll.minipollapp;

import java.util.ArrayList;
import java.util.List;


public class PollAnswer {


    private String description;
    private int inPollPosition;
    private List<AssociationEval> listAssociationEval;
    public PollAnswer(User author, String date,String description, int inPollPosition){
        this.description=description;
        this.inPollPosition=inPollPosition;
        this.listAssociationEval = new ArrayList<AssociationEval>();

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInPollPosition() {
        return inPollPosition;
    }

    public void setInPollPosition(int inPollPosition) {
        this.inPollPosition =inPollPosition;
    }

    /* Nécessaire au bon fonctionnement de AssociationEval */
    public void addAssociationEval(AssociationEval associationEval){
        this.listAssociationEval.add(associationEval);
    }
    public void deleteAssociationEval(AssociationEval associationEval){
        this.listAssociationEval.remove(associationEval);
    }
    public List<AssociationEval> getListAssociationEval(){
        return this.listAssociationEval;
    }
}
