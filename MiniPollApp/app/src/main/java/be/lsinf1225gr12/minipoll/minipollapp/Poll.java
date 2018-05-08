package be.lsinf1225gr12.minipoll.minipollapp;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Poll extends PollAbstract {
    private int number_top;
    private int number_answer;
    private boolean isChoice;
    private String question;
    private PollAnswer[] pollAnswers ;
    private List<AssociationEval> listAssociationEval;



    public Poll(int number_top, int number_answer, String date, User author, String name, boolean isChoice, String question,String format,PollAnswer[] pollAnswers, boolean closedStatus ) {
        super (closedStatus,format, name, author, date);
        this.number_top = number_top;
        this.number_answer = number_answer;
        this.isChoice = isChoice;
        this.question = question;
        this.pollAnswers=pollAnswers;
        this.listAssociationEval = new ArrayList<AssociationEval>();

    }


    public int getNumber_top() {
        return number_top;
    }

    public void setNumber_top(int number_top) {
        this.number_top = number_top;
    }

    public int getNumber_answer() {
        return number_answer;
    }

    public void setNumber_answer(int number_answer) {
        this.number_answer = number_answer;
    }

    /* Savoir si c'est un sondage pour accord ou sondage pour choix
    * true = sondage pour choix
    * false = sondage pour accord*/
    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public PollAnswer[] getPollAnswers() {
        return pollAnswers;
    }

    public void setPollAnswers(PollAnswer[] pollAnswers) {
        this.pollAnswers = pollAnswers;
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