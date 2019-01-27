package pantry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.MySQLConnection;

public class PantryDao {
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError ="Connection Error";
	
	public PantryDao() {}

	
	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private static String createPantry = "INSERT INTO Pantry (userID, ingredientID, avalibility) VALUES(?,?,?)";
	private static String updatePantry = "UPDATE Pantry SET  avalibility =? WHERE userID =? AND ingredientID, =?";
	
	public int createPantry(int userID) {
		int result = -1;
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(createPantry);				
			
			//userID sarà ottenuto dalla sessione
			for(int i=1; i<=6; i++){			
				 statement.setInt(1, userID);
				 statement.setInt(2, i);
				 statement.setInt(3, 0);
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
	
	
	public int UpdatePantry(ArrayList<Pantry> ingredients) {
		int result = -1;
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(updatePantry);				
			
			//la Servlet passera un arrayList di Pantry dove il primo attributo sarà l'id dell'utente preso dalla sessione
			//il secondo attributo sarà l'id dell ingrediente
			//il terzo attributo sarà la nuova disponibilità
			for(int i=0; i<ingredients.size(); i++){
				Pantry ingredient= ingredients.get(i);
				
				 statement.setInt(1, ingredient.getAvailability());
				 statement.setInt(2, ingredient.getIngredientId());
				 statement.setInt(3, ingredient.getUserID());
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
