package be.lsinf1225gr12.minipoll.minipollapp;

import java.util.ArrayList;
import java.util.List;

public class MCQAnswer {
    private String description;
    private List<AssociationMCQ> listAssociationMCQ;

    public MCQAnswer(String description) {
        this.description = description;
        this.listAssociationMCQ = new ArrayList<AssociationMCQ>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
