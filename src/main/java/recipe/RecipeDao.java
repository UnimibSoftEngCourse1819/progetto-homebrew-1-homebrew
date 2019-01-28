package recipe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.MySQLConnection;

class RecipeDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError ="Connection Error";
	
	public RecipeDao() {}
	
	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private static String findAllRecipes = "SELECT * From Recipe WHERE visibility='public'";
	private static String findAllRecipesUser = "SELECT * From Recipe WHERE visibility='public' OR user=?";

	
	public List<Recipe> findAllRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(findAllRecipes);			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("recipeID");
				int userID = resultSet.getInt("user");
				String name = resultSet.getString("name");
				String content = resultSet.getString("content");
				int capacity = resultSet.getInt("capacity");
				String visibility = resultSet.getString("visibility");
				Recipe recipe = new Recipe(id, userID, name, content, capacity, visibility);
				recipes.add(recipe);
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);
		} finally {
			close();
		}
		return recipes;
	}
	
	public List<Recipe> findAllRecipes(int userRequest) {
		List<Recipe> recipes = new ArrayList<>();
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(findAllRecipesUser);
			statement.setInt(1, userRequest);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("recipeID");
				int userID = resultSet.getInt("user");
				String name = resultSet.getString("name");
				String content = resultSet.getString("content");
				int capacity = resultSet.getInt("capacity");
				String visibility = resultSet.getString("visibility");
				Recipe recipe = new Recipe(id, userID, name, content, capacity, visibility);
				recipes.add(recipe);
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);
		} finally {
			close();
		}
		return recipes;
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
