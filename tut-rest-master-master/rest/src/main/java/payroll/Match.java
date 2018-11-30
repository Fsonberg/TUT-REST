package payroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Match {

    private @Id
    @GeneratedValue
    Long matchID;
    private Long foundID;
    private Long lostID;
    private Long userID;
    private Long empID;

    Match() {
        this.lostID = lostID;
        this.foundID = foundID;
        this.userID = userID;
        this.empID = empID;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getMatchID() {
        return matchID;
    }

    public void setMatchID(Long matchID) {
        this.matchID = matchID;
    }

    public Long getFoundID() {
        return foundID;
    }

    public void setFoundID(Long foundID) {
        this.foundID = foundID;
    }

    public Long getLostID() {
        return lostID;
    }

    public void setLostID(Long lostID) {
        this.lostID = lostID;
    }
}
