package payroll;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class FoundItem {
    private @Id @GeneratedValue Long foundItemID;
    private String category;
    private String brand;
    private String color;
    private Long empID;
    private boolean active;

    FoundItem(String category, String brand, String color, boolean active) {
        this.category = category;
        this.brand = brand;
        this.color = color;
        this.active = active;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getFoundItemID() {
        return foundItemID;
    }

    public void setFoundItemID(Long foundItemID) {
        this.foundItemID = foundItemID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}