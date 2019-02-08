package model.recipe;

public class IngredientRecipe {
	private int recipeID;
	private int ingredientID;
	private String ingredientName;
	private int quantity;
	private String measure;

	public IngredientRecipe(int recipeID, int ingredientID, String ingredientName, int quantity, String measure) {
		super();
		this.recipeID = recipeID;
		this.ingredientID = ingredientID;
		this.ingredientName = ingredientName;
		this.quantity = quantity;
		this.measure = measure;
	}

	public int getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
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
