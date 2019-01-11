package controller.user;

import java.sql.*;
import java.util.ArrayList;

import controller.database.connection.MySQLAccess;

import model.user.UserBrewer;

public class UserDAO {

	private MySQLAccess access = null;

	public UserDAO() {
		access = new MySQLAccess();
	}

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
				String email = resultSet.getString("mail");
				String password = resultSet.getString("password");
				String tipo = resultSet.getString("tipo");
				UserBrewer user = new UserBrewer(id, nome, cognome, dataDiNascita, email, password, tipo);
				users.add(user);
			}

		} catch (Exception e) {
			System.out.println("errore qui");
		}
		return users;
	}

}
