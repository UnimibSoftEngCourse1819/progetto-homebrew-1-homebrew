package model.brew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.MySQLConnection;

public class BrewDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public BrewDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String createBrew = "INSERT INTO Brew (brewID, userID, recipeID, name, brewDate, description, quantity, tasteNote) VALUES(?,?,?,?,?,?,?,?)";
	private static String findAllBrew = "SELECT b.*, u.name, u.surname FROM Brew AS b JOIN User AS u ON b.userID=u.userID";
	private static String findAllBrewsUser = "SELECT b.*, u.name, u.surname FROM Brew AS b JOIN User AS u ON b.userID=u.userID WHERE b.userID=?";
	private static String findMaxID = "SELECT MAX(brewID) AS max FROM Brew";

	private static String findBrewByRecipeID = "SELECT b.*, u.name, u.surname FROM Brew AS b JOIN User AS u ON b.userID=u.userID WHERE b.recipeID=?";


	public List<Brew> findAllBrews() {
		List<Brew> brews = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findAllBrew);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int brewID = resultSet.getInt("b.brewID");
				int userID = resultSet.getInt("b.userID");
				int recipeID = resultSet.getInt("b.recipeID");
				String name = resultSet.getString("b.name");
				Date brewDate = resultSet.getDate("b.brewDate");
				String description = resultSet.getString("b.description");
				int quantity = resultSet.getInt("b.quantity");
				String tasteNote = resultSet.getString("b.tasteNote");
				String userName = resultSet.getString("u.name");
				String userSurname = resultSet.getString("u.surname");
				Brew brew = new Brew(brewID, name, userID, userName, userSurname, recipeID, brewDate, description,
						quantity, tasteNote);
				brews.add(brew);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return brews;
	}

	public List<Brew> findAllBrewsUser(int userID) {
		List<Brew> brews = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findAllBrewsUser);
			statement.setInt(1, userID);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int brewID = resultSet.getInt("b.brewID");
				int recipeID = resultSet.getInt("b.recipeID");
				String name = resultSet.getString("b.name");
				Date brewDate = resultSet.getDate("b.brewDate");
				String description = resultSet.getString("b.description");
				int quantity = resultSet.getInt("b.quantity");
				String tasteNote = resultSet.getString("b.tasteNote");
				String userName = resultSet.getString("u.name");
				String userSurname = resultSet.getString("u.surname");
				Brew brew = new Brew(brewID, name, userID, userName, userSurname, recipeID, brewDate, description,
						quantity, tasteNote);
				brews.add(brew);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return brews;
	}

	public List<Brew> findBrewByRecipeID(int recipeID) {
		List<Brew> brews = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findBrewByRecipeID);
			statement.setInt(1, recipeID);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int brewID = resultSet.getInt("b.brewID");
				String name = resultSet.getString("b.name");
				int userID = resultSet.getInt("b.userID");
				Date brewDate = resultSet.getDate("b.brewDate");
				String description = resultSet.getString("b.description");
				int quantity = resultSet.getInt("b.quantity");
				String tasteNote = resultSet.getString("b.tasteNote");
				String userName = resultSet.getString("u.name");
				String userSurname = resultSet.getString("u.surname");
				Brew brew = new Brew(brewID, name, userID, userName, userSurname, recipeID, brewDate, description,
						quantity, tasteNote);
				brews.add(brew);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return brews;
	}

	public int createBrew(Brew brew) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findMaxID);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int brewID = resultSet.getInt("max") + 1;
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				statement = connect.prepareStatement(createBrew);
				statement.setInt(1, brewID);
				statement.setInt(2, brew.getUserID());
				statement.setInt(3, brew.getRecipeID());
				statement.setString(4, brew.getName());
				statement.setDate(5, date);
				statement.setString(6, brew.getDescription());
				statement.setInt(7, brew.getQuantity());
				statement.setString(8, brew.getTasteNote());
				statement.executeUpdate();
				result = brewID;
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return result;
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
