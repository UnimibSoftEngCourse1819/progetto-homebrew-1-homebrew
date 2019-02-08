package model.brew;

import java.util.Date;

public class Brew {

	private int brewID;
	private String name;
	private int userID;
	private String userName;
	private String userSurname;
	private int recipeID;
	private Date brewDate;
	private String description;
	private int quantity;
	private String tasteNote;
	
	public Brew(int brewID, String name, int userID, String userName, String userSurname, int recipeID, Date brewDate,
			String description, int quantity, String tasteNote) {
		super();
		this.brewID = brewID;
		this.name = name;
		this.userID = userID;
		this.userName = userName;
		this.userSurname = userSurname;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
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
