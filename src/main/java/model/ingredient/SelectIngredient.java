package model.ingredient;

public class SelectIngredient {
	
	private String name;
	private int availability;
	
	public SelectIngredient(String name, int availability) {
		super();
		this.name = name;
		this.availability = availability;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	
}
