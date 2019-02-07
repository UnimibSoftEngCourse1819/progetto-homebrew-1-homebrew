package model.ingredient;

public class Ingredient {

	private int ingredientID;
	private String name;

	public Ingredient(int ingredientID, String name) {
		super();
		this.ingredientID = ingredientID;
		this.name = name;
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

}
