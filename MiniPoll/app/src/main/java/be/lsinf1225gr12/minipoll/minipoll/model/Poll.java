package be.lsinf1225gr12.minipoll.minipoll.model;


import android.content.ContentValues;
import android.database.Cursor;
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
     * * Fonction qui ajoute une posibilité à la dbavec une description à la position ...
     * @param description description de la proposition
     * @param position position de la proposition dans le sondage
     */

    public void addChoicePoll(String description,int position)
    {
        PollAnswer pollanswer = new PollAnswer(description,0);
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyChoicepollAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyChoicepollDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyChoicepollText(),description);
        cv.put(MySQLiteHelper.getKeyChoicepollPosition(),position);
        int result = (int) db.insert(MySQLiteHelper.getTableChoicepoll(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();
        this.pollAnswer.add(pollanswer);
    }
    /**
     * Fonction qui modifie la position d'une pollchoice in db ...
     * @param pollanswer description de la proposition
     * @param position position de la proposition dans le sondage
     */
    public void setChoicePollPosition(PollAnswer pollanswer,int position)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyChoicepollPosition(),position);
        String selection = MySQLiteHelper.getKeyChoicepollAuthor() + " = ? AND " + MySQLiteHelper.getKeyChoicepollDate() + " = ?"+MySQLiteHelper.getKeyChoicepollText(); //rajouter autant qu'il faut
        String[] selectionArgs = new String[]{String.valueOf(author.getId()), String.valueOf(date),pollanswer.getDescription()};
        db.update(MySQLiteHelper.getTableQuestion(), cv, selection, selectionArgs);
        int result = (int) db.insert(MySQLiteHelper.getTableChoicepoll(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();


    }
    /**
     * Fonction qui ajoute une réponse d'un utilisateur dans la db ...
     * @param pollanswer De quelle réponse on veux donné une valeur de score
     * @param score Le score qu'on donne à cette answer
     * @param user Quel user donne quel score a quel answer
     */
    public void addPollAnswer(User user,int score,PollAnswer pollanswer)
    {

        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyAnswerpollAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyAnswerpollDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyAnswerpollChoice() ,pollanswer.getInPollPosition());
        cv.put(MySQLiteHelper.getKeyAnswerpollScore(),score);
        cv.put(MySQLiteHelper.getKeyAnswerpollUser(),user.getId());
        int result = (int) db.insert(MySQLiteHelper.getTableAnswerpoll(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();

    }

    /**
     * Fonction qui return la somme des score pour un pollAnswer
     * @param pollAnswer On veut les scores de quelle réponses
     */
   public int getScoredPollChoice(PollAnswer pollAnswer )
    {
        int sumScore=0;
   // Récupération du  SQLiteHelper et de la base de données.
   SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

    String[] colonnes = {MySQLiteHelper.getKeyAnswerpollScore()};
    String selection = MySQLiteHelper.getKeyAnswerpollChoice() + " = ?"+MySQLiteHelper.getKeyAnswerpollDate() + " = ?"+MySQLiteHelper.getKeyAnswerpollAuthor() + " = ?";
    String[] selectionArgs = new String[]{String.valueOf(pollAnswer.getInPollPosition()),String.valueOf(this.getDate()),String.valueOf(author.getId())};
    Cursor cursor = db.query(MySQLiteHelper.getTableAnswerpoll(), colonnes, selection, selectionArgs, null, null, null);

    // Placement du curseur sur la première ligne.
        cursor.moveToFirst();
        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de Score pour chaque ligne.
            int score= cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.getKeyUserId()));

            // du score à la somme.
            sumScore+=score;
            // Passe à la ligne suivante.
            cursor.moveToNext();
        }


    // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return 0;
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



}

