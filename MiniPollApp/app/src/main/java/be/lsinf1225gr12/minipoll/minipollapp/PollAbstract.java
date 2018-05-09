package be.lsinf1225gr12.minipoll.minipollapp;

public abstract class PollAbstract {
    /**
     * Statut du Poll
     * true=fermé
     * false=ouvert.
     */
    protected boolean closedStatus;
    /**
     * Format des propositons.
     * Pic si les propositions sont des photos
     * Text si c'est du texte
     */
    protected String format;
    /**
     * Nom du poll courant, titre pour questionnaire.
     */
    protected String name;
    /**
     * Créateur du poll courant.
     */
    protected User author;
    /**
     * Date du Poll courant
     */
    protected String date;

    /**
     * Constructeur du PollAbstract. Initialise une instance de l'utilisateur présent dans la base
     * de données.
     *
     * @note Ce constructeur est privé (donc utilisable uniquement depuis cette classe). Cela permet
     * d'éviter d'avoir deux instances différentes d'un même utilisateur.
     */
    protected PollAbstract(String format, String name, User author, String date) {
        this.closedStatus =false;
        this.format = format;
        this.name = name;
        this.author = author;
        this.date = date;
    }

    /**
     * Fournit le statut du Poll.
     */
    public boolean isClosedStatus() {
        return closedStatus;
    }

    /**
     * Modifie le statut du Poll
     */

    public void setClosedStatus(boolean closedStatus) {
        this.closedStatus = closedStatus;
    }

    /**
     * Fournit le format du Poll
     */

    public String getFormat() {
        return format;
    }
    /**
     * Modifie le format du Poll
     */

    public void setFormat(String format) {
        this.format = format;
    }
    /**
     * Fournit le nom du Poll
     */

    public String getName() {
        return name;
    }
    /**
     * Modifie le nom du Poll
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Fournit l'auteur du Poll
     */

    public User getAuthor() {
        return author;
    }
    /**
     * Modifie l'auteur du Poll
     */

    public void setAuthor(User author) {
        this.author = author;
    }
    /**
     * Fournit la date du Poll
     */

    public String getDate() {
        return date;
    }
    /**
     * Modifie la date du Poll
     */

    public void setDate(String date) {
        this.date = date;
    }
}
