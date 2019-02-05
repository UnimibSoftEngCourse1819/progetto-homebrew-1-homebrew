package model.recipe;

import java.util.Date;
import java.util.Map;

public class Recipe {

	private int recipeID;
	private int userID;
	private String name;
	private Date creation;
	private String description;
	private String visibility;
	private String imagePath;
	private Map<Integer, String> steps;

	public Recipe(int recipeID, int userID, String name, Date creation, String description, String visibility,
			String imagePath, Map<Integer, String> steps) {
		super();
		this.recipeID = recipeID;
		this.userID = userID;
		this.name = name;
		this.creation = creation;
		this.description = description;
		this.visibility = visibility;
		this.imagePath = imagePath;
		this.steps = steps;

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

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Map<Integer, String> getSteps() {
		return steps;
	}

	public void setSteps(Map<Integer, String> steps) {
		this.steps = steps;
	}

}
