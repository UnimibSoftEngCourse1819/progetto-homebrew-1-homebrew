package model.brew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.MySQLConnection;

public class BrewDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError ="Connection Error";
	
	public BrewDao() {}
	
	private Connection connect = null;
	private PreparedStatement statement = null;
	
	private static String createBrew = "INSERT INTO Brew (recipeID, name) VALUES(?,?,?)";
	
	public int createBrew(Brew brew) {
		int result = -1;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(createBrew);				
			statement.setInt(1, brew.getRecipeID());
			statement.setString(2, brew.getName());
			result = statement.executeUpdate();
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);		
		} finally {
			close();
		}
		
		return result;
	}
	
	private void close() {
		try {
			if (statement != null) statement.close();
			if (connect != null) connect.close();			
		} catch (Exception e) {
			logger.log(Level.SEVERE, connectionError, e);
		}
	}
	
}
