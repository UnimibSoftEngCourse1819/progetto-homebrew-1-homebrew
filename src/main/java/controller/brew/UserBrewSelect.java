package controller.brew;

import java.util.Date;

public class UserBrewSelect {
	private String brewName;	
	private String recipeName;
	private Date brewDate;
	private int quantity;
	
	
	public UserBrewSelect(String brewName, String recipeName, Date brewDate, int quantity) {
		super();
		this.brewName = brewName;
		this.recipeName = recipeName;
		this.brewDate = brewDate;
		this.quantity = quantity;
	}
	
	public String getBrewName() {
		return brewName;
	}
	public void setBrewName(String brewName) {
		this.brewName = brewName;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
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
