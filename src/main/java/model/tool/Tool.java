package model.tool;

public class Tool {
	
	private int toolID;
	private String toolName;
	private int capacity;
	private String measure;
	
	
	public Tool(int toolID, String toolName, int capacity, String measure) {
		super();
		this.toolID = toolID;
		this.toolName = toolName;
		this.capacity = capacity;
		this.measure = measure;
	}
	
	public int getToolID() {
		return toolID;
	}
	public void setToolID(int toolID) {
		this.toolID = toolID;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	

	

	
}
