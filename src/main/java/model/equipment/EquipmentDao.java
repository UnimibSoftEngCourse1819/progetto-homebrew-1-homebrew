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

public class EquipmentDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public EquipmentDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String createEquipment = "INSERT INTO Equipment (userID, toolID, avalibility) VALUES(?,?,?)";
	private static String updateEquipment = "UPDATE Equipment SET  capacity =? WHERE userID =? AND toolID, =?";
	private static String userEquipment = "SELECT T.name, E.capacity From Equipment as E "
			+ "INNER JOIN Tool AS T ON T.toolID = E.toolID" + "WHERE E.userID = ?";
	
	public int createEquipment(int userID) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(createEquipment);

			for (int i = 1; i <= 6; i++) {
				statement.setInt(1, userID);
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

	public int updateEquipment(ArrayList<Equipment> tools) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(updateEquipment);

			for (int i = 0; i < tools.size(); i++) {
				Equipment tool = tools.get(i);

				statement.setInt(1, tool.getCapacity());
				statement.setInt(2, tool.getToolID());
				statement.setInt(3, tool.getUserID());
				result = statement.executeUpdate();
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}
	
	public List<Tool> userEquipment() {
		List<Tool> tools = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(userEquipment);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int capacity = resultSet.getInt("capacity");				
				Tool tool = new Tool( name, capacity);
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
