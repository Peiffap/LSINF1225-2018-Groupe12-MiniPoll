package be.lsinf1225gr12.minipoll.minipoll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import be.lsinf1225gr12.minipoll.minipoll.model.User;

/**
 classe générale pour accès à la DB.

 Emploi :

 * lire la DB :

 SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
 String selection = NOM_COLONNE + " = ? AND " + AUTRE_NOM_COLONNE + " = ?"; //rajouter autant qu'il faut
 String[] selectionArgs = new String[]{String.valueOf(VALEUR), String.valueOf(VALEUR)};
 Cursor c = db.query(NOM_TABLE, NOM_COLONNE, selection, selectionArgs, null, null, null);
 c.moveToFirst(); //place au premier résultat
 //c.moveToNext(); //place au prochain résultat
 //c.isLast(); //true si on est au dernier élément
 c.getTYPE(...); //différents type de get, cf https://developer.android.com/reference/android/database/Cursor#getcolumnindex
 c.close();
 db.close();

 * écrire dans la DB :

 SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
 ContentValues cv = new ContentValues();
 cv.put(NOM_COLONNE,VALEUR);
 int result = db.insert(NOM_TABLE, null, cv);
 if (result==-1)
 {
 //erreur dans l'ajout, suppression
 }
 db.close();

 * modifier la DB :

 SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
 ContentValues cv = new ContentValues();
 cv.put(NOM_COLONNE,VALEUR);
 String selection = NOM_COLONNE + " = ? AND " + AUTRE_NOM_COLONNE + " = ?"; //rajouter autant qu'il faut
 String[] selectionArgs = new String[]{String.valueOf(VALEUR), String.valueOf(VALEUR)};
 db.update(NOM_TABLE, cv, selection, selectionArgs);
 int result = (int) db.insert(NOM_TABLE, null, cv);
 if (result==-1)
 {
 //erreur dans l'ajout, suppression
 }
 db.close();

 * effacer des données de la DB :

 SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
 String selection = NOM_COLONNE + " = ? AND " + AUTRE_NOM_COLONNE + " = ?"; //rajouter autant qu'il faut
 String[] selectionArgs = new String[]{String.valueOf(cv.valeur), String.valueOf(cv.VALEUR)};
 db.delete(TABLE_NAME, selection, selectionArgs);
 db.close();

 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_SQL_FILENAME = "database.sql";
    /**
     * Nom du fichier de la base de données.
     */
    private static final String DATABASE_NAME = "minipoll_database.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static MySQLiteHelper instance;

    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        instance = this;
    }

    public static MySQLiteHelper get() {
        if (instance == null) {
            return new MySQLiteHelper(MiniPollApp.getContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteDatabase(db);
        onCreate(db);
    }

    private void initDatabase(SQLiteDatabase db) {
        try {
            // Ouverture du fichier sql.
            Scanner scan = new Scanner(MiniPollApp.getContext().getAssets().open(DATABASE_SQL_FILENAME));
            scan.useDelimiter(Pattern.compile(";"));
            while (scan.hasNext()) {
                String sqlQuery = scan.next();
                /*
                 * @note : Pour des raisons de facilité, on ne prend en charge ici que les fichiers
                 * contenant une instruction par ligne. Si des instructions SQL se trouvent sur deux
                 * lignes, cela produira des erreurs (car l'instruction sera coupée)
                 */
                if (!sqlQuery.trim().isEmpty() && !sqlQuery.trim().startsWith("--")) {
                    Log.d("MySQL query", sqlQuery);
                    db.execSQL(sqlQuery);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture du fichier " + DATABASE_SQL_FILENAME + " : " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur SQL lors de la création de la base de données." +
                    "Vérifiez que chaque instruction SQL est au plus sur une ligne." +
                    "L'erreur est : " + e.getMessage(), e);
        }
    }

    /**
     * Supprime toutes les tables dans la base de données.
     *
     * @param db Base de données.
     *
     * @post Les tables de la base de données passées en argument sont effacées.
     */
    private void deleteDatabase(SQLiteDatabase db) {
        Cursor c = db.query("sqlite_master", new String[]{"name"}, "type = 'table' AND name NOT LIKE '%sqlite_%'", null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            db.execSQL("DROP TABLE IF EXISTS " + c.getString(0));
            c.moveToNext();
        }
    }

    /**  NOMS DES TABLES ET COLONNES =======================================================================================
     * */

    private static final String TABLE_ANSWERPOLL = "AnswerPoll";
    private static final String TABLE_ANSWERQUESTION = "AnswerQuestion";
    private static final String TABLE_CHOICEPOLL = "ChoicePoll";
    private static final String TABLE_CHOICEQUESTION = "ChoiceQuestion";
    private static final String TABLE_FRIENDRELATION = "FriendRelation";
    private static final String TABLE_MCQ = "MCQ";
    private static final String TABLE_PARTICIPATIONPOLL = "ParticipationPoll";
    private static final String TABLE_PARTICIPATIONQUESTION = "ParticipationMCQ";
    private static final String TABLE_POLL = "Poll";
    private static final String TABLE_QUESTION = "Question";
    private static final String TABLE_USER = "User";

    //AnswerPoll Table Columns
    private static final String KEY_ANSWERPOLL_USER = "User";
    private static final String KEY_ANSWERPOLL_AUTHOR = "Author";
    private static final String KEY_ANSWERPOLL_DATE = "Date";
    private static final String KEY_ANSWERPOLL_CHOICE = "Choice";
    private static final String KEY_ANSWERPOLL_SCORE = "Score";

    //AnswerQuestion Table Columns
    private static final String KEY_ANSWERQUESTION_AUTHOR = "Author";
    private static final String KEY_ANSWERQUESTION_DATE = "Date";
    private static final String KEY_ANSWERQUESTION_POSITION = "Position";
    private static final String KEY_ANSWERQUESTION_QUESTIONPOSITION = "QPos";
    private static final String KEY_ANSWERQUESTION_USER = "User";

    //ChoicePoll Table Columns
    private static final String KEY_CHOICEPOLL_AUTHOR = "Author";
    private static final String KEY_CHOICEPOLL_DATE = "Date";
    private static final String KEY_CHOICEPOLL_TEXT = "Text";
    private static final String KEY_CHOICEPOLL_POSITION = "Position";

    //ChoiceQuestion Table Columns
    private static final String KEY_CHOICEQUESTION_POSITION = "Position";
    private static final String KEY_CHOICEQUESTION_AUTHOR = "Author";
    private static final String KEY_CHOICEQUESTION_DATE = "Date";
    private static final String KEY_CHOICEQUESTION_TEXT = "Text";
    private static final String KEY_CHOICEQUESTION_QUESTIONPOSITION = "QPos";

    //FriendRelation Table Columns
    private static final String KEY_FRIENDRELATION_SENDER = "Sender";
    private static final String KEY_FRIENDRELATION_RECEIVER = "Receiver";
    private static final String KEY_FRIENDRELATION_STATUS = "Status";

    //MCQ Table Columns
    private static final String KEY_MCQ_AUTHOR = "Author";
    private static final String KEY_MCQ_DATE = "Date";
    private static final String KEY_MCQ_TITLE = "Title";
    private static final String KEY_MCQ_FORMAT = "Format";
    private static final String KEY_MCQ_ISCLOSED = "IsClosed";
    private static final String KEY_MCQ_NUMBERQUESTION = "NbrQ";

    //ParticipationPoll Table Columns
    private static final String KEY_PARTICIPATIONPOLL_USER = "User";
    private static final String KEY_PARTICIPATIONPOLL_AUTHOR = "Author";
    private static final String KEY_PARTICIPATIONPOLL_DATE = "Date";

    //ParticipationQuestion Table Columns
    private static final String KEY_PARTICIPATIONQUESTION_USER = "User";
    private static final String KEY_PARTICIPATIONQUESTION_AUTHOR = "Author";
    private static final String KEY_PARTICIPATIONQUESTION_DATE = "Date";

    //Poll Table Columns
    private static final String KEY_POLL_AUTHOR = "Author";
    private static final String KEY_POLL_DATE = "Date";
    private static final String KEY_POLL_TITLE = "Title";
    private static final String KEY_POLL_QUESTION = "Question";
    private static final String KEY_POLL_FORMAT = "Format";
    private static final String KEY_POLL_NUMBERTOP = "NbrTop";
    private static final String KEY_POLL_NUMBERCHOICE = "NbrChoice";
    private static final String KEY_POLL_ISPOLL = "IsPoll";
    private static final String KEY_POLL_ISCLOSED = "IsClosed";

    //Question Table Columns
    private static final String KEY_QUESTION_AUTHOR = "Author";
    private static final String KEY_QUESTION_DATE = "Date";
    private static final String KEY_QUESTION_DESCRIPTION = "Description";
    private static final String KEY_QUESTION_POSITION = "Position";
    private static final String KEY_QUESTION_RIGHTANSWER = "RightAnswer";

    // User Table Columns
    private static final String KEY_USER_ID = "ID";
    private static final String KEY_USER_SURNAME = "Surname";
    private static final String KEY_USER_FIRSTNAME = "FirstName";
    private static final String KEY_USER_LOGIN = "Login";
    private static final String KEY_USER_PASSWORD = "Password";
    private static final String KEY_USER_MAIL = "Mail";
    private static final String KEY_USER_PICTURE = "ProfilePicture";
    private static final String KEY_USER_BESTFRIEND = "BF";


    /* Tous les getters pour avoir le nom des tables */


    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getTableAnswerpoll() {
        return TABLE_ANSWERPOLL;
    }

    public static String getTableAnswerquestion() {
        return TABLE_ANSWERQUESTION;
    }

    public static String getTableChoicepoll() {
        return TABLE_CHOICEPOLL;
    }

    public static String getTableChoicequestion() {
        return TABLE_CHOICEQUESTION;
    }

    public static String getTableFriendrelation() {
        return TABLE_FRIENDRELATION;
    }

    public static String getTableMcq() {
        return TABLE_MCQ;
    }

    public static String getTableParticipationpoll() {
        return TABLE_PARTICIPATIONPOLL;
    }

    public static String getTableParticipationquestion() {
        return TABLE_PARTICIPATIONQUESTION;
    }

    public static String getTablePoll() {
        return TABLE_POLL;
    }

    public static String getTableQuestion() {
        return TABLE_QUESTION;
    }

    public static String getTableUser() {
        return TABLE_USER;
    }

    public static String getKeyAnswerpollUser() {
        return KEY_ANSWERPOLL_USER;
    }

    public static String getKeyAnswerpollAuthor() {
        return KEY_ANSWERPOLL_AUTHOR;
    }

    public static String getKeyAnswerpollDate() {
        return KEY_ANSWERPOLL_DATE;
    }

    public static String getKeyAnswerpollChoice() {
        return KEY_ANSWERPOLL_CHOICE;
    }

    public static String getKeyAnswerpollScore() {
        return KEY_ANSWERPOLL_SCORE;
    }

    public static String getKeyAnswerquestionAuthor() {
        return KEY_ANSWERQUESTION_AUTHOR;
    }

    public static String getKeyAnswerquestionDate() {
        return KEY_ANSWERQUESTION_DATE;
    }

    public static String getKeyAnswerquestionPosition() {
        return KEY_ANSWERQUESTION_POSITION;
    }

    public static String getKeyAnswerquestionQuestionposition() {
        return KEY_ANSWERQUESTION_QUESTIONPOSITION;
    }

    public static String getKeyAnswerquestionUser() {
        return KEY_ANSWERQUESTION_USER;
    }

    public static String getKeyChoicepollAuthor() {
        return KEY_CHOICEPOLL_AUTHOR;
    }

    public static String getKeyChoicepollDate() {
        return KEY_CHOICEPOLL_DATE;
    }

    public static String getKeyChoicepollText() {
        return KEY_CHOICEPOLL_TEXT;
    }

    public static String getKeyChoicepollPosition() {
        return KEY_CHOICEPOLL_POSITION;
    }

    public static String getKeyChoicequestionPosition() {
        return KEY_CHOICEQUESTION_POSITION;
    }

    public static String getKeyChoicequestionAuthor() {
        return KEY_CHOICEQUESTION_AUTHOR;
    }

    public static String getKeyChoicequestionDate() {
        return KEY_CHOICEQUESTION_DATE;
    }

    public static String getKeyChoicequestionText() {
        return KEY_CHOICEQUESTION_TEXT;
    }

    public static String getKeyChoicequestionQuestionposition() {
        return KEY_CHOICEQUESTION_QUESTIONPOSITION;
    }

    public static String getKeyFriendrelationSender() {
        return KEY_FRIENDRELATION_SENDER;
    }

    public static String getKeyFriendrelationReceiver() {
        return KEY_FRIENDRELATION_RECEIVER;
    }

    public static String getKeyFriendrelationStatus() {
        return KEY_FRIENDRELATION_STATUS;
    }

    public static String getKeyMcqAuthor() {
        return KEY_MCQ_AUTHOR;
    }

    public static String getKeyMcqDate() {
        return KEY_MCQ_DATE;
    }

    public static String getKeyMcqTitle() {
        return KEY_MCQ_TITLE;
    }

    public static String getKeyMcqFormat() {
        return KEY_MCQ_FORMAT;
    }

    public static String getKeyMcqIsclosed() {
        return KEY_MCQ_ISCLOSED;
    }

    public static String getKeyMcqNumberquestion() {
        return KEY_MCQ_NUMBERQUESTION;
    }

    public static String getKeyParticipationpollUser() {
        return KEY_PARTICIPATIONPOLL_USER;
    }

    public static String getKeyParticipationpollAuthor() {
        return KEY_PARTICIPATIONPOLL_AUTHOR;
    }

    public static String getKeyParticipationpollDate() {
        return KEY_PARTICIPATIONPOLL_DATE;
    }

    public static String getKeyParticipationquestionUser() {
        return KEY_PARTICIPATIONQUESTION_USER;
    }

    public static String getKeyParticipationquestionAuthor() {
        return KEY_PARTICIPATIONQUESTION_AUTHOR;
    }

    public static String getKeyParticipationquestionDate() {
        return KEY_PARTICIPATIONQUESTION_DATE;
    }

    public static String getKeyPollAuthor() {
        return KEY_POLL_AUTHOR;
    }

    public static String getKeyPollDate() {
        return KEY_POLL_DATE;
    }

    public static String getKeyPollTitle() {
        return KEY_POLL_TITLE;
    }

    public static String getKeyPollQuestion() {
        return KEY_POLL_QUESTION;
    }

    public static String getKeyPollFormat() {
        return KEY_POLL_FORMAT;
    }

    public static String getKeyPollNumbertop() {
        return KEY_POLL_NUMBERTOP;
    }

    public static String getKeyPollNumberchoice() {
        return KEY_POLL_NUMBERCHOICE;
    }

    public static String getKeyPollIspoll() {
        return KEY_POLL_ISPOLL;
    }

    public static String getKeyPollIsclosed() {
        return KEY_POLL_ISCLOSED;
    }

    public static String getKeyQuestionAuthor() {
        return KEY_QUESTION_AUTHOR;
    }

    public static String getKeyQuestionDate() {
        return KEY_QUESTION_DATE;
    }

    public static String getKeyQuestionDescription() {
        return KEY_QUESTION_DESCRIPTION;
    }

    public static String getKeyQuestionPosition() {
        return KEY_QUESTION_POSITION;
    }

    public static String getKeyQuestionRightanswer() {
        return KEY_QUESTION_RIGHTANSWER;
    }

    public static String getKeyUserId() {
        return KEY_USER_ID;
    }

    public static String getKeyUserSurname() {
        return KEY_USER_SURNAME;
    }

    public static String getKeyUserFirstname() {
        return KEY_USER_FIRSTNAME;
    }

    public static String getKeyUserLogin() {
        return KEY_USER_LOGIN;
    }

    public static String getKeyUserPassword() {
        return KEY_USER_PASSWORD;
    }

    public static String getKeyUserMail() {
        return KEY_USER_MAIL;
    }

    public static String getKeyUserPicture() {
        return KEY_USER_PICTURE;
    }

    public static String getKeyUserBestfriend() {
        return KEY_USER_BESTFRIEND;
    }
}
