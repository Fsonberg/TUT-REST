package payroll;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Match {
    private @Id @GeneratedValue Long issuedMatchID;
    private Long foundItemID;
    private Long lostItemID;
    private Long customerID;
    private Long empID;

    public Match() {
        this.foundItemID = foundItemID;
        this.lostItemID = lostItemID;
        this.customerID = customerID;
        this.empID = empID;
    }

    public Long getIssuedMatchID() {
        return issuedMatchID;
    }

    public void setIssuedMatchID(Long issuedMatchID) {
        this.issuedMatchID = issuedMatchID;
    }

    public Long getFoundItemID() {
        return foundItemID;
    }

    public void setFoundItemID(Long foundItemID) {
        this.foundItemID = foundItemID;
    }

    public Long getLostItemID() {
        return lostItemID;
    }

    public void setLostItemID(Long lostItemID) {
        this.lostItemID = lostItemID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }
}


