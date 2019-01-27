package brew;

import java.util.Date;

public class UserBrew {
	private int userId;
	private int brewId;
	private Date brewDate;
	private int quantity;
	
	public UserBrew(int userId, int brewId, Date brewDate, int quantity) {
		super();
		this.userId = userId;
		this.brewId = brewId;
		this.brewDate = brewDate;
		this.quantity = quantity;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBrewId() {
		return brewId;
	}
	public void setBrewId(int brewId) {
		this.brewId = brewId;
	}
	public Date getBrewDate() {
		return brewDate;
	}
	public void setBrewDate(Date brewDate) {
		this.brewDate = brewDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
