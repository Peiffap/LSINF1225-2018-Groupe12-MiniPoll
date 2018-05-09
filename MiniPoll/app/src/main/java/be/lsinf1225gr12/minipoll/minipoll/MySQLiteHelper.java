package be.lsinf1225gr12.minipoll.minipoll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
String selection = NOM_COLONNE + " = ? AND " + AUTRE_NOM_COLONNE + " = ?"; //rajouter autant qu'il faut
String[] selectionArgs = new String[]{String.valueOf(VALEUR), String.valueOf(VALEUR)};
cv.update(NIM_TABLE, cv, selection, selectionArgs);
int result = (int) db.insert(NOM_TABLE, null, cv);
if (result==-1)
{
	//erreur dans l'ajout, suppression
}
db.close();

 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "Database";
    private static final int DATABASE_VERSION = 2;

    // Table Names
    private static final String TABLE_ANSWERPOLL = "answerpoll";
    private static final String TABLE_ANSWERQUESTION = "answerquestion";
    private static final String TABLE_CHOICEPOLL = "pollchoice";
    private static final String TABLE_CHOICEQUESTION = "possiblechoice";
    private static final String TABLE_FRIENDRELATION = "friendrelation";
    private static final String TABLE_MCQ = "mcq";
    private static final String TABLE_PARTICIPATIONPOLL = "pollparticipation";
    private static final String TABLE_PARTICIPATIONQUESTION = "questionparticipation";
    private static final String TABLE_POLL = "poll";
    private static final String TABLE_QUESTION = "question";
    private static final String TABLE_USER = "user";

    //AnswerPoll Table Columns
    private static final String KEY_ANSWERPOLL_USER = "USER";
    private static final String KEY_ANSWERPOLL_AUTHOR = "AUTHOR";
    private static final String KEY_ANSWERPOLL_DATE = "DATE";
    private static final String KEY_ANSWERPOLL_CHOICE = "CHOICE";
    private static final String KEY_ANSWERPOLL_SCORE = "SCORE";

    //AnswerQuestion Table Columns
    private static final String KEY_ANSWERQUESTION_AUTHOR = "AUTHOR";
    private static final String KEY_ANSWERQUESTION_DATE = "DATE";
    private static final String KEY_ANSWERQUESTION_POSITION = "POSITION";
    private static final String KEY_ANSWERQUESTION_QUESTIONPOSITION = "QPOS";
    private static final String KEY_ANSWERQUESTION_USER = "USER";

    //ChoicePoll Table Columns
    private static final String KEY_CHOICEPOLL_AUTHOR = "AUTHOR";
    private static final String KEY_CHOICEPOLL_DATE = "DATE";
    private static final String KEY_CHOICEPOLL_TEXT = "CHOICE";
    private static final String KEY_CHOICEPOLL_POSITION = "POSITION";

    //ChoiceQuestion Table Columns
    private static final String KEY_CHOICEQUESTION_POSITION = "POSITION";
    private static final String KEY_CHOICEQUESTION_AUTHOR = "AUTHOR";
    private static final String KEY_CHOICEQUESTION_DATE = "DATE";
    private static final String KEY_CHOICEQUESTION_TEXT = "TEXT";
    private static final String KEY_CHOICEQUESTION_QUESTIONPOSITION = "QPOS";

    //FriendRelation Table Columns
    private static final String KEY_FRIENDRELATION_SENDER = "SENDER";
    private static final String KEY_FRIENDRELATION_RECEIVER = "RECEIVER";
    private static final String KEY_FRIENDRELATION_STATUS = "STATUS";

    //MCQ Table Columns
    private static final String KEY_MCQ_AUTHOR = "AUTHOR";
    private static final String KEY_MCQ_DATE = "DATE";
    private static final String KEY_MCQ_TITLE = "NAME";
    private static final String KEY_MCQ_FORMAT = "FORMAT";
    private static final String KEY_MCQ_ISCLOSED = "ISCLOSED";
    private static final String KEY_MCQ_NUMBERQUESTION = "NUMBERQUESTION";

    //ParticipationPoll Table Columns
    private static final String KEY_PARTICIPATIONPOLL_USER = "USER";
    private static final String KEY_PARTICIPATIONPOLL_AUTHOR = "AUTHOR";
    private static final String KEY_PARTICIPATIONPOLL_DATE = "DATE";

    //ParticipationQuestion Table Columns
    private static final String KEY_PARTICIPATIONQUESTION_USER = "USER";
    private static final String KEY_PARTICIPATIONQUESTION_AUTHOR = "AUTHOR";
    private static final String KEY_PARTICIPATIONQUESTION_DATE = "DATE";

    //Poll Table Columns
    private static final String KEY_POLL_AUTHOR = "AUTHOR";
    private static final String KEY_POLL_DATE = "DATE";
    private static final String KEY_POLL_TITLE = "TITLE";
    private static final String KEY_POLL_QUESTION = "QUESTION";
    private static final String KEY_POLL_FORMAT = "FORMAT";
    private static final String KEY_POLL_NUMBERTOP = "NBRTOP";
    private static final String KEY_POLL_NUMBERCHOICE = "NBRCHOICE";
    private static final String KEY_POLL_ISPOLL = "ISPOLL";
    private static final String KEY_POLL_ISCLOSED = "ISCLOSED";

    //Question Table Columns
    private static final String KEY_QUESTION_AUTHOR = "AUTHOR";
    private static final String KEY_QUESTION_DATE = "DATE";
    private static final String KEY_QUESTION_DESCRIPTION = "DESCRIPTION";
    private static final String KEY_QUESTION_POSITION = "POSITION";
    private static final String KEY_QUESTION_RIGHTANSWER = "RIGHTANSWER";

    // User Table Columns
    private static final String KEY_USER_ID = "ID";
    private static final String KEY_USER_SURNAME = "NAME";
    private static final String KEY_USER_FIRSTNAME = "FIRSTNAME";
    private static final String KEY_USER_LOGIN = "LOGIN";
    private static final String KEY_USER_PASSWORD = "PASSWORD";
    private static final String KEY_USER_MAIL = "MAIL";
    private static final String KEY_USER_PICTURE = "PICTURE";
    private static final String KEY_USER_BESTFRIEND = "BESTFRIEND";

    private static MySQLiteHelper sInstance;

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

    /**
     * Gets an instance of MySQLiteHelper.
     *
     * @return MySQLiteHelper
     */
    public static MySQLiteHelper get() {
        if (sInstance == null) {
            return new MySQLiteHelper(MiniPollApp.getContext());
        }
        return sInstance;
    }

    public static synchronized MySQLiteHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MySQLiteHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ANSWERPOLL_TABLE = "CREATE TABLE " + TABLE_ANSWERPOLL +
                "(\n" +
                KEY_ANSWERPOLL_USER + " int not null references " + TABLE_USER + " (" + KEY_USER_ID + "),\n" +
                KEY_ANSWERPOLL_AUTHOR + " int not null,\n" +
                KEY_ANSWERPOLL_DATE + " int not null,\n" +
                KEY_ANSWERPOLL_CHOICE + " int not null,\n" +
                KEY_ANSWERPOLL_SCORE + " int not null,\n" +
                "foreign key (\n" +
                KEY_ANSWERPOLL_AUTHOR + ",\n" +
                KEY_ANSWERPOLL_DATE + ",\n" +
                KEY_ANSWERPOLL_CHOICE + "\n" +
                ")\n" +
                "references " + TABLE_CHOICEPOLL + " (\n" +
                KEY_CHOICEPOLL_AUTHOR + ",\n" +
                KEY_CHOICEPOLL_DATE + ",\n" +
                KEY_CHOICEPOLL_POSITION +
                ")\n" +
                ")";

        String CREATE_ANSWERQUESTION_TABLE = "CREATE TABLE " + TABLE_ANSWERQUESTION +
                "(\n" +
                KEY_ANSWERQUESTION_AUTHOR + " int not null,\n" +
                KEY_ANSWERQUESTION_DATE + " int not null,\n" +
                KEY_ANSWERQUESTION_POSITION + " int not null,\n" +
                KEY_ANSWERQUESTION_QUESTIONPOSITION + " int not null,\n" +
                KEY_ANSWERQUESTION_USER + " int not null references " + TABLE_USER + " (" + KEY_USER_ID + "),\n" +
                "foreign key (\n" +
                KEY_ANSWERQUESTION_AUTHOR + ",\n" +
                KEY_ANSWERQUESTION_DATE + ",\n" +
                KEY_ANSWERQUESTION_POSITION + ",\n" +
                KEY_ANSWERQUESTION_QUESTIONPOSITION + "\n" +
                ")\n" +
                "references " + TABLE_CHOICEQUESTION + " (\n" +
                KEY_CHOICEQUESTION_AUTHOR + ",\n" +
                KEY_CHOICEQUESTION_DATE + ",\n" +
                KEY_CHOICEQUESTION_POSITION + ",\n" +
                KEY_CHOICEQUESTION_QUESTIONPOSITION +
                ")\n" +
                ")";

        String CREATE_CHOICEPOLL_TABLE = "CREATE TABLE " + TABLE_CHOICEPOLL +
                "(\n" +
                KEY_CHOICEPOLL_AUTHOR + " int not null,\n" +
                KEY_CHOICEPOLL_DATE + " int not null,\n" +
                KEY_CHOICEPOLL_TEXT + " text not null,\n" +
                KEY_CHOICEPOLL_POSITION + " int not null,\n" +
                "primary key (\n" +
                KEY_CHOICEPOLL_AUTHOR + ",\n" +
                KEY_CHOICEPOLL_DATE + ",\n" +
                KEY_CHOICEPOLL_POSITION +
                "),\n" +
                "foreign key (\n" +
                KEY_CHOICEPOLL_AUTHOR + ",\n" +
                KEY_CHOICEPOLL_DATE + "\n" +
                ")\n" +
                "references " + TABLE_POLL + " (" +
                KEY_POLL_AUTHOR + ",\n" +
                KEY_POLL_DATE +
                ")\n" +
                ")";

        String CREATE_CHOICEQUESTION_TABLE = "CREATE TABLE " + TABLE_CHOICEQUESTION +
                "(\n" +
                KEY_CHOICEQUESTION_POSITION + " int not null,\n" +
                KEY_CHOICEQUESTION_AUTHOR + " int not null,\n" +
                KEY_CHOICEQUESTION_DATE + " int not null,\n" +
                KEY_CHOICEQUESTION_TEXT + " text not null,\n" +
                KEY_CHOICEQUESTION_QUESTIONPOSITION + " int not null,\n" +
                "primary key (\n" +
                KEY_CHOICEQUESTION_POSITION + ",\n" +
                KEY_CHOICEQUESTION_AUTHOR + ",\n" +
                KEY_CHOICEQUESTION_DATE + ",\n" +
                KEY_CHOICEQUESTION_QUESTIONPOSITION + "\n" +
                "),\n" +
                "foreign key (\n" +
                KEY_CHOICEQUESTION_POSITION + ",\n" +
                KEY_CHOICEQUESTION_AUTHOR + ",\n" +
                KEY_CHOICEQUESTION_DATE + "\n" +
                ")\n" +
                "references " + TABLE_QUESTION + " (" +
                KEY_QUESTION_POSITION + ",\n" +
                KEY_QUESTION_AUTHOR + ",\n" +
                KEY_QUESTION_DATE +
                ")\n" +
                ")";

        String CREATE_FRIENDRELATION_TABLE = "CREATE TABLE " + TABLE_FRIENDRELATION +
                "(\n" +
                KEY_FRIENDRELATION_SENDER + " int references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_FRIENDRELATION_SENDER + " int references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_FRIENDRELATION_STATUS + " text not null\n" +
                ")";

        String CREATE_MCQ_TABLE = "CREATE TABLE " + TABLE_MCQ +
                "(\n" +
                KEY_MCQ_AUTHOR + " int references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_MCQ_DATE + " int not null,\n" +
                KEY_MCQ_TITLE + " text not null,\n" +
                KEY_MCQ_FORMAT + " text not null,\n" +
                KEY_MCQ_ISCLOSED + " boolean not null,\n" +
                KEY_MCQ_NUMBERQUESTION + " int not null,\n" +
                "primary key (" +
                KEY_MCQ_AUTHOR + ",\n" +
                KEY_MCQ_DATE + "\n" +
                ")\n" +
                ")";

        String CREATE_PARTICIPATIONPOLL_TABLE = "CREATE TABLE " + TABLE_PARTICIPATIONPOLL +
                "(\n" +
                KEY_PARTICIPATIONPOLL_USER + " int not null references " + TABLE_USER + " (" + KEY_USER_ID + "),\n" +
                KEY_PARTICIPATIONPOLL_AUTHOR + " int not null,\n" +
                KEY_PARTICIPATIONPOLL_DATE + " int not null,\n" +
                "foreign key (" +
                KEY_PARTICIPATIONPOLL_AUTHOR + ",\n" +
                KEY_PARTICIPATIONPOLL_DATE + "\n" +
                ")" +
                "references" + TABLE_POLL + " (" +
                KEY_POLL_AUTHOR + ",\n" +
                KEY_POLL_DATE +
                ")\n" +
                ")";

        String CREATE_PARTICIPATIONQUESTION_TABLE = "CREATE TABLE " + TABLE_PARTICIPATIONQUESTION +
                "(\n" +
                KEY_PARTICIPATIONQUESTION_USER + " int not null references " + TABLE_USER + " (" + KEY_USER_ID + "),\n" +
                KEY_PARTICIPATIONQUESTION_AUTHOR + " int not null,\n" +
                KEY_PARTICIPATIONQUESTION_DATE + " int not null,\n" +
                "foreign key (" +
                KEY_PARTICIPATIONQUESTION_AUTHOR + ",\n" +
                KEY_PARTICIPATIONQUESTION_DATE + "\n" +
                ")\n" +
                "references " + TABLE_MCQ + " (" +
                KEY_MCQ_AUTHOR + ",\n" +
                KEY_MCQ_DATE +
                ")\n" +
                ")";

        String CREATE_POLL_TABLE = "CREATE TABLE " + TABLE_POLL +
                "(\n" +
                KEY_POLL_AUTHOR + " int references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_POLL_DATE + " int not null,\n" +
                KEY_POLL_TITLE + " text not null,\n" +
                KEY_POLL_QUESTION + " text not null,\n" +
                KEY_POLL_FORMAT + " text not null,\n" +
                KEY_POLL_NUMBERTOP + " int not null,\n" +
                KEY_POLL_NUMBERCHOICE + " int not null,\n" +
                KEY_POLL_ISPOLL + " boolean not null,\n" +
                KEY_POLL_ISCLOSED + " boolean not null,\n" +
                "primary key (" +
                KEY_POLL_AUTHOR + ",\n" +
                KEY_POLL_DATE + "\n" +
                ")\n" +
                ")";

        String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION +
                "(\n" +
                KEY_QUESTION_AUTHOR + " int not null,\n" +
                KEY_QUESTION_DATE + " int not null,\n" +
                KEY_QUESTION_DESCRIPTION + " text not null,\n" +
                KEY_QUESTION_POSITION + " int not null,\n" +
                KEY_QUESTION_RIGHTANSWER + " int not null,\n" +
                "primary key (" +
                KEY_QUESTION_AUTHOR + ",\n" +
                KEY_QUESTION_DATE + ",\n" +
                KEY_QUESTION_POSITION + ",\n" +
                "),\n" +
                "foreign key (" +
                KEY_QUESTION_AUTHOR + ",\n" +
                KEY_QUESTION_DATE + "\n" +
                ")\n" +
                "references " + TABLE_MCQ + "(" +
                KEY_MCQ_AUTHOR + ",\n" +
                KEY_MCQ_DATE +
                ")\n" +
                ")";

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                "(\n" +
                KEY_USER_ID + " int not null unique primary key,\n" +
                KEY_USER_LOGIN + " text null,\n" +
                KEY_USER_FIRSTNAME + " text not null,\n" +
                KEY_USER_SURNAME + " text not null,\n" +
                KEY_USER_PASSWORD + " text not null,\n" +
                KEY_USER_MAIL + " text not null,\n" +
                KEY_USER_PICTURE + " text,\n" +
                KEY_USER_BESTFRIEND + " text references " + TABLE_USER + " (" + KEY_USER_ID + ")\n" +
                ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ANSWERPOLL_TABLE);
        db.execSQL(CREATE_ANSWERQUESTION_TABLE);
        db.execSQL(CREATE_CHOICEPOLL_TABLE);
        db.execSQL(CREATE_CHOICEQUESTION_TABLE);
        db.execSQL(CREATE_FRIENDRELATION_TABLE);
        db.execSQL(CREATE_MCQ_TABLE);
        db.execSQL(CREATE_PARTICIPATIONPOLL_TABLE);
        db.execSQL(CREATE_PARTICIPATIONQUESTION_TABLE);
        db.execSQL(CREATE_POLL_TABLE);
        db.execSQL(CREATE_QUESTION_TABLE);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERPOLL);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERQUESTION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHOICEPOLL);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHOICEQUESTION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDRELATION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MCQ);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTICIPATIONPOLL);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTICIPATIONQUESTION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POLL);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
            onCreate(db);
        }
    }
}