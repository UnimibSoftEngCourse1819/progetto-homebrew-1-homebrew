package model.equipment;

public class Equipment {
	private int equipmentID;
	private int userID;
	private int batchSize;
	
	public Equipment(int equipmentID, int userID, int batchSize) {
		super();
		this.equipmentID = equipmentID;
		this.userID = userID;
		this.batchSize = batchSize;
	}
	
	public int getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(int equipmentID) {
		this.equipmentID = equipmentID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	
}
