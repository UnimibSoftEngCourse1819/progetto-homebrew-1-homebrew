package model.tool;

public class Equipment {
	
	private int userID;
	private int toolID;
	private int capacity;
	
	public Equipment(int userID, int toolID, int capacity) {
		super();
		this.userID = userID;
		this.toolID = toolID;
		this.capacity = capacity;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getToolID() {
		return toolID;
	}
	public void setToolID(int toolID) {
		this.toolID = toolID;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	

	
}
