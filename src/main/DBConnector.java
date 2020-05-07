package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	public static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/LibraryDatabase", "root", "Qapobon123");
		return con;
	}

}
