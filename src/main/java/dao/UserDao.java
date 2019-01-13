package dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.database.connection.MySQLConnection;
import model.user.Brewer;

public class UserDao {
	
	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError ="Connection Error";
	private static UserDao instance = null;
	
	private UserDao() {}
	
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private static String findAllUsers = "SELECT * From Utente";
	//private static String findAllUsers = "SELECT * From User";
	private static String createUser = "INSERT INTO Utente (nome, cognome, dataDiNascita, mail, password, tipo) VALUES(?,?,?,?,?,?)";
	//private static String createUser = "INSERT INTO User (name, surname, dateOfBirth, mail, password, kind) VALUES(?,?,?,?,?,?)";
	private static String deleteUser = "DELETE FROM Utente WHERE idUtente =?";
	//private static String deleteUser = "DELETE FROM User WHERE userID =?";
	private static String seletUserById = "SELECT * From Utente WHERE idUtente =?";
	//private static String seletUserById = "SELECT * From User WHERE userId =?";
	private static String updateUser = "UPDATE Utente SET nome =?, cognome =?, dataDiNascita =?, mail =?, password =?, tipo =? WHERE idUtente =?";
	//private static String updateUser = "UPDATE User SET name =?, surname =?, dateOfBirth =?, mail =?, password =?, kind =? WHERE userID =?";
	
	public List<Brewer> findAllUsers() {
		List<Brewer> brewers = new ArrayList<>();
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(findAllUsers);			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("idUtente");
				//int id = resultSet.getInt("userId");
				String name = resultSet.getString("nome");
				//String name = resultSet.getString("name");
				String surname = resultSet.getString("cognome");
				//String surname = resultSet.getString("surname");
				Date dateOfBirth = resultSet.getDate("dataDiNascita");
				//Date dateOfBirth = resultSet.getDate("dateOfBirth");
				String mail = resultSet.getString("mail");
				String password = resultSet.getString("password");
				String kind = resultSet.getString("tipo");
				//String kind = resultSet.getString("kind");
				Brewer brewer = new Brewer(id, name, surname,dateOfBirth,mail,password,kind);
				brewers.add(brewer);
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);
		}catch  (ClassNotFoundException e) {
			logger.log(Level.SEVERE, connectionError, e);
		} finally {
			close();
		}
		return brewers;
	}
	
	public Brewer selectUserById(int userId) {
		Brewer brewer = null;
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(seletUserById);			
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("idUtente");
				//int id = resultSet.getInt("userId");
				String name = resultSet.getString("nome");
				//String name = resultSet.getString("name");
				String surname = resultSet.getString("cognome");
				//String surname = resultSet.getString("surname");
				Date dateOfBirth = resultSet.getDate("dataDiNascita");
				//Date dateOfBirth = resultSet.getDate("dateOfBirth");
				String mail = resultSet.getString("mail");
				String password = resultSet.getString("password");
				String kind = resultSet.getString("tipo");
				//String kind = resultSet.getString("kind");
				brewer = new Brewer(id, name, surname,dateOfBirth,mail,password,kind);
				return brewer;
			}
			
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);
		}catch  (ClassNotFoundException e) {
			logger.log(Level.SEVERE, connectionError, e);
		} finally {
			close();
		}
		return brewer;
	}
	
	public int createUser(Brewer brewer) {
		int result = -1;
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(createUser);				
			statement.setString(1, brewer.getName());
			statement.setString(2, brewer.getSurname());
			java.sql.Date castDate = new java.sql.Date(brewer.getDateOfBirth().getTime());
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String reportDate = dateformat.format(castDate);
			statement.setString(3, reportDate);
			statement.setString(4, brewer.getMail());
			statement.setString(5, brewer.getPassword());
			statement.setString(6, brewer.getKind());
			result = statement.executeUpdate();
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);		
		}catch  (ClassNotFoundException e) {
			logger.log(Level.SEVERE, connectionError, e);
		} finally {
			close();
		}
		
		return result;
	}
	
	public int updateUser(int id, Brewer brewer) {
		int result = -1;
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(updateUser);				
			statement.setString(1, brewer.getName());
			statement.setString(2, brewer.getSurname());
			java.sql.Date castDate = new java.sql.Date(brewer.getDateOfBirth().getTime());
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String reportDate = dateformat.format(castDate);
			statement.setString(3, reportDate);
			statement.setString(4, brewer.getMail());
			statement.setString(5, brewer.getPassword());
			statement.setString(6, brewer.getKind());
			statement.setInt(7, id);
			result = statement.executeUpdate();
		} catch (SQLException  e) {
			logger.log(Level.SEVERE,sqlError , e);		
		}catch  (ClassNotFoundException e) {
			logger.log(Level.SEVERE, connectionError, e);
		} finally {
			close();
		}
		
		return result;
	}
	
	public int deleteUser(int id) {
		int result = -1;
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(deleteUser);				
			statement.setInt(1, id);
			result = statement.executeUpdate();
			
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
