package payroll;

import org.springframework.context.annotation.Condition;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

public class MatchItem  {

    private @Id @GeneratedValue Long id;

    private ArrayList<Long> LostID = new ArrayList<Long>();
    private ArrayList<Long> FoundID = new ArrayList<Long>();

    public MatchItem(ArrayList<Long> lostID, ArrayList<Long> foundID) {
        this.LostID = lostID;
        this.FoundID = foundID;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Long> getLostID() {
        return LostID;
    }

    public void setLostID(ArrayList<Long> lostID) {
        LostID = lostID;
    }

    public ArrayList<Long> getFoundID() {
        return FoundID;
    }

    public void setFoundID(ArrayList<Long> foundID) {
        FoundID = foundID;
    }


}
