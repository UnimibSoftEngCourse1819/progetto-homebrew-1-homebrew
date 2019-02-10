package model.recipe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.MySQLConnection;

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
	private static String findMaxID = "SELECT MAX(recipeID) AS max FROM Recipe";
	private static String createRecipe = "INSERT INTO Recipe (recipeID, userID, name, creation, description, visibility, imagePath) VALUES(?,?,?,?,?,?,?)";
	private static String updateRecipe = "UPDATE Recipe SET name=?, description=?, visibility=?, imagePath=? WHERE recipeID=?";
	private static String deleteRecipe = "DELETE FROM Recipe WHERE recipeID=?";

	private static String findStepsRecipeByID = "SELECT stepPos, text FROM Step_Recipe WHERE recipeID=?";
	private static String createSteps = "INSERT INTO Step_Recipe (recipeID, stepPos, text) VALUES(?,?,?)";
	private static String deleteStepsByRecipeID = "DELETE FROM Step_Recipe WHERE recipeID=?";

	private static String findRecipesUser = "SELECT * FROM Recipe WHERE userID=?";

	private static String findRecipesByName = "SELECT * FROM Recipe WHERE name LIKE ? AND visibility='public'";

	private static String wisbt = "SELECT * FROM Recipe WHERE recipeID=(SELECT r.recipeID AS recipe FROM (SELECT SUM(ir.quantity)AS qTot, ir.recipeID AS recipeIDS "
			+ "FROM (SELECT cm.recipeIDM AS recipeIDMax FROM (SELECT COUNT(i.recipeID) AS countM, i.recipeID AS recipeIDM "
			+ "FROM Recipe AS r JOIN Ingredient_Recipe AS i ON r.recipeID=i.RecipeID JOIN Pantry AS p "
			+ "ON i.ingredientID = p.ingredientID JOIN User AS u ON p.userID=u.userID WHERE u.userID=? AND "
			+ "(r.visibility='public' OR (r.visibility='private' AND u.userID=?)) AND p.availability >= (i.quantity* "
			+ "(SELECT e.batchSize FROM User AS u JOIN Equipment AS e ON u.userID=e.userID WHERE u.userID=?)) "
			+ "GROUP BY i.recipeID) AS cm JOIN (SELECT COUNT(countM) AS countR, c.recipeIDM AS recipeIDR FROM Ingredient_Recipe "
			+ "AS ir JOIN (SELECT COUNT(i.recipeID) AS countM, i.recipeID AS recipeIDM FROM Recipe AS r JOIN Ingredient_Recipe "
			+ "AS i ON r.recipeID=i.RecipeID JOIN Pantry AS p ON i.ingredientID = p.ingredientID JOIN User AS u "
			+ "ON p.userID=u.userID WHERE u.userID=? AND (r.visibility='public' OR (r.visibility='private' "
			+ "AND u.userID=?)) AND p.availability >= (i.quantity* (SELECT e.batchSize FROM User AS u JOIN Equipment "
			+ "AS e ON u.userID=e.userID WHERE u.userID=?)) GROUP BY i.recipeID) AS c ON ir.recipeID=c.recipeIDM "
			+ "GROUP BY c.recipeIDM) AS ci ON cm.recipeIDM=ci.recipeIDR WHERE cm.countM = ci.countR) AS v3 JOIN Ingredient_Recipe "
			+ "AS ir ON v3.recipeIDMax=ir.recipeID GROUP BY ir.recipeID) AS v4 JOIN Recipe AS r ON v4.recipeIDS=r.recipeID "
			+ "WHERE v4.qTot = (SELECT MAX(v5.qTot) FROM (SELECT SUM(ir.quantity) AS qTot, ir.recipeID AS recipeIDS "
			+ "FROM (SELECT cm.recipeIDM AS recipeIDMax FROM (SELECT COUNT(i.recipeID) AS countM, i.recipeID AS recipeIDM "
			+ "FROM Recipe AS r JOIN Ingredient_Recipe AS i ON r.recipeID=i.RecipeID JOIN Pantry AS p "
			+ "ON i.ingredientID = p.ingredientID JOIN User AS u ON p.userID=u.userID WHERE u.userID=? AND "
			+ "(r.visibility='public' OR (r.visibility='private' AND u.userID=?)) AND p.availability >= (i.quantity* "
			+ "(SELECT e.batchSize FROM User AS u JOIN Equipment AS e ON u.userID=e.userID WHERE u.userID=?)) "
			+ "GROUP BY i.recipeID) AS cm JOIN (SELECT COUNT(countM) AS countR, c.recipeIDM AS recipeIDR FROM Ingredient_Recipe "
			+ "AS ir JOIN (SELECT COUNT(i.recipeID) AS countM, i.recipeID AS recipeIDM FROM Recipe AS r JOIN Ingredient_Recipe "
			+ "AS i ON r.recipeID=i.RecipeID JOIN Pantry AS p ON i.ingredientID = p.ingredientID JOIN User AS u "
			+ "ON p.userID=u.userID WHERE u.userID=? AND (r.visibility='public' OR (r.visibility='private' "
			+ "AND u.userID=?)) AND p.availability >= (i.quantity* (SELECT e.batchSize FROM User AS u JOIN Equipment "
			+ "AS e ON u.userID=e.userID WHERE u.userID=?)) GROUP BY i.recipeID) AS c ON ir.recipeID=c.recipeIDM "
			+ "GROUP BY c.recipeIDM) AS ci ON cm.recipeIDM=ci.recipeIDR WHERE cm.countM = ci.countR) AS v3 JOIN Ingredient_Recipe"
			+ " AS ir ON v3.recipeIDMax=ir.recipeID GROUP BY ir.recipeID) AS v5))";

	public Recipe wsibt(int recipeID) {
		Recipe recipe = null;
		Map<Integer, String> steps = null;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(wisbt);
			for (int i = 1; i <= 12; i++) {
				statement.setInt(i, recipeID);
			}
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

	public List<Recipe> findRecipesUser(int userRequest) {
		List<Recipe> recipes = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(findRecipesUser);
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
			statement = connect.prepareStatement(findMaxID);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int recipeID = resultSet.getInt("max") + 1;
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				statement = connect.prepareStatement(createRecipe);
				statement.setInt(1, recipeID);
				statement.setInt(2, recipe.getUserID());
				statement.setString(3, recipe.getName());
				statement.setDate(4, date);
				statement.setString(5, recipe.getDescription());
				statement.setString(6, recipe.getVisibility());
				statement.setString(7, recipe.getImagePath());
				statement.executeUpdate();
				createSteps(recipeID, recipe.getSteps());
				result = recipeID;
			}
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

	public int removeRecipe(int recipeID) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(deleteRecipe);
			statement.setInt(1, recipeID);
			result = statement.executeUpdate();
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

	public List<Recipe> findRecipesByName(String searchName) {
		List<Recipe> recipes = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			searchName = "%" + searchName + "%";
			statement = connect.prepareStatement(findRecipesByName);
			statement.setString(1, searchName);
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

	public List<Recipe> findRecipesByIngredients(List<IngredientRecipe> ingRecipes) {
		List<Recipe> recipes = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());

			String searchByIngredients = "SELECT DISTINCT R.* FROM Recipe AS R JOIN Ingredient_Recipe AS IR ON R.recipeID = IR.recipeID"
					+ " WHERE R.visibility='public' AND (";
			Iterator<IngredientRecipe> iterator = ingRecipes.iterator();
			while (iterator.hasNext()) {
				IngredientRecipe ingR = iterator.next();
				searchByIngredients += "(IR.ingredientID=" + ingR.getIngredientID() + " AND IR.quantity="
						+ ingR.getQuantity() + ")";
				if (iterator.hasNext()) {
					searchByIngredients += " OR ";
				}
			}
			searchByIngredients += ")";
			statement = connect.prepareStatement(searchByIngredients);
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
