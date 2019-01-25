package tool;

public class Tool {
	
	private int toolID;
	private String name;
	
	public Tool(int toolID, String name) {
		super();
		this.toolID = toolID;
		this.name = name;
	}
	
	public int getToolID() {
		return toolID;
	}
	public void setToolID(int toolID) {
		this.toolID = toolID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
