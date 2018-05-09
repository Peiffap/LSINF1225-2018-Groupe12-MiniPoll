package be.lsinf1225gr12.minipoll.minipoll.model;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;

import static be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper.getTablePoll;

public class PollAnswer {
    /**
     * Donne la description de la proposition
     */
    private String description;
    /**
     * La position de la proposition
     */
    private int inPollPosition;
    /**
     * Constructeur du PollAnswer. Initialise une instance du PollAnswer présent dans la base
     * de données.
     *
     *
     */
    public PollAnswer(String description, int inPollPosition){
        this.description=description;
        this.inPollPosition=inPollPosition;


    }

    /**
     * fournit la description de la pollAnswer
     */
    public String getDescription() {
        return description;
    }
    /**
     * Modifie la description de la pollAnswer
     */

    public void setDescription(String description)
    {
        this.description = description;
    }
    /**
     * Fournit la position de la proposition
     */

    public int getInPollPosition()
    {
        return inPollPosition;
    }
    /**
     * Modifie la position de la proposition
     */
    public void setInPollPosition(int inPollPosition)
    {
        this.inPollPosition =inPollPosition;
    }



}
