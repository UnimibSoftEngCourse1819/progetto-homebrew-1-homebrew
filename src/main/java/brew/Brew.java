package brew;

public class Brew {
	
	private int brewID;
	private int recipeID;
	private String name;
	
	public Brew(int brewID, int recipeID, String name) {
		super();
		this.brewID = brewID;
		this.recipeID = recipeID;
		this.name = name;
	}
	
	public int getBrewID() {
		return brewID;
	}
	public void setBrewID(int brewID) {
		this.brewID = brewID;
	}
	public int getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
