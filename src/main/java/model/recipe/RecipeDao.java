package model.recipe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	private static String findAllRecipes = "SELECT * FROM Recipe WHERE visibility='public'";
	private static String findAllRecipesUser = "SELECT * FROM Recipe WHERE visibility='public' OR userID=?";
	private static String findRecipeByID = "SELECT * FROM Recipe WHERE recipeID=?";
	private static String createRecipe = "INSERT INTO Recipe (recipeID, userID, name, creation, description, visibility, imagePath) VALUES(?,?,?,?,?,?,?)";
	private static String updateRecipe = "UPDATE Recipe SET name=?, description=?, visibility=?, imagePath=? WHERE recipeID=?";

	private static String findStepsRecipeByID = "SELECT stepPos, text FROM Step_Recipe WHERE recipeID=?";
	private static String createSteps = "INSERT INTO Step_Recipe (recipeID, stepPos, text) VALUES(?,?,?)";
	private static String deleteStepsByRecipeID = "DELETE FROM Step_Recipe WHERE recipeID=?";

	public Recipe findRecipeByID(int recipeID) {
		Recipe recipe = null;
		Map<Integer, String> steps = null;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findRecipeByID);
			statement.setInt(1, recipeID);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("recipeID");
				int userID = resultSet.getInt("userID");
				String name = resultSet.getString("name");
				Date creation = resultSet.getDate("creation");
				String description = resultSet.getString("description");
				String visibility = resultSet.getString("visibility");
				String imagePath = resultSet.getString("imagePath");
				steps = getSteps(recipeID);
				recipe = new Recipe(id, userID, name, creation, description, visibility, imagePath, steps);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return recipe;
	}

	public List<Recipe> findAllRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findAllRecipes);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("recipeID");
				int userID = resultSet.getInt("userID");
				String name = resultSet.getString("name");
				Date creation = resultSet.getDate("creation");
				String description = resultSet.getString("description");
				String visibility = resultSet.getString("visibility");
				String imagePath = resultSet.getString("imagePath");
				Recipe recipe = new Recipe(id, userID, name, creation, description, visibility, imagePath, null);
				recipes.add(recipe);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return recipes;
	}

	public List<Recipe> findAllRecipesUser(int userRequest) {
		List<Recipe> recipes = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findAllRecipesUser);
			statement.setInt(1, userRequest);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("recipeID");
				int userID = resultSet.getInt("userID");
				String name = resultSet.getString("name");
				Date creation = resultSet.getDate("creation");
				String description = resultSet.getString("description");
				String visibility = resultSet.getString("visibility");
				String imagePath = resultSet.getString("imagePath");
				Recipe recipe = new Recipe(id, userID, name, creation, description, visibility, imagePath, null);
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
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(createRecipe);

			statement.setInt(1, recipe.getRecipeID());
			statement.setInt(2, recipe.getUserID());
			statement.setString(3, recipe.getName());
			statement.setString(4, recipe.getDescription());
			statement.setString(5, recipe.getVisibility());
			statement.setString(6, recipe.getImagePath());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public int updateRecipe(Recipe recipe) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(updateRecipe);
			statement.setString(1, recipe.getName());
			statement.setString(2, recipe.getDescription());
			statement.setString(3, recipe.getVisibility());
			statement.setString(4, recipe.getImagePath());
			statement.setInt(5, recipe.getRecipeID());
			result = statement.executeUpdate();
			editSteps(recipe);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	private Map<Integer, String> getSteps(int recipeID) {
		Map<Integer, String> steps = new HashMap<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findStepsRecipeByID);
			statement.setInt(1, recipeID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int stepPos = resultSet.getInt("stepPos");
				String text = resultSet.getString("text");
				steps.put(stepPos, text);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return steps;
	}

	private int editSteps(Recipe recipe) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(deleteStepsByRecipeID);
			statement.setInt(1, recipe.getRecipeID());
			result = statement.executeUpdate();
			result = createSteps(recipe.getRecipeID(), recipe.getSteps());

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return result;
	}

	private int createSteps(int recipeID, Map<Integer, String> steps) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(createSteps);
			for (Integer key : steps.keySet()) {
				statement.setInt(1, recipeID);
				statement.setInt(2, key);
				statement.setString(3, steps.get(key));
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
