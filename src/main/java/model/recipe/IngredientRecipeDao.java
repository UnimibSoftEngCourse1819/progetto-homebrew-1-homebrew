package model.recipe;

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

public class IngredientRecipeDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public IngredientRecipeDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String findIngredientRecipe = "SELECT r.recipeID AS recipeID, r.ingredientID AS ingredientID, r.quantity AS quantity, r.measure AS measure, i.name AS ingredientName FROM Ingredient_Recipe as r JOIN Ingredient as i ON r.ingredientID=i.ingredientID WHERE recipeID=?";
	private static String createIngredientRecipe = "INSERT INTO Ingredient_Recipe (recipeID, ingredientID, quantity, measure) VALUES(?,?,?,?)";
	private static String deleteIngredientRecipe = "DELETE FROM Ingredient_Recipe WHERE recipeID=?";

	public List<IngredientRecipe> findIngredientsRecipe(int recipeID) {
		List<IngredientRecipe> ingredientsRecipe = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findIngredientRecipe);
			statement.setInt(1, recipeID);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int recipeId = resultSet.getInt("recipeID");
				int ingredientId = resultSet.getInt("ingredientID");
				String ingredientName = resultSet.getString("ingredientName");
				int quantity = resultSet.getInt("quantity");
				String measure = resultSet.getString("measure");
				IngredientRecipe ingredientRecipe = new IngredientRecipe(recipeId, ingredientId, ingredientName,
						quantity, measure);
				ingredientsRecipe.add(ingredientRecipe);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return ingredientsRecipe;
	}

	public int createIngredientsRecipe(List<IngredientRecipe> ingredientsRecipes) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(createIngredientRecipe);
			Iterator<IngredientRecipe> iterator = ingredientsRecipes.iterator();
			while (iterator.hasNext()) {
				IngredientRecipe ingredientRecipe = iterator.next();
				statement.setInt(1, ingredientRecipe.getRecipeID());
				statement.setInt(2, ingredientRecipe.getIngredientID());
				statement.setInt(3, ingredientRecipe.getQuantity());
				statement.setString(4, ingredientRecipe.getMeasure());
				result = statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public int updateIngredientsRecipe(int recipeID, List<IngredientRecipe> ingredientsRecipes) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(deleteIngredientRecipe);
			statement.setInt(1, recipeID);
			result = statement.executeUpdate();
			createIngredientsRecipe(ingredientsRecipes);
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
