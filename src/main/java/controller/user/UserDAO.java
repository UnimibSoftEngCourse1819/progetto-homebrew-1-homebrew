package controller.user;

import java.sql.*;
import java.util.ArrayList;

import controller.database.connection.MySQLAccess;

import model.user.UserBrewer;

public class UserDAO {
	
	private static UserDAO instance = null;
	
	private UserDAO() {}
	
	public static UserDAO getInstance() {
		if(instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	private MySQLAccess access = null;

		
	public ArrayList<UserBrewer> findAllUsers() {
		ArrayList<UserBrewer> users = new ArrayList<UserBrewer>();

		String query = "SELECT * From Utente";
		try {

			ResultSet resultSet = access.getQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("idUtente");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				Date dataDiNascita = resultSet.getDate("dataDiNascita");
				String mail = resultSet.getString("mail");
				String password = resultSet.getString("password");
				String tipo = resultSet.getString("tipo");
				UserBrewer user = new UserBrewer(id, nome, cognome, dataDiNascita, mail, password, tipo);
				users.add(user);
			}

		} catch (Exception e) {
			System.out.println("errore qui");
			e.printStackTrace();
		}
		return users;
	}
	
}
