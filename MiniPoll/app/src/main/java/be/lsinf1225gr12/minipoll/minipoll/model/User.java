package be.lsinf1225gr12.minipoll.minipoll.model;
import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

public class User {

    private int id;
    private String login;
    private String password;
    private String picture;
    private String mail;
    private String firstname;
    private String name;
    private String bestfriend;
    private static SparseArray<User> userSparseArray = new SparseArray<>();

    /**
     * Utilisateur actuellement connecté à l'application. Correspond à null si aucun utilisateur
     * n'est connecté.
     */
    private static User connectedUser = null;


    /* Constructeurs */
    public User(int id , String login, String password, String picture, String mail, String firstname, String name, String bestfriend){
        this.id = id;
        this.login = login;
        this.password = password;
        this.picture = picture;
        this.mail = mail;
        this.firstname = firstname;
        this.name = name;
        this.bestfriend = bestfriend;
        User.userSparseArray.put(id,this);
    }

    /* Getters and setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBestfriend() {
        return bestfriend;
    }

    public void setBestfriend(String bestfriend) {
        this.bestfriend = bestfriend;
    }

    /**
     * Fournit l'utilisateur actuellement connecté.
     */
    public static User getConnectedUser() {
        return User.connectedUser;
    }

    /**
     * Déconnecte l'utilisateur actuellement connecté à l'application.
     */
    public static void logout() {
        User.connectedUser = null;
    }

