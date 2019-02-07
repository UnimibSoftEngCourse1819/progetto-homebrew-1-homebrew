package model.ingredient;

public class Ingredient {

	private int ingredientID;
	private String name;
	private String measure;
	public Ingredient(int ingredientID, String name, String measure) {
		super();
		this.ingredientID = ingredientID;
		this.name = name;
		this.measure = measure;
	}
	public int getIngredientID() {
		return ingredientID;
	}
	public void setIngredientID(int ingredientID) {
		this.ingredientID = ingredientID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}



}
