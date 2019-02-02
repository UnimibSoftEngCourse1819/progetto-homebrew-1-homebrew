package model.pantry;

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
import model.ingredient.Ingredient;

public class PantryDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public PantryDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String createPantry = "INSERT INTO Pantry (userID, ingredientID, availability) VALUES(?,?,?)";
	private static String updatePantry = "UPDATE Pantry SET  availability =? WHERE userID =? AND ingredientID, =?";
	private static String userPantry = "SELECT I.name, P.availability From Pantry as P "
			+ "INNER JOIN Ingredient AS I ON I.ingredientID = P.ingredientID" + "WHERE P.userID = ?";
	
	public int createPantry(int userID) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(createPantry);

			for (int i = 1; i <= 6; i++) {
				statement.setInt(1, userID);
				statement.setInt(2, i);
				statement.setInt(3, 0);
				result = statement.executeUpdate();
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public int updatePantry(ArrayList<Pantry> ingredients) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(updatePantry);

			for (int i = 0; i < ingredients.size(); i++) {
				Pantry ingredient = ingredients.get(i);

				statement.setInt(1, ingredient.getAvailability());
				statement.setInt(2, ingredient.getIngredientId());
				statement.setInt(3, ingredient.getUserID());
				result = statement.executeUpdate();
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}
	
	public List<Ingredient> userPantry(int userID) {
		List<Ingredient> ingredients = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(userPantry);
			statement.setInt(1, userID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int availability = resultSet.getInt("availability");				
				Ingredient ingredient = new Ingredient( name, availability);
				ingredients.add(ingredient);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return ingredients;
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
