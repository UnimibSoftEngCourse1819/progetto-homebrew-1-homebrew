package model.recipe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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

	private static String findIngredientRecipe = "SELECT * FROM Ingredient_Recipe WHERE recipeID=?";
	private static String createIngredientRecipe = "INSERT INTO Ingredient_Recipe (recipeID, ingredientID, quantity) VALUES(?,?,?)";

	public IngredientRecipe findRecipeByID(int recipeID) {
		IngredientRecipe ingredientRecipe = null;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findIngredientRecipe);
			statement.setInt(1, recipeID);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int recipeId = resultSet.getInt("recipeID");
				int ingredientId = resultSet.getInt("ingredientID");
				int quantity = resultSet.getInt("quantity");
				String measure = resultSet.getString("measure");
				ingredientRecipe = new IngredientRecipe(recipeId, ingredientId, quantity, measure);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return ingredientRecipe;
	}

	public int updateEquipment(List<IngredientRecipe> ingredientRecipes) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(createIngredientRecipe);

			// la Servlet passera un arrayList di Ingredient_Recipe dove il primo attributo
			// sar� l'id della ricetta
			// il secondo attributo sar� l'id dell'ingrediente
			// il terzo attributo sar� la quantit�
			for (int i = 0; i < ingredientRecipes.size(); i++) {
				IngredientRecipe ingredientRecipe = ingredientRecipes.get(i);

				statement.setInt(1, ingredientRecipe.getRecipeId());
				statement.setInt(2, ingredientRecipe.getIngredientId());
				statement.setInt(3, ingredientRecipe.getQuantity());
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
