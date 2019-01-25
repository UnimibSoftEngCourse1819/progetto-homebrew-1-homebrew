package recipe;

public class Recipe {
	
	private int recipeID;
	private int userID;
	private String name;	
	private String content;
	private int capacity;
	private String visibility;
	
	public Recipe(int recipeID, int userID, String name, String content, int capacity, String visibility) {
		super();
		this.recipeID = recipeID;
		this.userID = userID;
		this.name = name;
		this.content = content;
		this.capacity = capacity;
		this.visibility = visibility;
	}
	
	public int getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	
}
