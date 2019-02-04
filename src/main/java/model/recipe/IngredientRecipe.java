package model.recipe;

public class IngredientRecipe {
	private int recipeId;
	private int ingredientId;
	private int quantity;
	private String measure;

	public IngredientRecipe(int recipeId, int ingredientId, int quantity, String measure) {
		super();
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
		this.quantity = quantity;
		this.measure = measure;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
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
