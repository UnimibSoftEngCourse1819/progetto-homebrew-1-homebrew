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

	private static String createBrew = "INSERT INTO Brew (userID, recipeID, brewDate, description, quantity, tasteNote) VALUES(?,?,?,?,?,?)";
	private static String findAllBrew = "SELECT * FROM Brew";
	//private static String findAllBrewRecipe = "SELECT * FROM Brew WHERE recipeID=?";
	//private static String findRecipeByID = "SELECT * FROM Recipe WHERE recipeID=?";
	//private static String createRecipe = "INSERT INTO Recipe (recipeID, userID, name, creation, description, visibility, imagePath) VALUES(?,?,?,?,?,?,?)";
	//private static String updateRecipe = "UPDATE Recipe SET name=?, description=?, visibility=?, imagePath=? WHERE recipeID=?";

	public int createBrew(Brew brew) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(createBrew);
			statement.setInt(1, brew.getUserID());
			statement.setInt(2, brew.getRecipeID());
			statement.setDate(3, (java.sql.Date) brew.getBrewDate());
			statement.setString(4, brew.getDescription());
			statement.setInt(5, brew.getQuantity());
			statement.setString(6, brew.getTasteNote());

			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

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
				int brewID = resultSet.getInt("brewID");
				int userID = resultSet.getInt("userID");
				int recipeID = resultSet.getInt("recipeID");
				Date brewDate = resultSet.getDate("brewDate");
				String description = resultSet.getString("description");
				int quantity = resultSet.getInt("quantity");
				String tasteNote = resultSet.getString("tasteNote");
				Brew brew = new Brew(brewID, userID, recipeID, brewDate, description, quantity, tasteNote);
				brews.add(brew);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return brews;
	}

	private void close() {
		try {
			if (statement != null)
				statement.close();
			if (connect != null)
				connect.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, connectionError, e);
		}
	}

}
