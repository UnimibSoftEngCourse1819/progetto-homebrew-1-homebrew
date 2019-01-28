package equipment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.MySQLConnection;

public class EquipmentDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError ="Connection Error";
	
	public EquipmentDao() {}

	
	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private static String createEquipment = "INSERT INTO Equipment (userID, toolID, avalibility) VALUES(?,?,?)";
	private static String updateEquipment = "UPDATE Equipment SET  capacity =? WHERE userID =? AND toolID, =?";
	
	public int createEquipment(int userID) {
		int result = -1;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(createEquipment);				
			
			//userID sar� ottenuto dalla sessione
			for(int i=1; i<=6; i++){			
				 statement.setInt(1, userID);
				 statement.setInt(2, i);
				 statement.setInt(3, 1);
				 result = statement.executeUpdate();
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);		
		} finally {
			close();
		}
		
		return result;
	}
	
	public int UpdateEquipment(ArrayList<Equipment> tools) {
		int result = -1;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(updateEquipment);				
			
			//la Servlet passera un arrayList di Equipment dove il primo attributo sar� l'id dell'utente preso dalla sessione
			//il secondo attributo sar� l'id dell'attrezzo
			//il terzo attributo sar� la nuova capacit�
			for(int i=0; i<tools.size(); i++){
				Equipment tool= tools.get(i);
				
				 statement.setInt(1, tool.getCapacity());
				 statement.setInt(2, tool.getToolID());
				 statement.setInt(3, tool.getUserID());
				 result = statement.executeUpdate();
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);		
		} finally {
			close();
		}
		
		return result;
	}
	
	private void close() {
		try {
			if (resultSet != null) resultSet.close();
			if (statement != null) statement.close();
			if (connect != null) connect.close();			
		} catch (Exception e) {
			logger.log(Level.SEVERE, connectionError, e);
		}
	}
}
