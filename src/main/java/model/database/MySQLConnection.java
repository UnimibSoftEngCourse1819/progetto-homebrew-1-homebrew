package model.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {

	private String url;
	private String user;
	private String password;

	final Logger logger = Logger.getLogger("MyLog");

	public MySQLConnection() {
		InputStream inputStream = null;

		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			inputStream.close();

		} catch (IOException e) {
			logger.log(Level.SEVERE, "MySQLConnector error", e);
		}
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
