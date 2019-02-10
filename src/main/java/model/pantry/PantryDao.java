package model.pantry;

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
import model.ingredient.Ingredient;
import model.ingredient.IngredientDao;

public class PantryDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public PantryDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String createPantry = "INSERT INTO Pantry (userID, ingredientID, availability, measure) VALUES(?,?,?,?)";
	private static String updatePantry = "UPDATE Pantry SET  availability =? WHERE userID =? AND ingredientID =?";
	private static String userPantry = "SELECT P.*, I.name FROM Pantry AS P JOIN Ingredient AS I ON I.ingredientID=P.ingredientID WHERE P.userID = ?";

	public int createPantry(int userID) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(createPantry);
			IngredientDao ingredientDao = new IngredientDao();
			List<Ingredient> ingredients = ingredientDao.findAllIngredient();
			Iterator<Ingredient> iterator = ingredients.iterator();
			while (iterator.hasNext()) {
				Ingredient ingredient = iterator.next();
				statement.setInt(1, userID);
				statement.setInt(2, ingredient.getIngredientID());
				statement.setInt(3, 0);
				switch (ingredient.getMeasure()) {
				case "%":
					statement.setString(4, "l");
					break;
				case "g/l":
					statement.setString(4, "g");
					break;
				default:
					statement.setString(4, "g");
				}
				result = statement.executeUpdate();
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public int updatePantry(List<Pantry> pantries) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(updatePantry);

			Iterator<Pantry> pantryIT = pantries.iterator();

			while (pantryIT.hasNext()) {
				Pantry pantry = pantryIT.next();
				statement.setInt(1, pantry.getAvailability());
				statement.setInt(2, pantry.getUserID());
				statement.setInt(3, pantry.getIngredientID());
				result = statement.executeUpdate();
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public List<Pantry> findUserPantry(int userID) {
		List<Pantry> pantry = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
			statement = connect.prepareStatement(userPantry);
			statement.setInt(1, userID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int ingredientID = resultSet.getInt("P.ingredientID");
				String ingredientName = resultSet.getString("I.name");
				int availability = resultSet.getInt("P.availability");
				String measure = resultSet.getString("P.measure");
				Pantry ingredient = new Pantry(userID, ingredientID, ingredientName, availability, measure);
				pantry.add(ingredient);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return pantry;
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
