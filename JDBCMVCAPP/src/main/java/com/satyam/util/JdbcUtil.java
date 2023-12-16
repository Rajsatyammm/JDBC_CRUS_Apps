package com.satyam.util;

//import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

	private JdbcUtil() {
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws SQLException, IOException {
//		FileInputStream fis = new FileInputStream("src\\main\\java\\com\\satyam\\properties\\db.properties");
//		Properties properties = new Properties();
//		properties.load(fis);

//		String url = properties.getProperty("url");
//		String username = properties.getProperty("user");
//		String password = properties.getProperty("password");
		
		String url = "jdbc:mysql://localhost:3306/raj";
		String username = "root";
		String password = "satyam";

		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}
