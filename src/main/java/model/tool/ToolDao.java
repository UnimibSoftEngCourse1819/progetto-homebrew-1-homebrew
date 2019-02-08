package model.tool;

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

public class ToolDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public ToolDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String findAllTool = "SELECT T.*, TE.capacity From Tool_Equipment as TE "
			+ "INNER JOIN Tool AS T ON T.toolID = TE.toolID "
			+ "INNER JOIN Equipment AS E ON TE.equipmentID = E.equipmentID ";

	public List<Tool> findAllTool() {
		List<Tool> tools = new ArrayList<>();

		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findAllTool);
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
