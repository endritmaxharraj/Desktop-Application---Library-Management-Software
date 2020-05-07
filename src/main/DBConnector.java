package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	public static Connection getConnection() throws SQLException {
		// Ketu i ndrroni keto me passwordin e MYSQL db tjuv
		String url = "jdbc:mysql://127.0.0.1/LibraryDatabase";
		String user = "root";
		String password = "toor";

		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}

}
