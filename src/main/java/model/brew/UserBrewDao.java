package model.brew;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.brew.UserBrewSelect;
import model.database.MySQLConnection;

public class UserBrewDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError ="Connection Error";
	
	public UserBrewDao() {
		//costructor
	}

	
	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private static String findAllBrew = "SELECT B.name, R.name, UB.brewDate, UB.quantity From Brew as B "
										+ "INNER JOIN Recipe AS R ON B.recipeID = R.recipeID"
										+ "INNER JOIN User_Brew AS R ON B.brewID = UB.brewID"
										+ "WHERE UB.UserID = ?";
	private static String createUserBrew = "INSERT INTO User_Brew (userID, brewID, brewDate, quantity) VALUES(?,?,?,?)";
	
	public List<UserBrewSelect> findAllUsers() {
		List<UserBrewSelect> userBrewSelects = new ArrayList<>();
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(findAllBrew);			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				String brewName = resultSet.getString("brewName");
				String recipeName = resultSet.getString("recipeName");
				Date brewDate = resultSet.getDate("brewDate");
				int quantity = resultSet.getInt("quantity");
				UserBrewSelect userBrewSelect = new UserBrewSelect (brewName, recipeName, brewDate, quantity);
				userBrewSelects.add(userBrewSelect);
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);
		} finally {
			close();
		}
		return userBrewSelects;
	}
	
	public int createUser(UserBrew userBrew) {
		int result = -1;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(createUserBrew);				
			statement.setInt(1, userBrew.getUserId());
			statement.setInt(2, userBrew.getBrewId());
			java.sql.Date castDate = new java.sql.Date(userBrew.getBrewDate().getTime());
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String reportDate = dateformat.format(castDate);
			statement.setString(3, reportDate);
			statement.setInt(4, userBrew.getQuantity());
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
			if (resultSet != null) resultSet.close();
			if (statement != null) statement.close();
			if (connect != null) connect.close();			
		} catch (Exception e) {
			logger.log(Level.SEVERE, connectionError, e);
		}
	}
}
