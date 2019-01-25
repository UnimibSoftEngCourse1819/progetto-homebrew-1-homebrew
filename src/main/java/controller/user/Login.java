package controller.user;

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

import controller.database.connection.MySQLConnection;

public class Login {

	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	private byte[] passIN;

	private byte[] passDB;

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	public boolean match(String login, String pass) {
		boolean result = false;
		try {
			// Class.forName(MySQLConnection.getDriver());
			connect = DriverManager.getConnection(MySQLConnection.getUrl(), MySQLConnection.getUser(),
					MySQLConnection.getPassword());
			statement = connect.prepareStatement("SELECT password FROM User WHERE email = ?");
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			System.out.println(resultSet);
			if (resultSet.next()) {
				String passGET = resultSet.getString("password");
				
				SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
				passIN = digestSHA3.digest(pass.getBytes());

				passDB = Hex.decode(passGET);

				result = Arrays.equals(passIN, passDB);
			}			

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
			/*
			 * }catch (ClassNotFoundException e) { logger.log(Level.SEVERE, connectionError,
			 * e);
			 */} finally {
			close();
		}

		return result;
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
