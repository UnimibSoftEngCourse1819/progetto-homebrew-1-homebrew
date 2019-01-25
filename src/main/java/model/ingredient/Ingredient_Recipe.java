package model.ingredient;

public class Ingredient_Recipe {
	
	private int recipeID;
	private int ingredientId;
	private int quantity;
	
	public Ingredient_Recipe(int recipeID, int ingredientId, int quantity) {
		super();
		this.recipeID = recipeID;
		this.ingredientId = ingredientId;
		this.quantity = quantity;
	}
	
	public int getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
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

	
}
