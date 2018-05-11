package be.lsinf1225gr12.minipoll.minipoll.model;

import java.io.Serializable;

/**
 * Created by augus on 09/05/2018.
 */

public class MCQAnswer implements Serializable {
    /**
     * Description du MCQAnswer
     */
    private String description;

    public MCQAnswer (String title)
    {
        this.description= title;
    }

}
