package recipe;

public class Ingredient_Recipe {
	private int recipeId;
	private int ingredientId;
	private int quantity;
	
	public Ingredient_Recipe(int recipeId, int ingredientId, int quantity) {
		super();
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
		this.quantity = quantity;
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
	
	
}
