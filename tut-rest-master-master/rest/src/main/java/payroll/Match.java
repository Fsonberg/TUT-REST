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
    Long id;
    private Long foundID;
    private Long lostID;
    private Long userID;

    Match() {
        this.lostID = lostID;
        this.foundID = foundID;
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
