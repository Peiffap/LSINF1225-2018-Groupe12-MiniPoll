package be.lsinf1225gr12.minipoll.minipollapp;

import java.util.ArrayList;
import java.util.List;

public class MCQ extends PollAbstract {

    private int numberQuestion;
    private List<AssociationMCQ> listAssociationMCQ;

    public MCQ(boolean closedStatus, String format, String name, User author, String date, int numberQuestion){
        super(closedStatus,format,name,author,date);
        this.numberQuestion = numberQuestion;
        this.listAssociationMCQ = new ArrayList<AssociationMCQ>();
    }

    /* Getters and setters */
    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    /* Nécessaire au bon fonctionnement de AssociationMCQ */
    public void addAssociationMCQ(AssociationMCQ associationMCQ){
        this.listAssociationMCQ.add(associationMCQ);
    }

    public void deleteAssociationMCQ(AssociationMCQ associationMCQ){
        this.listAssociationMCQ.remove(associationMCQ);
    }

    public List<AssociationMCQ> getListAssociationMCQ(){
        return this.listAssociationMCQ;
    }

    /* fonction inutile ? */
    public void printinfo(){

    }







}