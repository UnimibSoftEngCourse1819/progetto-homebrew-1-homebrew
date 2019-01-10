package controller.database.connection;

import java.sql.*;

public class MySQLAccess {
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private MySQLConnection connector = null;




	public ResultSet getQuery(String query) throws Exception {
		try {
			connector = new MySQLConnection();
			connect = DriverManager.getConnection(connector.getUrl(), connector.getUser(), connector.getPassword());

			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			return resultSet;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}


	}
	
	// You need to close the resultSet
	private void close() throws Exception {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
