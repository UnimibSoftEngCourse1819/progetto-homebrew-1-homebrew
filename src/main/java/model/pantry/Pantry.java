package model.pantry;

public class Pantry {
	
	private int userID;
	private int ingredientId;
	private int availability;
	
	public Pantry(int userID, int ingredientId, int availability) {
		super();
		this.userID = userID;
		this.ingredientId = ingredientId;
		this.availability = availability;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}
	

}
