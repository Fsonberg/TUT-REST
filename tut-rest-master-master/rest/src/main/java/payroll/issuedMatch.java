package payroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class IssuedMatch {
    private @Id @GeneratedValue Long issuedMatchID;
    private Long foundID;
    private Long lostID;
    private Long customerID;
    private Long empID;

    public IssuedMatch() {
        this.foundID = foundID;
        this.lostID = lostID;
        this.customerID = customerID;
        this.empID = empID;
    }

    public Long getIssuedMatchID() {
        return issuedMatchID;
    }

    public void setIssuedMatchID(Long issuedMatchID) {
        this.issuedMatchID = issuedMatchID;
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


