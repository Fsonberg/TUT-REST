package payroll;

class Match {

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
