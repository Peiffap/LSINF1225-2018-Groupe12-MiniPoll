package be.lsinf1225gr12.minipoll.minipollapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;


public class User
{
    /* Variables de la classe */
    private List<AssociationEval> listAssociationEval;
    private List<AssociationMCQ> listAssociationMCQ;
    private int id;
    private String login;
    private String password;
    private String picture;
    private String mail;
    private String firstname;
    private String name;
    private User bestFriend;

    private static final String DB_COLUMN_ID = "u_id";
    private static final String DB_COLUMN_NAME = "u_name";
    private static final String DB_COLUMN_PASSWORD = "u_password";
    private static final String DB_TABLE = "users";

    /**
     * Contient les instances déjà existantes des utilisateurs afin d'éviter de créer deux instances
     * du même utilisateur.
     */
    private static SparseArray<User> userSparseArray = new SparseArray<>();



    /* Constructeurs */
    public User(){

    }

    /**
     * Constructeur de l'utilisateur. Initialise une instance de l'utilisateur présent dans la base
     * de données.
     *
     * @note Ce constructeur est privé (donc utilisable uniquement depuis cette classe). Cela permet
     * d'éviter d'avoir deux instances différentes d'un même utilisateur.
     */
    private User(int uId, String uName, String uPassword) {

        this.id = uId;
        this.name = uName;
        this.password = uPassword;
        User.userSparseArray.put(uId, this);
    }

    public User(int id , String login, String password, String picture, String mail, String firstname, String name){
        this.id = id;
        this.login = login;
        this.password = password;
        this.picture = picture;
        this.mail = mail;
        this.firstname = firstname;
        this.name = name;
        this.listAssociationMCQ = new ArrayList<AssociationMCQ>();
        this.listAssociationEval = new ArrayList<AssociationEval>();
        this.bestFriend=null;
    }

    /* Getters and setters */

    public User getBestFriend(){return bestFriend;}

    public int getBestFriendId(){return bestFriend.getId();}

    public void setBestFriend(User bestFriend) {this.bestFriend=bestFriend;}

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

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* Functions qui check un bon identifiant */
    public boolean connexion(String typedLogin, String typedPassword){
        return (this.login.equals(typedLogin) && this.password.equals(typedPassword));
    }

    public boolean checkLogin(String typedLogin){
        return (this.login.equals(typedLogin));
    }

    public boolean checkMail(String typedMail){
        return (this.mail.equals(typedMail));
    }

    /**
     * Retourne la liste des utilisateurs qui ont envoyé une demande
     * TODO
     */
    public List<User> notifications(){
        List<User> list = null;
        return list;
    }

    /**
     * Fonction qui envoie une demande à un ami
     * Renvoie true si la demande a bien été envoyée
     * Renvoie false si une erreur s'est produite (ex: user n'existe pas, déjà ami, ...)
     * TODO
     */
    public boolean addFriend(User user){
        return false;
    }

    /**
     * Fonction qui renvoie la liste d'amis de l'user
     * TODO
     */
    public List<User> friendList(){
        List<User> list = null;
        return list;
    }

    /**
     * Aucune idée de à quoi ça sert ça retourne rien
     * TODO
     */
    public void viewFriendList(List<User> list){

    }

    /**
     * TODO
     */
    public void computePollResult(PollAbstract pollAbstract){

    }

    /**
     * Fonction qui renvoie la liste de tous les utilisateurs
     * TODO
     */
    public static ArrayList<User> getUtilisateurs() {
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_ID, DB_COLUMN_NAME, DB_COLUMN_PASSWORD};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int uId = cursor.getInt(0);
            String uNom = cursor.getString(1);
            String uPassword = cursor.getString(2);

            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            User user = User.userSparseArray.get(uId);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                user = new User(uId, uNom, uPassword);
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

    /* Nécessaire au bon fonctionnement de AssociationMCQ */
    public void addAssociationMCQ(AssociationMCQ associationMCQ){
        this.listAssociationMCQ.add(associationMCQ);
    }
    public void deleteAssociationMCQ(AssociationMCQ associationMCQ){
        this.listAssociationMCQ.remove(associationMCQ);
    }
    public List<AssociationMCQ> getListAssociationMCQ(){
        return this.listAssociationMCQ;
    }
    /* Nécessaire au bon fonctionnement de AssociationEval */
    public void addAssociationEval(AssociationEval associationEval){
        this.listAssociationEval.add(associationEval);
    }
    public void deleteAssociationEval(AssociationEval associationEval){
        this.listAssociationEval.remove(associationEval);
    }
    public List<AssociationEval> getListAssociationEval(){
        return this.listAssociationEval;
    }
}
