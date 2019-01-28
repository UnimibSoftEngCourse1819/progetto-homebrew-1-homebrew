package model.recipe;

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
import model.recipe.Recipe;

public class RecipeDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public RecipeDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String findAllRecipes = "SELECT * From Recipe WHERE visibility='public'";
	private static String findAllRecipesUser = "SELECT * From Recipe WHERE visibility='public' OR user=?";
	private static String createRecipe = "INSERT INTO Recipe (recipeID, userID, name, content, capacity, visibility) VALUES(?,?,?,?,?,?)";

	public List<Recipe> findAllRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(findAllRecipes);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("recipeID");
				int userID = resultSet.getInt("user");
				String name = resultSet.getString("name");
				String content = resultSet.getString("content");
				int capacity = resultSet.getInt("capacity");
				String visibility = resultSet.getString("visibility");
				Recipe recipe = new Recipe(id, userID, name, content, capacity, visibility);
				recipes.add(recipe);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return recipes;
	}

	public List<Recipe> findAllRecipes(int userRequest) {
		List<Recipe> recipes = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(findAllRecipesUser);
			statement.setInt(1, userRequest);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("recipeID");
				int userID = resultSet.getInt("user");
				String name = resultSet.getString("name");
				String content = resultSet.getString("content");
				int capacity = resultSet.getInt("capacity");
				String visibility = resultSet.getString("visibility");
				Recipe recipe = new Recipe(id, userID, name, content, capacity, visibility);
				recipes.add(recipe);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return recipes;
	}

	public int createRecipe(Recipe recipe) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(createRecipe);

			statement.setInt(1, recipe.getRecipeID());
			statement.setInt(2, recipe.getUserID());
			statement.setString(3, recipe.getName());
			statement.setString(4, recipe.getContent());
			statement.setInt(5, recipe.getCapacity());
			statement.setString(6, recipe.getVisibility());
			result = statement.executeUpdate();
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
