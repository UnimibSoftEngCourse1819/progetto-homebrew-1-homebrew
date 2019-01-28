package model.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import model.database.MySQLConnection;

public class Login {

	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	private byte[] passIN;

	private byte[] passDB;

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private void getUserDao(String login) {
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());

			statement = connect.prepareStatement("SELECT * FROM User WHERE email = ?");
			statement.setString(1, login);
			resultSet = statement.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		}
	}

	public boolean match(String login, String pass) {
		getUserDao(login);
		boolean match = false;
		try {
			if (resultSet.next()) {
				String password = resultSet.getString("password");
				SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
				passIN = digestSHA3.digest(pass.getBytes());
				passDB = Hex.decode(password);
				if (Arrays.equals(passIN, passDB)) {
					match = true;
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return match;
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
