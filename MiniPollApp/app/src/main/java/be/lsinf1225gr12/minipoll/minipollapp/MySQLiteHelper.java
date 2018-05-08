package be.lsinf1225gr12.minipoll.minipollapp;

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

//nom des classes ressources
import be.lsinf1225gr12.minipoll.minipollapp.AssociationEval;
import be.lsinf1225gr12.minipoll.minipollapp.AssociationMCQ;
import be.lsinf1225gr12.minipoll.minipollapp.MCQ;
import be.lsinf1225gr12.minipoll.minipollapp.MCQAnswer;
import be.lsinf1225gr12.minipoll.minipollapp.Poll;
import be.lsinf1225gr12.minipoll.minipollapp.PollAbstract;
import be.lsinf1225gr12.minipoll.minipollapp.PollAnswer;
import be.lsinf1225gr12.minipoll.minipollapp.Question;
import be.lsinf1225gr12.minipoll.minipollapp.User;
/*
classe générale pour accès à la DB.
Pour plus de détails sur le fonctionnement, consulter https://github.com/codepath/android_guides/wiki/Local-Databases-with-SQLiteOpenHelper
*/
public class MySQLiteHelper extends SQLiteOpenHelper{
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
    private static final String KEY_PARTICIPATIONQUESTION_USER ="USER";
    private static final String KEY_PARTICIPATIONQUESTION_AUTHOR ="AUTHOR";
    private static final String KEY_PARTICIPATIONQUESTION_DATE ="DATE";

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
    private static final String KEY_QUESTION_AUTHOR ="AUTHOR";
    private static final String KEY_QUESTION_DATE ="DATE";
    private static final String KEY_QUESTION_DESCRIPTION ="DESCRIPTION";
    private static final String KEY_QUESTION_POSITION ="POSITION";
    private static final String KEY_QUESTION_RIGHTANSWER ="RIGHTANSWER";

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
                KEY_ANSWERPOLL_USER + " text references "+ TABLE_USER +" ("+ KEY_USER_ID +") not null,\n" +
                KEY_ANSWERPOLL_AUTHOR + " text not null,\n" +
                KEY_ANSWERPOLL_DATE + " datetime not null,\n" +
                KEY_ANSWERPOLL_CHOICE + " text not null,\n" +
                KEY_ANSWERPOLL_SCORE + " int not null,\n" +
                "foreign key (\n" +
                KEY_ANSWERPOLL_AUTHOR + ",\n" +
                KEY_ANSWERPOLL_DATE + ",\n" +
                KEY_ANSWERPOLL_CHOICE + "\n" +
                ")\n" +
                "references " + TABLE_CHOICEPOLL + " (\n" +
                KEY_CHOICEPOLL_AUTHOR + ",\n" +
                KEY_CHOICEPOLL_DATE + ",\n" +
                KEY_CHOICEPOLL_TEXT +
                ")\n" +
                ")";

