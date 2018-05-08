package be.lsinf1225gr12.minipoll.minipollapp;

public class AssociationEval {

    private User user;
    private Poll poll;
    private PollAnswer pollAnswer;
    private int position;

    public AssociationEval( int position, User user, Poll poll, PollAnswer pollAnswer) {

        this.position = position;
        this.user = user;
        this.poll = poll;
        this.pollAnswer = pollAnswer;
        this.user.addAssociationEval(this);
        this.poll.addAssociationEval(this);
        this.pollAnswer.addAssociationEval(this);

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
        this.user.deleteAssociationEval(this);
        this.user = user;
        this.user.addAssociationEval(this);
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll.deleteAssociationEval(this);
        this.poll = poll;
        this.poll.addAssociationEval(this);
    }

    public PollAnswer getPollAnswer() {
        return pollAnswer;
    }

    public void setPollAnswer(PollAnswer pollAnswer) {
        this.pollAnswer.deleteAssociationEval(this);
        this.pollAnswer = pollAnswer;
        this.pollAnswer.addAssociationEval(this);
    }



    public void delete(){
        this.user.deleteAssociationEval(this);
        this.poll.deleteAssociationEval(this);
        this.pollAnswer.deleteAssociationEval(this);
    }


}

