package model.equipment;

public class ToolEquipment {
	
	private int equipmentID;
	private int toolID;
	private int capacity;
	
	public ToolEquipment(int equipmentID, int toolID, int capacity) {
		super();
		this.equipmentID = equipmentID;
		this.toolID = toolID;
		this.capacity = capacity;
	}
	
	public int getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(int equipmentID) {
		this.equipmentID = equipmentID;
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
