package model.brew;

public class IngredientBrew {
	private int ingredientID;
	private int brewID;
	private int quantity;
	private String measure;

	public IngredientBrew(int ingredientID, int brewID, int quantity, String measure) {
		super();
		this.ingredientID = ingredientID;
		this.brewID = brewID;
		this.quantity = quantity;
		this.measure = measure;
	}

	public int getIngredientID() {
		return ingredientID;
	}

	public void setIngredientID(int ingredientID) {
		this.ingredientID = ingredientID;
	}

	public int getBrewID() {
		return brewID;
	}

	public void setBrewID(int brewID) {
		this.brewID = brewID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

}
