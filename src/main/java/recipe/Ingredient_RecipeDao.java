package recipe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.MySQLConnection;

public class Ingredient_RecipeDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError ="Connection Error";
	
	public Ingredient_RecipeDao() {}

	
	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private static String createIngredient_Recipe = "INSERT INTO Ingredient_Recipe (recipeID, ingredientID, quantity) VALUES(?,?,?)";
	
	public int UpdateEquipment(ArrayList<Ingredient_Recipe> ingredient_Recipes) {
		int result = -1;
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(createIngredient_Recipe);				
			
			//la Servlet passera un arrayList di Ingredient_Recipe dove il primo attributo sarà l'id della ricetta 
			//il secondo attributo sarà l'id dell'ingrediente
			//il terzo attributo sarà la quantità
			for(int i=0; i<ingredient_Recipes.size(); i++){
				Ingredient_Recipe ingredient_Recipe= ingredient_Recipes.get(i);
				
				 statement.setInt(1, ingredient_Recipe.getRecipeId());
				 statement.setInt(2, ingredient_Recipe.getIngredientId());
				 statement.setInt(3, ingredient_Recipe.getQuantity());
				 result = statement.executeUpdate();
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);		
		}catch  (ClassNotFoundException e) {
			logger.log(Level.SEVERE, connectionError, e);
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
