package model.ingredient;

public class Pantry {
	
	private int userID;
	private int ingredientId;
	private int avilability;
	
	public Pantry(int userID, int ingredientId, int avilability) {
		super();
		this.userID = userID;
		this.ingredientId = ingredientId;
		this.avilability = avilability;
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

	public int getAvilability() {
		return avilability;
	}

	public void setAvilability(int avilability) {
		this.avilability = avilability;
	}
	

}