        String CREATE_ANSWERQUESTION_TABLE = "CREATE TABLE " + TABLE_ANSWERQUESTION +
                "(\n" +
                KEY_ANSWERQUESTION_AUTHOR + " text not null,\n" +
                KEY_ANSWERQUESTION_DATE + " datetime not null,\n" +
                KEY_ANSWERQUESTION_POSITION + "int not null,\n" +
                KEY_ANSWERQUESTION_QUESTIONPOSITION + "int not null,\n" +
                KEY_ANSWERQUESTION_USER + " references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                "foreign key (\n" +
                KEY_ANSWERQUESTION_AUTHOR + ",\n" +
                KEY_ANSWERQUESTION_DATE + ",\n" +
                KEY_ANSWERQUESTION_POSITION + ",\n" +
                KEY_ANSWERQUESTION_QUESTIONPOSITION + "\n" +
                ")\n" +
                "references " + TABLE_CHOICEQUESTION +" (\n" +
                KEY_CHOICEQUESTION_AUTHOR + ",\n" +
                KEY_CHOICEQUESTION_DATE + ",\n" +
                KEY_CHOICEQUESTION_POSITION + ",\n" +
                KEY_CHOICEQUESTION_QUESTIONPOSITION +
                ")\n" +
                ")";

        String CREATE_CHOICEPOLL_TABLE = "CREATE TABLE " + TABLE_CHOICEPOLL +
                "(\n" +
                KEY_CHOICEPOLL_AUTHOR + " text not null,\n" +
                KEY_CHOICEPOLL_DATE + " datetime not null,\n" +
                KEY_CHOICEPOLL_TEXT + " text not null,\n" +
                KEY_CHOICEPOLL_POSITION + " int not null,\n" +
                "primary key (\n" +
                KEY_CHOICEPOLL_AUTHOR + ",\n" +
                KEY_CHOICEPOLL_DATE + ",\n" +
                KEY_CHOICEPOLL_TEXT +
                "),\n" +
                "foreign key (\n" +
                KEY_CHOICEPOLL_AUTHOR + ",\n" +
                KEY_CHOICEPOLL_DATE + ",\n" +
                ")\n" +
                "references " + TABLE_POLL + " (" +
                KEY_POLL_AUTHOR + ",\n" +
                KEY_POLL_DATE +
                ")\n" +
                ")";

        String CREATE_CHOICEQUESTION_TABLE = "CREATE TABLE " + TABLE_CHOICEQUESTION +
                "(\n" +
                KEY_CHOICEQUESTION_POSITION + " int not null,\n" +
                KEY_CHOICEQUESTION_AUTHOR + " text not null,\n" +
                KEY_CHOICEQUESTION_DATE + " datetime not null,\n" +
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
                KEY_FRIENDRELATION_SENDER + " text references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_FRIENDRELATION_SENDER + " text references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_FRIENDRELATION_STATUS + " text not null\n" +
                ")";

        String CREATE_MCQ_TABLE = "CREATE TABLE " + TABLE_MCQ +
                "(\n" +
                KEY_MCQ_AUTHOR + " text references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_MCQ_DATE + " datetime not null,\n" +
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
                KEY_PARTICIPATIONPOLL_USER + " text references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_PARTICIPATIONPOLL_AUTHOR + " text not null,\n" +
                KEY_PARTICIPATIONPOLL_DATE + " datetime not null,\n" +
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
                KEY_PARTICIPATIONQUESTION_USER + " text references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_PARTICIPATIONQUESTION_AUTHOR + " text not null,\n" +
                KEY_PARTICIPATIONQUESTION_DATE + " datetime not null,\n" +
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
                KEY_POLL_AUTHOR + " text references " + TABLE_USER + " (" + KEY_USER_ID + ") not null,\n" +
                KEY_POLL_DATE + " datetime not null,\n" +
                KEY_POLL_TITLE + " text not null,\n" +
                KEY_POLL_QUESTION + " text not null,\n" +
                KEY_POLL_FORMAT + " text not null,\n" +
                KEY_POLL_NUMBERTOP + " text not null,\n" +
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
                KEY_QUESTION_AUTHOR + " text not null,\n" +
                KEY_QUESTION_DATE + " datetime not null,\n" +
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
                "references " + TABLE_MCQ + "("+
                KEY_MCQ_AUTHOR + ",\n" +
                KEY_MCQ_DATE +
                ")\n" +
                ")";

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                "(\n" +
                KEY_USER_ID + " text primary key not null,\n" +
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


/*========================================================================================================================
USER METHODS
*/

    /*
    compte le nombre total d'utilisateurs
    */
    public long getUserCount() {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement request = db.compileStatement("SELECT COUNT(*) FROM " +
                TABLE_USER + ";");
        long count = request.simpleQueryForLong();

        return count;
    }

    /*
    ajoute un utilisateur a la DB
    */
    public boolean addOrUpdateUser(User user) {
        // The database connection is cached so it's not expensive to call getWriteableDatabase() multiple times.
        SQLiteDatabase db = getWritableDatabase();
        boolean succeed = false;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USER_ID, user.getId());
            values.put(KEY_USER_BESTFRIEND, user.getBestFriendId());
            values.put(KEY_USER_FIRSTNAME, user.getFirstName());
            values.put(KEY_USER_LOGIN, user.getLogin());
            values.put(KEY_USER_MAIL, user.getMail());
            values.put(KEY_USER_PASSWORD, user.getPassword());
            values.put(KEY_USER_PICTURE, user.getPicture());
            values.put(KEY_USER_SURNAME, user.getName());

            Log.d("user id", user.getId() + "");

            // First try to update the user in case the user already exists in the database
            // This assumes user id's are unique
            int rows = db.update(TABLE_USER, values, KEY_USER_ID + "= ?", new String[]{user.getId()});

            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_USER_ID, TABLE_USER, KEY_USER_SURNAME);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{ String.valueOf(user.getId()) });
                try {
                    if (cursor.moveToFirst()) {
                        succeed = true;
                        db.setTransactionSuccessful();
                    }
                }
                finally{
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
            else {
                // user with this ID did not already exist, so insert new user
                db.insertOrThrow(TABLE_USER, null, values);
                succeed=true;
                db.setTransactionSuccessful();
            }
        }
        catch (Exception e) {
            Log.d("ERROR", "Error while trying to add or update user " + e.toString());
        }
        finally{
            db.endTransaction();
        }
        return succeed;
    }

    /*
    donne tous les utilisateurs
    */
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getString(0));
                user.setLogin(cursor.getString(1));
                user.setFirstName(cursor.getString(2));
                user.setName(cursor.getString(3));
                user.setMail(cursor.getString(4));
                user.setPassword(cursor.getString(5));
                user.setPicture(cursor.getString(6));
                user.setBestFriend(getUser(cursor.getString(7)));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        // return contact list
        return userList;
    }

    /**
     * Donne les informations propres à un User en passant son ID
     */
    public User getUser(String userID) {

        User user = new User();

        String PREF_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %s",
                        TABLE_USER,
                        KEY_USER_ID,
                        userID);
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(PREF_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                user.setId(cursor.getString(cursor.getColumnIndex(KEY_USER_ID)));
                user.setLogin(cursor.getString(cursor.getColumnIndex(KEY_USER_LOGIN)));
                user.setName(cursor.getString(cursor.getColumnIndex(KEY_USER_SURNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD)));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_USER_FIRSTNAME)));
                user.setMail(cursor.getString(cursor.getColumnIndex(KEY_USER_MAIL)));
                user.setPicture(cursor.getString(cursor.getColumnIndex(KEY_USER_PICTURE)));
            }
        }
        catch (Exception e) {
            Log.d("PREFERENCE ERROR", "Error while trying to get one user from database");
        }
        finally{
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return user;
    }

    /**
     renvoie la liste d'amis d'un utilisateur
     */
    public List<User> getFriends(User user){
        String selectQuery = "select * from " + TABLE_FRIENDRELATION +" R where R."+ KEY_FRIENDRELATION_SENDER +"=ID and R."+ KEY_FRIENDRELATION_STATUS +"=’Friend’ or R."+ KEY_FRIENDRELATION_RECEIVER +"=ID and R."+KEY_FRIENDRELATION_STATUS+"=’Friend’";

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //boucle sur les elements et ajout des amis dans la liste
        if (cursor.moveToFirst()) {
            do {
                String ID = cursor.getString(cursor.getColumnIndex(KEY_FRIENDRELATION_RECEIVER));
                if (!ID.equals(user.getId()))
                    ID = cursor.getString(cursor.getColumnIndex(KEY_FRIENDRELATION_SENDER));
                User friend = getUser(ID);
                userList.add(friend);
            } while (cursor.moveToNext());
        }
        // return contact list
        return userList;
    }

    /*
    * donne la liste d'utilisateurs ayant envoyé une demande d'ami à user
    */
    public List<User> notifications(User user){
        String selectQuery = "select * from "+ TABLE_FRIENDRELATION +" R where R."+ KEY_FRIENDRELATION_RECEIVER +"="+user.getId()+" and R."+ KEY_FRIENDRELATION_STATUS +"=’Pending’";

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //boucle sur les elements et ajout des amis potentiels dans la liste
        if (cursor.moveToFirst()) {
            do {
                String ID = cursor.getString(cursor.getColumnIndex(KEY_FRIENDRELATION_SENDER));
                User friend = getUser(ID);
                userList.add(friend);
            } while (cursor.moveToNext());
        }
        // return contact list
        return userList;
    }

    /*
    *check si le login est disponible
    @post : false : login indisponible
            true : login disponible
    */
    public boolean checkLogin(String login)
    {
        boolean result = true;
        String PREF_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %s",
                        TABLE_USER,
                        KEY_USER_LOGIN,
                        login);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(PREF_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                result=false;
            }
        }
        catch (Exception e) {;}
        finally{
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return result;
    }

    /*
*check si le mail est disponible
@post : false : mail indisponible
        true : mail disponible
*/
    public boolean checkMail(String mail)
    {
        boolean result=true;
        String PREF_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %s",
                        TABLE_USER,
                        KEY_USER_MAIL,
                        mail);
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(PREF_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                result = false;
            }
        }
        catch (Exception e) {;}
        finally{
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return result;
    }

    /*
    accepte un ajout d'ami
    */
    public boolean acceptFriend(User sender, User receiver){
        //check si une invitation a déjà été envoyée (l'ordre de sender et receiver est dont inversé)
        String selectQuery = "select *  from "+ TABLE_FRIENDRELATION +" R  where R."+ KEY_FRIENDRELATION_SENDER +"="+ receiver.getId() +" and R."+ KEY_FRIENDRELATION_RECEIVER +"="+sender.getId() +
                " and R."+ KEY_FRIENDRELATION_STATUS + "= 'Pending'";
        SQLiteDatabase db = this.getWritableDatabase();
        boolean succeed=false;
        db.beginTransaction();
        try
        {
            Cursor cursor = db.rawQuery(selectQuery, null);
            ContentValues values = new ContentValues();
            values.put(KEY_FRIENDRELATION_RECEIVER, receiver.getId());
            values.put(KEY_FRIENDRELATION_SENDER, sender.getId());
            values.put(KEY_FRIENDRELATION_STATUS, "Friend");
            if (cursor.moveToFirst()) //une relation existe déjà
            {
                try {
                    db.update(TABLE_FRIENDRELATION, values, KEY_FRIENDRELATION_RECEIVER + " = ? AND " + KEY_FRIENDRELATION_SENDER + " = ?", new String[]{receiver.getId(), sender.getId()});
                    succeed = true;
                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    Log.d("Friend update", "Error while trying to accept a friend");
                }
                finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    db.endTransaction();
                }
            }
        }
        catch (Exception e)
        {
            Log.d("Friend update", "Error while trying to accept a friend");
        }
        return succeed;
    }

    /*
    envoie une invitation d'ami
    */
    public boolean addFriend(User sender, User receiver)
    {
        //s'assure qu'une relation n'est pas déjà en cours (ils peuvent être déjà amis)
        String selectQuery = "select *  from "+ TABLE_FRIENDRELATION +" R  where R."+ KEY_FRIENDRELATION_SENDER +"="+ receiver.getId() +" and R."+ KEY_FRIENDRELATION_RECEIVER +"="+sender.getId() +
                " or R." + KEY_FRIENDRELATION_SENDER +"="+ sender.getId() +" and R."+ KEY_FRIENDRELATION_RECEIVER +"="+receiver.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        boolean succeed=false;
        db.beginTransaction();
        Cursor cursor=null;
        try
        {
            cursor = db.rawQuery(selectQuery, null);
            ContentValues values = new ContentValues();
            values.put(KEY_FRIENDRELATION_RECEIVER, receiver.getId());
            values.put(KEY_FRIENDRELATION_SENDER, sender.getId());
            values.put(KEY_FRIENDRELATION_STATUS, "Pending");
            if (!(cursor.moveToFirst())) //si pas encore de relation
            {
                try {
                    db.update(TABLE_FRIENDRELATION, values, KEY_FRIENDRELATION_RECEIVER + " = ? AND " + KEY_FRIENDRELATION_SENDER + " = ?", new String[]{receiver.getId(), sender.getId()});
                    succeed = true;
                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    Log.d("Friend update", "Error while trying to add a friend");
                }
                finally {
                    db.endTransaction();
                }
            }
        }
        catch (Exception e)
        {
            Log.d("Friend update", "Error while trying to add a friend");
        }
        finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return succeed;
    }

/*========================================================================================================================
ASSOCIATIONEVAL METHODS
*/
    /*
    check si l'utilisateur a déjà répondu au sondage
     */
    public boolean hasAnswered(User user, Poll poll)
    {
        boolean hasAnswered = false;
        String selectQuery = "select *  from "+ TABLE_ANSWERPOLL + " where " + KEY_ANSWERPOLL_USER + " = " + user.getId() + " and " +
                KEY_ANSWERPOLL_AUTHOR + " = " + poll.getAuthor() + " and " + KEY_ANSWERPOLL_DATE + " = " + poll.getDate();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=null;
        try
        {
            cursor = db.rawQuery(selectQuery, null);
            if (!(cursor.moveToFirst())) //si la table a une entrée de ce type
            {
                hasAnswered = true;
            }
        }
        catch (Exception e)
        {
            Log.d(TABLE_ANSWERPOLL, "Error while checking if someone has answered to a poll");
        }
        finally
        {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return hasAnswered;
    }

    /*
    enregistre une réponse à un sondage
     */
    public void giveAnswer(User user, int position, Poll poll)
    {
        //check si l'utilisateur a déjà répondu au sondage
        if (!hasAnswered(user, poll))
        {
            //ajoute la réponse dans la DB

        }
    }

}




