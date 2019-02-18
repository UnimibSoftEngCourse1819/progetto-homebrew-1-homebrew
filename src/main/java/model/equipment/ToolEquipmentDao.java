package model.equipment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.MySQLConnection;
import model.tool.Tool;
import model.tool.ToolDao;

public class ToolEquipmentDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public ToolEquipmentDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String createToolEquipment = "INSERT INTO Tool_Equipment (equipmentID, toolID, capacity) VALUES(?,?,?)";
	private static String updateToolEquipment = "UPDATE Tool_Equipment SET capacity =? WHERE equipmentID =? AND toolID =?";
	private static String getBatchSize = "SELECT MIN(capacity) AS batchSize FROM Tool_Equipment WHERE (toolID > 10000000 AND"
			+ "toolID < 10000006) AND equipmentID = ?";
	private static String userToolEquipment = "SELECT T.*, TE.capacity FROM Tool_Equipment as TE "
			+ "INNER JOIN Tool AS T ON T.toolID = TE.toolID "
			+ "INNER JOIN Equipment AS E ON TE.equipmentID = E.equipmentID "
			+ "WHERE E.userID = ?";
	
	public int createToolEquipment(int equipmentID) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(createToolEquipment);
			ToolDao toolDao = new ToolDao();
			List<Tool> tools = toolDao.findAllTool();
			Iterator<Tool> iterator = tools.iterator();
			while (iterator.hasNext()) {
				Tool tool = iterator.next();
				statement.setInt(1, equipmentID);
				statement.setInt(2, tool.getToolID());
				statement.setInt(3, 0);
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
				int toolID = resultSet.getInt("T.toolID");
				String name = resultSet.getString("T.name");
				int capacity = resultSet.getInt("TE.capacity");
				String measure = resultSet.getString("T.measure");
				Tool tool = new Tool(toolID, name, capacity, measure);
				tools.add(tool);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return tools;
	}
	
	public int getBatchSize(int equipmentID) {
		int capacity = 0;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(getBatchSize);
			statement.setInt(1, equipmentID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				capacity = resultSet.getInt("batchSize");
				return capacity;
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return capacity;
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
