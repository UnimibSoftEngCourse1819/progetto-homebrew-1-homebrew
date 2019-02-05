package model.equipment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.MySQLConnection;
import model.tool.Tool;

public class ToolEquipmentDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public ToolEquipmentDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String createToolEquipment = "INSERT INTO Equipment (equipmentID, toolID, capacity) VALUES(?,?,?)";
	private static String updateToolEquipment = "UPDATE Equipment SET  capacity =? WHERE equipmentID =? AND toolID, =?";
	private static String userToolEquipment = "SELECT T.name, E.capacity, T.measure From Tool_Equipment as E "
			+ "INNER JOIN Tool AS T ON T.toolID = E.toolID" + "WHERE E.equipmentID = ?";
	
	public int createToolEquipment(int equipmentID) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(createToolEquipment);

			for (int i = 1; i <= 9; i++) {
				statement.setInt(1, equipmentID);
				statement.setInt(2, i);
				statement.setInt(3, 1);
				result = statement.executeUpdate();
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public int updateToolEquipment(ArrayList<ToolEquipment> tools) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(updateToolEquipment);

			for (int i = 0; i < tools.size(); i++) {
				ToolEquipment tool = tools.get(i);

				statement.setInt(1, tool.getCapacity());
				statement.setInt(2, tool.getEquipmentID());
				statement.setInt(3, tool.getToolID());
				result = statement.executeUpdate();
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}
	
	public List<Tool> userToolEquipment(int userID) {
		List<Tool> tools = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(userToolEquipment);
			statement.setInt(1, userID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int capacity = resultSet.getInt("batchSize");
				String measure = resultSet.getString("measure");
				Tool tool = new Tool( name, capacity, measure);
				tools.add(tool);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return tools;
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
