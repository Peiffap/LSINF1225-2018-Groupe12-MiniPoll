package be.lsinf1225gr12.minipoll.minipoll.model;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

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

    private List <PollAnswer> pollAnswer;
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
    private Poll(int number_top, int number_answer, long date, User author, String name, boolean isChoice, String format,String question) {
        super(format, name, author, date);
        this.number_top = number_top;
        this.question=question;
        this.number_answer = number_answer;
        this.isChoice = isChoice;
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyPollNumbertop(),number_top);
        cv.put(MySQLiteHelper.getKeyPollNumberchoice(),number_answer);
        cv.put(MySQLiteHelper.getKeyPollDate(),date);
        cv.put(MySQLiteHelper.getKeyPollAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyPollTitle(),name);
        cv.put(MySQLiteHelper.getKeyPollQuestion(),question);
        cv.put(MySQLiteHelper.getKeyPollIspoll(),isChoice);
        cv.put(MySQLiteHelper.getKeyPollFormat(),format);
        cv.put(MySQLiteHelper.getKeyPollIsclosed(),false);

        int result = (int)db.insert(getTablePoll(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression

        }
        db.close();


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
     * Modifie le tableau avec les differentes Pollanswers
     */
    public void addPollAnswer(String description, int inPollPosition)
    {

        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyChoicepollText(),description);
        cv.put(MySQLiteHelper.getKeyChoicepollPosition(),inPollPosition);
        cv.put(MySQLiteHelper.getKeyChoicepollDate(),date);
        cv.put(MySQLiteHelper.getKeyChoicepollAuthor(),author.getId());


        int result = (int)db.insert(getTablePoll(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression

        }
        db.close();

    }


}

