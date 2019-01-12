package controller.database.connection;


public class MySQLConnection {

	private static String url = "jdbc:mysql://micorx.com:3306/micorxco_brewday";
	private static String user = "micorxco_maik";
	private static String password = "b#(&mBJ8Q9RC1x1s";
	private static String driver = "com.mysql.jdbc.Driver";
	
	public static String getDriver() {
		return driver;
	}
	public static String getUrl() {
		return url;
	}
	public static String getUser() {
		return user;
	}
	public static String getPassword() {
		return password;
	}
	
	
	
}
