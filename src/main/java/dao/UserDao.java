package dao;

import java.sql.*;

import java.util.ArrayList;

import controller.database.connection.MySQLConnection;
import model.user.Brewer;

public class UserDao {
	
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
	
	public ArrayList<Brewer> findAllUsers() {
		ArrayList<Brewer> brewers = new ArrayList<Brewer>();
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(findAllUsers);			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("idUser");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				Date dateOfBirth = resultSet.getDate("dateOfBirth");
				String mail = resultSet.getString("mail");
				String password = resultSet.getString("password");
				String kind = resultSet.getString("kind");
				Brewer brewer = new Brewer(id, name, surname,dateOfBirth,mail,password,kind);
				brewers.add(brewer);
			}
			
		} catch (SQLException  e) {
			System.out.println("SQL Error");
		}catch  (ClassNotFoundException e) {
			System.out.println("Connection Error");
		} finally {
			close();
		}
		return brewers;
	}
	
	private void close() {
		try {
			if (resultSet != null) resultSet.close();
			if (statement != null) statement.close();
			if (connect != null) connect.close();			
		} catch (Exception e) {
			System.out.println("Connection Error");
		}
	}
}
