package model.brew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.MySQLConnection;
import model.recipe.IngredientRecipe;

public class IngredientBrewDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public IngredientBrewDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String createIngredientBrew = "INSERT INTO Ingredient_Brew (ingredientID, brewID, quantity, measure) VALUES(?,?,?,?)";
	private static String findAllIngredientBrew = "SELECT i.* FROM Brew AS b JOIN Ingredient_Brew AS i ON b.brewID=i.brewID WHERE b.brewID=?";
	private static String findAllBrewsUser = "SELECT b.*, u.name, u.surname FROM Brew AS b JOIN User AS u ON b.userID=u.userID WHERE b.userID=?";

	private static String findBrewByRecipeID = "SELECT b.*, u.name, u.surname FROM Brew AS b JOIN User AS u ON b.userID=u.userID WHERE b.recipeID=?";

	public List<IngredientBrew> findAllIngredientBrew(int brewID) {
		List<IngredientBrew> ingredientBrews = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findAllIngredientBrew);
			statement.setInt(1, brewID);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int ingredientID = resultSet.getInt("i.ingredientID");
				int quantity = resultSet.getInt("i.quantity");
				String measure = resultSet.getString("i.measure");
				IngredientBrew ingredientBrew = new IngredientBrew(ingredientID, brewID, quantity, measure);
				ingredientBrews.add(ingredientBrew);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return ingredientBrews;
	}

	public int createIngredientBrew(List<IngredientBrew> ingredientsBrew) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findAllIngredientBrew);
			Iterator<IngredientBrew> iterator = ingredientsBrew.iterator();
			while (iterator.hasNext()) {
				IngredientBrew ingredientBrew = iterator.next();
				statement.setInt(1, ingredientBrew.getIngredientID());
				statement.setInt(2, ingredientBrew.getBrewID());
				statement.setInt(3, ingredientBrew.getQuantity());
				statement.setString(4, ingredientBrew.getMeasure());
				result = statement.executeUpdate();
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
			if (statement != null)
				statement.close();
			if (connect != null)
				connect.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, connectionError, e);
		}
	}
}
