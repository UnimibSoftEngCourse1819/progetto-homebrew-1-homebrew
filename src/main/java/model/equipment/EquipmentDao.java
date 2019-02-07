package model.equipment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.MySQLConnection;


public class EquipmentDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public EquipmentDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String createEquipment = "INSERT INTO Equipment (userID, batchSize) VALUES(?,?)";
	private static String updateEquipment = "UPDATE Equipment SET  batchSize =? WHERE userID =? AND equipmentID =?";
	private static String userEquipment = "SELECT * From Equipment WHERE userID = ?";
	
	public int createEquipment(int userID) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(createEquipment);

				statement.setInt(1, userID);
				statement.setInt(2, 0);
				result = statement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public int updateEquipment(Equipment equipment) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(updateEquipment);

				statement.setInt(1, equipment.getBatchSize());
				statement.setInt(2, equipment.getUserID());
				statement.setInt(3, equipment.getEquipmentID());
				
				result = statement.executeUpdate();
			

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}
	
	public Equipment selectEquipmentByUser(int userID) {
		Equipment equipment= null;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(userEquipment);
			statement.setInt(1, userID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int equipmentID = resultSet.getInt("equipmentID");
				int userId = resultSet.getInt("userID");
				int batchSize = resultSet.getInt("batchSize");
				equipment = new Equipment(equipmentID, userId, batchSize);
				return equipment;
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return equipment;
	}

	private void close() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connect != null)
				connect.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, connectionError, e);
		}
	}
}
