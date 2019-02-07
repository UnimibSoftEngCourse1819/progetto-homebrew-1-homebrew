package model.brew;

import java.util.Date;

public class Brew {
	
	private int brewID;
	private int userID;
	private int recipeID;
	private Date brewDate;
	private String description;
	private int quantity;
	private String tasteNote;
	public Brew(int brewID, int userID, int recipeID, Date brewDate, String description, int quantity,
			String tasteNote) {
		super();
		this.brewID = brewID;
		this.userID = userID;
		this.recipeID = recipeID;
		this.brewDate = brewDate;
		this.description = description;
		this.quantity = quantity;
		this.tasteNote = tasteNote;
	}
	public int getBrewID() {
		return brewID;
	}
	public void setBrewID(int brewID) {
		this.brewID = brewID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}
	public Date getBrewDate() {
		return brewDate;
	}
	public void setBrewDate(Date brewDate) {
		this.brewDate = brewDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTasteNote() {
		return tasteNote;
	}
	public void setTasteNote(String tasteNote) {
		this.tasteNote = tasteNote;
	}

	
	
}
