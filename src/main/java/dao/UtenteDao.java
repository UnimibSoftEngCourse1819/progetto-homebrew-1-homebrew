package dao;

import java.sql.*;
import java.util.ArrayList;

import controller.database.connection.MySQLConnection;
import model.user.Birraio;

public class UtenteDao {
	
	private static UtenteDao instance = null;
	
	private UtenteDao() {}
	
	public static UtenteDao getInstance() {
		if(instance == null) {
			instance = new UtenteDao();
		}
		return instance;
	}
	
	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	private static String findAllUsers = "SELECT * From Utente";
	
	public ArrayList<Birraio> findAllUsers() {
		ArrayList<Birraio> birrai = new ArrayList<Birraio>();
		try {
			Class.forName(MySQLConnection.getDriver());  
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(), MySQLConnection.getPassword());
			statement = connect.prepareStatement(findAllUsers);			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("idUtente");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				Date dataDiNascita = resultSet.getDate("dataDiNascita");
				String mail = resultSet.getString("mail");
				String password = resultSet.getString("password");
				String tipo = resultSet.getString("tipo");
				Birraio birraio = new Birraio(id, nome, cognome,dataDiNascita,mail,password,tipo);
				birrai.add(birraio);
			}
			
		} catch (Exception e) {
			System.out.println(MySQLConnection.getUrl());
			System.out.println(MySQLConnection.getUser());
			System.out.println(MySQLConnection.getPassword());
			System.out.println(connect);
			 e.printStackTrace();
		} finally {
			close();
		}
		return birrai;
	}
	
	private void close() {
		try {
			if (resultSet != null) resultSet.close();
			if (statement != null) statement.close();
			if (connect != null) connect.close();			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
}
