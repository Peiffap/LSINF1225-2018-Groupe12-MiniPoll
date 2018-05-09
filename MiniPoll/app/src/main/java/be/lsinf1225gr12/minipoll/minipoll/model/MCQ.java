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
    public void addQuestion(String title)
    {
        Question question = new Question(title,0);
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
     * Ajoute une réponse possible à une question
     */
    public void addMCQAnswer(Question question, String description, User user)
    {
        MCQAnswer mcqAnswer = new MCQAnswer(description);
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyAnswerquestionAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyAnswerquestionDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyAnswerquestionPosition(),numberQuestion+1);
        cv.put(MySQLiteHelper.getKeyAnswerquestionQuestionposition(),0);
        cv.put(MySQLiteHelper.getKeyAnswerquestionUser(),user.getId());
        int result = (int) db.insert(MySQLiteHelper.getTableQuestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();
        this.question.add(question);
    }

    /**
     * Ajoute une réponse au MCQ et l'enregistre dans la DB
     */
    public void giveAnswer(Question question, MCQAnswer mcqAnswer)
    {

    }

    /* Getters and setters */
    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

}