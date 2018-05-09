package be.lsinf1225gr12.minipoll.minipoll.model;

import java.util.ArrayList;
import java.util.List;


public class Poll extends PollAbstract {
    /**
     * On peut évaluer les N différents propositions, selon un top de taille number_top ou number_top<=N.
     */
    private int number_top;
    /**
     * Il existe N différentes propositions où N=number_answer
     */
    private int number_answer;
    /**
     * Type du poll.
     * Si true le poll est un sondage pour aider dans un choix, sondage pour accord sinon
     */
    private boolean isChoice;
    /**
     * Liste des réponses possibles au poll
     */

    private PollAnswer[] pollAnswer;


    /**
     * Constructeur du Poll. Initialise une instance du Poll présent dans la base
     * de données.
     *
     * @note Ce constructeur est privé (donc utilisable uniquement depuis cette classe). Cela permet
     * d'éviter d'avoir deux instances différentes d'un même Poll.
     */
    private Poll(int number_top, int number_answer, long date, User author, String name, boolean isChoice, String question, String format, PollAnswer[] pollAnswer) {
        super(format, name, author, date);
        this.number_top = number_top;
        this.number_answer = number_answer;
        this.isChoice = isChoice;
        this.pollAnswer = pollAnswer;

    }

    /**
     * Fournit le number_top.
     */
    public int getNumber_top() {
        return number_top;
    }

    /**
     * Modifie le number_top
     */

    public void setNumber_top(int number_top) {
        this.number_top = number_top;
    }

    /**
     * Fournit le number_answer
     */


    public int getNumber_answer() {
        return number_answer;
    }

    /**
     * Modifie le number_answer
     */


    public void setNumber_answer(int number_answer) {
        this.number_answer = number_answer;
    }

    /**
     * Fournit le boolean qui te dis si c'est un sondage pour accord ou pour aider dans un choix
     */


    public boolean isChoice() {
        return isChoice;
    }

    /**
     * Modifie le boolean qui te dis si c'est un sondage pour accord ou pour aider dans un choix
     */


    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    /**
     * Fournit le tableau avec les differentes Pollanswers
     */

    public PollAnswer[] getPollAnswer() {
        return pollAnswer;
    }

    /**
     * Modifie le tableau avec les differentes Pollanswers
     */
    public void setPollAnswer(PollAnswer[] pollAnswer) {
        this.pollAnswer = pollAnswer;
    }
    /**
     * TODO Créer la méthode qui écrit dans la base de donnée
     */
}

