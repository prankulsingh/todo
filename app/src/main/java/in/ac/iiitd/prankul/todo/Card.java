package in.ac.iiitd.prankul.todo;

import java.io.Serializable;

/**
 * Created by Prankul on 08-11-2016.
 */

public class Card implements Serializable {
    String title, details;

    public Card(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
