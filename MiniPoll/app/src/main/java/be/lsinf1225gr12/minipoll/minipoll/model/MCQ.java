package be.lsinf1225gr12.minipoll.minipoll.model;

import java.util.ArrayList;
import java.util.List;
import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MCQ extends PollAbstract {

    private int numberQuestion;
    //private List<AssociationMCQ> listAssociationMCQ;

    public MCQ(boolean closedStatus, String format, String name, User author, long date, int numberQuestion){
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

    /* Getters and setters */
    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

}