package be.lsinf1225gr12.minipoll.minipollapp;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String title;
    private int rightAnswerPosition;
    private List<AssociationMCQ> listAssociationMCQ;

    public Question(String title, int rightAnswerPosition) {
        this.title = title;
        this.rightAnswerPosition = rightAnswerPosition;
        this.listAssociationMCQ = new ArrayList<AssociationMCQ>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRightAnswerPosition() {
        return rightAnswerPosition;
    }

    public void setRightAnswerPosition(int rightAnswerPosition) {
        this.rightAnswerPosition = rightAnswerPosition;
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

    /* Sert à rien ¯\_(ツ)_/¯  */
    public void printInfo(){

    }
}
