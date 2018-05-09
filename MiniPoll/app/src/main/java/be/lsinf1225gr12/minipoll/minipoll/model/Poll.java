package be.lsinf1225gr12.minipoll.minipoll.model;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;

import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollAuthor;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollDate;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollFormat;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollIsclosed;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollIspoll;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollNumberchoice;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollNumbertop;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollQuestion;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getKeyPollTitle;
import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getTablePoll;

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
     * question du poll
     */
    private String question;


    /**
     * Constructeur du Poll. Initialise une instance du Poll présent dans la base
     * de données.
     *
     * @note Ce constructeur est privé (donc utilisable uniquement depuis cette classe). Cela permet
     * d'éviter d'avoir deux instances différentes d'un même Poll.
     */
    private Poll(int number_top, int number_answer, long date, User author, String name, boolean isChoice, String format, PollAnswer[] pollAnswer,String question) {
        super(format, name, author, date);
        this.number_top = number_top;
        this.question=question;
        this.number_answer = number_answer;
        this.isChoice = isChoice;
        this.pollAnswer = pollAnswer;

    }
    /**
     * Fournit la question
     */
    public String getQuestion() {
        return question;
    }
    /**
     * Modifie la question
     */
    public void setQuestion(String question) {
        this.question = question;
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
     * Crée un nouvel élément dans la base de données.
     *
     * @param number_top        nombre de top à faire
     * @param number_answer     nombre d'answer rajouter
     * @param date              date de création
     * @param author            auteur  du poll
     * @param name              nom du poll
     * @param isChoice          Type du sondage
     * @param format            Format des propositions
     *
     *
     * @return Vrai (true) en cas de succès, faux (false) en cas d'échec.
     *
     * @post Enregistre le nouvel objet dans la base de données.
     */
    public static boolean createPoll(int number_top, int number_answer, long date, User author, String name, boolean isChoice, String format,String question) {

        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(getKeyPollNumbertop(),number_top);
        cv.put(getKeyPollNumberchoice(),number_answer);
        cv.put(getKeyPollDate(),date);
        cv.put(getKeyPollAuthor(),author.getId());
        cv.put(getKeyPollTitle(),name);
        cv.put(getKeyPollQuestion(),question);
        cv.put(getKeyPollIspoll(),isChoice);
        cv.put(getKeyPollFormat(),format);
        cv.put(getKeyPollIsclosed(),false);

        int result = (int)db.insert(getTablePoll(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
            return false;
        }
        db.close();

        return true;
    }

}

