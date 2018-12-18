package payroll;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class LostItem {
	private @Id @GeneratedValue Long lostItemID;
	private String category;
	private String brand;
	private String color;
	private Long customerID;
	private boolean active;

	LostItem(String category, String brand, String color, boolean active) {
		this.category = category;
		this.brand = brand;
		this.color = color;
		this.active = active;
	}

	public Long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getLostItemID() {
		return lostItemID;
	}
	public void setLostItemID(Long lostItemID) {
		this.lostItemID = lostItemID;
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

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}