    /**
     *  Renvoie la liste de tous les utilisateurs
     */
    public static ArrayList<User> getUsers(){
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] colonnes = {MySQLiteHelper.getKeyUserId(), MySQLiteHelper.getKeyUserSurname(), MySQLiteHelper.getKeyUserFirstname(),MySQLiteHelper.getKeyUserLogin(),MySQLiteHelper.getKeyUserPassword(),MySQLiteHelper.getKeyUserMail(),MySQLiteHelper.getKeyUserPicture(),MySQLiteHelper.getKeyUserBestfriend()};
        Cursor cursor = db.query(MySQLiteHelper.getTableUser(), colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int id = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.getKeyUserId()));
            String name = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyUserSurname()));
            String firstname = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyUserFirstname()));
            String login = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyUserLogin()));
            String password = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyUserPassword()));
            String mail = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyUserMail()));
            String picture = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyUserPicture()));
            String bestFriend = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyUserBestfriend()));

            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            User user = User.userSparseArray.get(id);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                user = new User(id, login, password, picture, mail, firstname, name, bestFriend);
            }

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return users;
    }

    /**
     * Connecte l'utilisateur courant.
     *
     * @param passwordToTry le mot de passe entré.
     *
     * @return Vrai (true) si l'utilisateur a l'autorisation de se connecter, false sinon.
     */
    public boolean login(String passwordToTry) {
        if (this.password.equals(passwordToTry)) {
            // Si le mot de passe est correct, modification de l'utilisateur connecté.
            User.connectedUser = this;
            return true;
        }
        return false;
    }

    /**
     * Fournit une représentation textuelle de l'utilisateur courant. (Ici le nom)
     *
     * @note Cette méthode est utilisée par l'adaptateur ArrayAdapter afin d'afficher la liste des
     * utilisateurs. (Voir LoginActivity).
     */
    public String toString() {
        return getName();
    }


    /**
     * Fonction qui renvoie la liste d'amis d'un utilsateur
     * @param utilisateur, l'user dont on veut la liste d'amis
     * @return une ArrayList<User> contenant la liste des amis de utilisateur
     */
    public static ArrayList<User> getFriends(User utilisateur){
        int thisId = utilisateur.getId();

        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String selection = MySQLiteHelper.getKeyFriendrelationStatus() + " = ? AND " + MySQLiteHelper.getKeyFriendrelationReceiver() + " = ?";
        String[] selectionArgs = new String[]{"Friend", String.valueOf(thisId)};

        String[] colonnes = {MySQLiteHelper.getKeyFriendrelationSender()};
        Cursor cursor = db.query(MySQLiteHelper.getTableFriendrelation(), colonnes, selection, selectionArgs, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        ArrayList<String> ids = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int id = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.getKeyFriendrelationReceiver()));

            // Ajout de l'utilisateur à la liste.
            ids.add(String.valueOf(id));

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }
        cursor.close();


        String selection2 = MySQLiteHelper.getKeyFriendrelationStatus() + " = ? AND " + MySQLiteHelper.getKeyFriendrelationSender() + " = ?";
        String[] selectionArgs2 = new String[]{"Friend", String.valueOf(thisId)};

        String[] colonnes2 = {MySQLiteHelper.getKeyFriendrelationReceiver()};
        Cursor cursor2 = db.query(MySQLiteHelper.getTableFriendrelation(), colonnes2, selection2, selectionArgs2, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor2.moveToFirst();

        // Tant qu'il y a des lignes.
        while (!cursor2.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int id = cursor2.getInt(cursor2.getColumnIndex(MySQLiteHelper.getKeyFriendrelationSender()));

            // Ajout de l'utilisateur à la liste.
            ids.add(String.valueOf(id));

            // Passe à la ligne suivante.
            cursor2.moveToNext();
        }

        cursor2.close();

        String selection3 = MySQLiteHelper.getKeyUserId() + " = ?";
        String[] selectionArgs3 = new String[ids.size()];
        selectionArgs3 = ids.toArray(selectionArgs3);

        String[] colonnes3 = {MySQLiteHelper.getKeyUserId(), MySQLiteHelper.getKeyUserSurname(), MySQLiteHelper.getKeyUserFirstname(),MySQLiteHelper.getKeyUserLogin(),MySQLiteHelper.getKeyUserPassword(),MySQLiteHelper.getKeyUserMail(),MySQLiteHelper.getKeyUserPicture(),MySQLiteHelper.getKeyUserBestfriend()};
        Cursor cursor3 = db.query(MySQLiteHelper.getTableUser(), colonnes3, selection3, selectionArgs3, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor3.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor3.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int id = cursor3.getInt(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserId()));
            String name = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserSurname()));
            String firstname = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserFirstname()));
            String login = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserLogin()));
            String password = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserPassword()));
            String mail = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserMail()));
            String picture = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserPicture()));
            String bestFriend = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserBestfriend()));

            User user = new User(id, login, password, picture, mail, firstname, name, bestFriend);

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor3.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor3.close();

        // Fermeture du curseur et de la base de données.

        db.close();

        return users;
    }

    /**
     * Fonction qui permet d'ajouter un user dans la database
     * @param user l'user à ajouter
     */
    public static void addUser(User user){
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyUserId(),user.getId());
        cv.put(MySQLiteHelper.getKeyUserBestfriend(),user.getBestfriend());
        cv.put(MySQLiteHelper.getKeyUserFirstname(),user.getFirstname());
        cv.put(MySQLiteHelper.getKeyUserLogin(),user.getLogin());
        cv.put(MySQLiteHelper.getKeyUserMail(),user.getMail());
        cv.put(MySQLiteHelper.getKeyUserPassword(),user.getPassword());
        cv.put(MySQLiteHelper.getKeyUserPicture(),user.getPicture());
        cv.put(MySQLiteHelper.getKeyUserSurname(),user.getName());
        long result = db.insert(MySQLiteHelper.getTableUser(), null, cv);
        if(result==-1)
        {
            //erreur dans l'ajout, suppression
            //QUE METTRE ICI ?????????
        }

        db.close();
    }

    /**
     * Fonction qui permet d'ajouter un ami
     * @param sender personne qui envoie l'invitation
     * @param receiver personne qui reçoit l'invitation
     */
    public static void addFriend(User sender, User receiver){
        int idSender = sender.getId();
        int idReceiver = receiver.getId();
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyFriendrelationSender(),idSender);
        cv.put(MySQLiteHelper.getKeyFriendrelationReceiver(),idReceiver);
        cv.put(MySQLiteHelper.getKeyFriendrelationStatus(),"Pending");
        long result = db.insert(MySQLiteHelper.getTableFriendrelation(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
            //QUE METTRE ICI ????
        }
        db.close();
    }

    /**
     * Fonction qui permet de supprimer un ami
     * @param user Personne qui supprime son ami
     * @param friendToRemove L'ami qui est supprimé
     */
    public static void removeFriend(User user, User friendToRemove){
        int idUser = user.getId();
        int idRemove = friendToRemove.getId();

    }

    /**
     * Fonction qui permet d'accepter une demande d'ami
     * @param user personne qui accepte la demande
     * @param friendToAccept personne qui lui a envoyé la demande
     */
    public static void acceptFriend(User user, User friendToAccept){
        int idUser = user.getId();
        int idAccept = friendToAccept.getId();
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(MySQLiteHelper.getKeyFriendrelationStatus(),"Friend");
        String selection = MySQLiteHelper.getKeyFriendrelationSender() + " = ? AND " + MySQLiteHelper.getKeyFriendrelationReceiver() + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(idAccept), String.valueOf(idUser)};
        //cv.update(MySQLiteHelper.getTableFriendrelation(), cv, selection, selectionArgs);
        long result = db.insert(MySQLiteHelper.getTableFriendrelation(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();
    }

    /**
     * Fonction qui renvoie la liste des utilisateurs ayant envoyé une demande à user
     * @param utilisateur l'utilisateur dont on regarde la liste de demandes en ami
     * @return liste d'utilisateurs lui envoyant une demande
     */
    public static ArrayList<User> getDemands(User utilisateur){
        int thisId = utilisateur.getId();

        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String selection = MySQLiteHelper.getKeyFriendrelationStatus() + " = ? AND " + MySQLiteHelper.getKeyFriendrelationReceiver() + " = ?";
        String[] selectionArgs = new String[]{"Pending", String.valueOf(thisId)};

        String[] colonnes = {MySQLiteHelper.getKeyFriendrelationSender()};
        Cursor cursor = db.query(MySQLiteHelper.getTableFriendrelation(), colonnes, selection, selectionArgs, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        ArrayList<String> ids = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int id = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.getKeyFriendrelationReceiver()));

            // Ajout de l'utilisateur à la liste.
            ids.add(String.valueOf(id));

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }
        cursor.close();


        String selection3 = MySQLiteHelper.getKeyUserId() + " = ?";
        String[] selectionArgs3 = new String[ids.size()];
        selectionArgs3 = ids.toArray(selectionArgs3);

        String[] colonnes3 = {MySQLiteHelper.getKeyUserId(), MySQLiteHelper.getKeyUserSurname(), MySQLiteHelper.getKeyUserFirstname(),MySQLiteHelper.getKeyUserLogin(),MySQLiteHelper.getKeyUserPassword(),MySQLiteHelper.getKeyUserMail(),MySQLiteHelper.getKeyUserPicture(),MySQLiteHelper.getKeyUserBestfriend()};
        Cursor cursor3 = db.query(MySQLiteHelper.getTableUser(), colonnes3, selection3, selectionArgs3, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor3.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor3.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int id = cursor3.getInt(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserId()));
            String name = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserSurname()));
            String firstname = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserFirstname()));
            String login = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserLogin()));
            String password = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserPassword()));
            String mail = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserMail()));
            String picture = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserPicture()));
            String bestFriend = cursor3.getString(cursor3.getColumnIndex(MySQLiteHelper.getKeyUserBestfriend()));

            User user = new User(id, login, password, picture, mail, firstname, name, bestFriend);

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor3.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor3.close();

        // Fermeture du curseur et de la base de données.

        db.close();

        return users;
    }
}


