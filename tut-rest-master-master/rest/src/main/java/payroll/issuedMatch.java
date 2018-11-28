package payroll;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class issuedMatch {
    private @Id @GeneratedValue Long id;
    private Long lostItemID;
    private Long foundItemID;
    private Long userID;
    //private Long employeeID;

    issuedMatch (Long lostItemID, Long foundItemID, Long userID) {
        this.lostItemID = lostItemID;
        this.foundItemID = foundItemID;
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLostItemID() {
        return lostItemID;
    }

    public void setLostItemID(Long lostItemID) {
        this.lostItemID = lostItemID;
    }

    public Long getFoundItemID() {
        return foundItemID;
    }

    public void setFoundItemID(Long foundItemID) {
        this.foundItemID = foundItemID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
