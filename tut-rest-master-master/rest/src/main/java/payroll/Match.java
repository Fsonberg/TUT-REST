package payroll;

class Match {
    private Long foundID;
    private Long lostID;
    private Long customerID;
    private Long empID;

    Match() {
        this.lostID = lostID;
        this.foundID = foundID;
        this.customerID = customerID;
        this.empID = empID;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
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
