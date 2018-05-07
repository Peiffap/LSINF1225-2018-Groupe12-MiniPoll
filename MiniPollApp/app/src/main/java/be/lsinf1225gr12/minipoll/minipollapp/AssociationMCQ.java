package be.lsinf1225gr12.minipoll.minipollapp;

public class AssociationMCQ {

    private int position;
    private User user;
    private MCQ mcq;
    private MCQAnswer mcqAnswer;
    private Question question;

    public AssociationMCQ(int position, User user, MCQ mcq, MCQAnswer mcqAnswer, Question question) {
        this.position = position;
        this.user = user;
        this.mcq = mcq;
        this.mcqAnswer = mcqAnswer;
        this.question = question;
        this.user.addAssociationMCQ(this);
        this.mcq.addAssociationMCQ(this);
        this.mcqAnswer.addAssociationMCQ(this);
        this.question.addAssociationMCQ(this);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.deleteAssociationMCQ(this);
        this.user = user;
        this.user.addAssociationMCQ(this);
    }

    public MCQ getMcq() {
        return mcq;
    }

    public void setMcq(MCQ mcq) {
        this.mcq.deleteAssociationMCQ(this);
        this.mcq = mcq;
        this.mcq.addAssociationMCQ(this);
    }

    public MCQAnswer getMcqAnswer() {
        return mcqAnswer;
    }

    public void setMcqAnswer(MCQAnswer mcqAnswer) {
        this.mcqAnswer.deleteAssociationMCQ(this);
        this.mcqAnswer = mcqAnswer;
        this.mcqAnswer.addAssociationMCQ(this);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question.deleteAssociationMCQ(this);
        this.question = question;
        this.question.addAssociationMCQ(this);
    }

    public void delete(){
        this.user.deleteAssociationMCQ(this);
        this.mcq.deleteAssociationMCQ(this);
        this.mcqAnswer.deleteAssociationMCQ(this);
        this.question.deleteAssociationMCQ(this);
    }

    /* La fonction giveAnswer initialement imaginée est en fait le constructeur de cette fonction !
    * En effet elle sert à créer le lien entre les 4 objets ainsi que la position que l'utilisateur
    * a donné à cette réponse*/
}
