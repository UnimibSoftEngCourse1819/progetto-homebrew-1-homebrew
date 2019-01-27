package brew;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.MySQLConnection;

public class User_BrewDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError ="Connection Error";
	
	public User_BrewDao() {}

	
	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private static String findAllBrew = "SELECT B.name, R.name, UB.brewDate, UB.quantity From Brew as B "
										+ "INNER JOIN Recipe AS R ON B.recipeID = R.recipeID"
										+ "INNER JOIN User_Brew AS R ON B.brewID = UB.brewID"
										+ "WHERE UB.UserID = ?";
	private static String createUserBrew = "INSERT INTO User_Brew (userID, brewID, brewDate, quantity) VALUES(?,?,?,?)";
	
	public ArrayList<User_Brew_Select> findAllUsers() {
		ArrayList<User_Brew_Select> user_brew_selects = new ArrayList<>();
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(findAllBrew);			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				String brewName = resultSet.getString("brewName");
				String recipeName = resultSet.getString("recipeName");
				Date brewDate = resultSet.getDate("brewDate");
				int quantity = resultSet.getInt("quantity");
				User_Brew_Select user_brew_select = new User_Brew_Select (brewName, recipeName, brewDate, quantity);
				user_brew_selects.add(user_brew_select);
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);
		}catch  (ClassNotFoundException e) {
			logger.log(Level.SEVERE, connectionError, e);
		} finally {
			close();
		}
		return user_brew_selects;
	}
	
	public int createUser(User_Brew user_brew) {
		int result = -1;
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(createUserBrew);				
			statement.setInt(1, user_brew.getUserId());
			statement.setInt(2, user_brew.getBrewId());
			java.sql.Date castDate = new java.sql.Date(user_brew.getBrewDate().getTime());
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String reportDate = dateformat.format(castDate);
			statement.setString(3, reportDate);
			statement.setInt(4, user_brew.getQuantity());
			result = statement.executeUpdate();
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);		
		}catch  (ClassNotFoundException e) {
			logger.log(Level.SEVERE, connectionError, e);
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
