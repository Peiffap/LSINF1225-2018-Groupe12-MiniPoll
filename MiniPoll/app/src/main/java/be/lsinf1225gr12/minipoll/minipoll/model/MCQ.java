package be.lsinf1225gr12.minipoll.minipoll.model;

import java.util.ArrayList;
import java.util.List;
import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MCQ extends PollAbstract {

    private int numberQuestion;
    private List<Question> question;

    //private List<AssociationMCQ> listAssociationMCQ;

    /**
     * Construit un MCQ et l'enregistre dans la DB
     * La liste de question est laissée vide
     */
    public MCQ(String format, String name, User author, long date, int numberQuestion){
        super(format,name,author,date);
        this.numberQuestion = numberQuestion;
        //écrit dans la BD
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyMcqAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyMcqDate(),date);
        cv.put(MySQLiteHelper.getKeyMcqFormat(),format);
        cv.put(MySQLiteHelper.getKeyMcqIsclosed(),false);
        cv.put(MySQLiteHelper.getKeyMcqNumberquestion(),numberQuestion);
        cv.put(MySQLiteHelper.getKeyMcqTitle(),name);
        int result = (int) db.insert(MySQLiteHelper.getTableMcq(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();
    }

    /**
     * Ajoute une nouvelle question au MCQ et dans la DB
     */
    public void addQuestion(String title,int position)
    {
        Question question = new Question(title,0,position);
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyQuestionAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyQuestionDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyQuestionDescription(),title);
        cv.put(MySQLiteHelper.getKeyQuestionPosition(),this.question.size()+1);
        cv.put(MySQLiteHelper.getKeyQuestionRightanswer(),0);
        int result = (int) db.insert(MySQLiteHelper.getTableQuestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();
        this.question.add(question);
    }
    /**
     * Set de la rightanswer
     */
    public void setRightanswer(int positionRight,Question question){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyQuestionRightanswer(),positionRight);
        String selection = MySQLiteHelper.getKeyQuestionAuthor() + " = ? AND " + MySQLiteHelper.getKeyQuestionDate() + " = ?"+MySQLiteHelper.getKeyQuestionDescription(); //rajouter autant qu'il faut
        String[] selectionArgs = new String[]{String.valueOf(author.getId()), String.valueOf(date),question.getTitle()};
        db.update(MySQLiteHelper.getTableQuestion(), cv, selection, selectionArgs);
        int result = (int) db.insert(MySQLiteHelper.getTableQuestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();

    }
    /**
     * Set de la position d'une question
     */
    public void setPosition(int position,Question question){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyQuestionPosition(),position);
        String selection = MySQLiteHelper.getKeyQuestionAuthor() + " = ? AND " + MySQLiteHelper.getKeyQuestionDate() + " = ?"+MySQLiteHelper.getKeyQuestionDescription(); //rajouter autant qu'il faut
        String[] selectionArgs = new String[]{String.valueOf(author.getId()), String.valueOf(date),question.getTitle()};
        db.update(MySQLiteHelper.getTableQuestion(), cv, selection, selectionArgs);
        int result = (int) db.insert(MySQLiteHelper.getTableQuestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();

    }

    /**
     * Ajoute une réponse possible à une question
     */
    public void addQuestionChoise(Question question, String description)
    {
        Questionchoice questionchoice = new Questionchoice(question.getPosition(),description);
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyChoicequestionAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyChoicequestionDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyChoicequestionPosition(),numberQuestion+1);
        cv.put(MySQLiteHelper.getKeyChoicequestionQuestionposition(),question.getPosition());
        cv.put(MySQLiteHelper.getKeyChoicequestionText(),description);


        int result = (int) db.insert(MySQLiteHelper.getTableChoicequestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();
        this.question.add(question);

    }



    /**
     * Un User répond au sondage
     */
    public void addMCQAnswer(Questionchoice questionchoice, User user, int position)
    {

        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyAnswerquestionAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyAnswerquestionDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyAnswerquestionPosition(),position);
        cv.put(MySQLiteHelper.getKeyAnswerquestionQuestionposition(),questionchoice.getPosition());
        cv.put(MySQLiteHelper.getKeyAnswerquestionUser(),user.getId());


        int result = (int) db.insert(MySQLiteHelper.getTableAnswerquestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();



    }

    /* Getters and setters */
    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

}