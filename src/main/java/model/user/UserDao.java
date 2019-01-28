package model.user;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.MySQLConnection;

public class UserDao {

	final Logger logger = Logger.getLogger("MyLog");
	private static String sqlError = "SQL error";
	private static String connectionError = "Connection Error";

	public UserDao() {
	}

	private Connection connect = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;

	private static String findAllUsers = "SELECT * From User";
	private static String createUser = "INSERT INTO User (name, surname, dateOfBirth, email, password, rights) VALUES(?,?,?,?,?,?)";
	private static String deleteUser = "DELETE FROM User WHERE userID =?";
	private static String seletUserById = "SELECT * From User WHERE userId =?";
	private static String seletUserByEmail = "SELECT * From User WHERE email =?";
	private static String updateUser = "UPDATE User SET name =?, surname =?, dateOfBirth =?, email =?, password =?, rights =? WHERE userID =?";

	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(findAllUsers);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				Date dateOfBirth = resultSet.getDate("dateOfBirth");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String rights = resultSet.getString("rights");
				User user = new User(id, name, surname, dateOfBirth, email, password, rights);
				users.add(user);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return users;
	}

	public User selectUserById(int userId) {
		User user = null;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(seletUserById);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				int id = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				Date dateOfBirth = resultSet.getDate("dateOfBirth");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String rights = resultSet.getString("rights");
				user = new User(id, name, surname, dateOfBirth, email, password, rights);
				return user;
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return user;
	}

	public User selectUserByEmail(String userEmail) {
		User user = null;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(seletUserByEmail);
			statement.setString(1, userEmail);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				int id = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				Date dateOfBirth = resultSet.getDate("dateOfBirth");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String rights = resultSet.getString("rights");
				user = new User(id, name, surname, dateOfBirth, email, password, rights);
				return user;
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}
		return user;
	}

	public int createUser(User user) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(createUser);
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			java.sql.Date castDate = new java.sql.Date(user.getDateOfBirth().getTime());
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String reportDate = dateformat.format(castDate);
			statement.setString(3, reportDate);
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getRights());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public int updateUser(int id, User user) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(updateUser);
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			java.sql.Date castDate = new java.sql.Date(user.getDateOfBirth().getTime());
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String reportDate = dateformat.format(castDate);
			statement.setString(3, reportDate);
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getRights());
			statement.setInt(7, id);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
			close();
		}

		return result;
	}

	public int deleteUser(int id) {
		int result = -1;
		MySQLConnection mysql;
		try {
			mysql = new MySQLConnection();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(),
					mysql.getPassword());
			statement = connect.prepareStatement(deleteUser);
			statement.setInt(1, id);
			result = statement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sqlError, e);
		} finally {
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
