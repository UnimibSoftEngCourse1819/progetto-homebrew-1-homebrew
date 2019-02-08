package model.pantry;

public class Pantry {

	private int userID;
	private int ingredientID;
	private String ingredientName;
	private int availability;
	private String measure;

	public Pantry(int userID, int ingredientID, String ingredientName, int availability, String measure) {
		super();
		this.userID = userID;
		this.ingredientID = ingredientID;
		this.ingredientName = ingredientName;
		this.availability = availability;
		this.measure = measure;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getIngredientID() {
		return ingredientID;
	}

	public void setIngredientID(int ingredientID) {
		this.ingredientID = ingredientID;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

}
