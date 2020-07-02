package main.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnector {

	private static Connection con = null;

	public static Connection getConnection() throws SQLException {
		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection(AppConfig.get().getConnectionString());
	}
		return con;
	}

	public static void migrate() throws Exception {
		String driverType = AppConfig.get().getDriverType();
		String autoIncFunc = driverType.equals("mysql") ? "AUTO_INCREMENT" : "AUTOINCREMENT";

		ArrayList<String> queries = new ArrayList<>();
		queries.add(String.format(
				"Create Database if not exists LibraryDatabase;", autoIncFunc));

		queries.add(String.format("Use LibraryDatabase;", autoIncFunc));

		queries.add(String
				.format(
				"CREATE TABLE IF NOT EXISTS Users(\n" + "user_name varchar(25) not null,\n"
						+ "user_pass varchar(45) not null,\n"
						+ "user_type varchar(10) not null,\n" + "Primary Key(user_name))\n"
						+ "ENGINE = InnoDB;",
				autoIncFunc));


		queries.add(String.format(
				"CREATE TABLE IF NOT EXISTS Lexuesit (\n"
						+ "EmriMbiemri varchar(50) NOT NULL,\n"
						+ "Profesioni varchar(40) NOT NULL,\n" + "Adresa varchar(255) NOT NULL,\n"
						+ "Sektori varchar(40) NOT NULL,\n" + "Cmimi INT NOT NULL,\n" + "Regjistrimi Date NOT NULL,\n"
						+ "Skadimi Date NOT NULL,\n" + "Primary Key(EmriMbiemri))\n" + "ENGINE = InnoDB;",
				autoIncFunc));

		queries.add(String.format("CREATE TABLE IF NOT EXISTS RegjistrimiLibrave (\n"
				+ "Emri_Librit_Autori varchar(50) NOT NULL,\n"
				+ "Viti_Botimit int not null,\n" + "ISBNKodi LONG NOT NULL,\n" + "Sasia int null,\n"
				+ "Primary Key(Emri_Librit_Autori))\n" + "ENGINE = InnoDB;", autoIncFunc));

		
		queries.add(String
				.format("CREATE TABLE IF NOT EXISTS todolist(\n" + "id INT (5) PRIMARY KEY AUTO_INCREMENT NOT NULL,\n"
						+ "data DATE NOT NULL,\n" + "text VARCHAR(100) NOT NULL\n" + ");", autoIncFunc));
		

		queries.add(String.format("CREATE TABLE IF NOT EXISTS MbajtesitLibrave (\n"
				+ "EmriMbiemri varchar(50) NOT NULL,\n"
				+ "Emri_Librit_Autori varchar(50) NOT NULL,\n" + "Data_Marrjes DATE NOT NULL,\n"
				+ "Data_Kthimit DATE NOT NULL,\n" + "user_name varchar(25) not null,\n"
				+ "foreign key(user_name) references users(user_name) on delete cascade,\n"
				+ "foreign key(EmriMbiemri) references Lexuesit(EmriMbiemri) on delete cascade,\n"
				+ "foreign key(Emri_Librit_Autori) references RegjistrimiLibrave(Emri_Librit_Autori) on delete cascade\n"
				+ ")\n" + "ENGINE = InnoDB;", autoIncFunc));

		queries.add(String.format("CREATE TABLE IF NOT EXISTS Vendet (\n"
				+ " nrVendi int primary key,\n"
				+ " EmriMbiemri varchar(50),\n" + " Sektori varchar(40) NOT NULL,\n" + " DataKoha timestamp,\n"
				+ " foreign key(EmriMbiemri) references Lexuesit(EmriMbiemri) on delete cascade\n" + ")\n"
				+ "ENGINE = InnoDB;", autoIncFunc));

		queries.add(String.format(
				"INSERT into Users(user_name, user_pass, user_type)\n"
						+ "SELECT * FROM (SELECT 'gentrit', '1234', 'admin') AS tmp\n" + "WHERE NOT EXISTS (\n"
						+ "    SELECT user_name FROM Users WHERE user_name = 'gentrit'\n" + ") LIMIT 1;",
				autoIncFunc));

		queries.add(String.format(
				"INSERT into Lexuesit(EmriMbiemri, Profesioni, Adresa, Sektori, Cmimi, Regjistrimi, Skadimi)\n"
						+ "SELECT * FROM (SELECT 'Gentrit Ibishi', 'Student - FIEK','Deshmoret e Kombit', 'Kati i pare', 10, '2020-05-11', '2021-05-12') AS tmp\n"
						+ "WHERE NOT EXISTS (\n"
						+ "    SELECT EmriMbiemri FROM Lexuesit WHERE EmriMbiemri = 'Gentrit Ibishi'\n" + ") LIMIT 1;",
				autoIncFunc));

		queries.add(String
				.format("INSERT into Lexuesit(EmriMbiemri, Profesioni, Adresa, Sektori, Cmimi, Regjistrimi, Skadimi)\n"
						+ "SELECT * FROM (SELECT 'Mirsad Dibrani', 'Student - FIEK','Fushe Kosove', 'Kati i pare', 10, '2020-01-01', '2021-01-02') AS tmp\n"
						+ "WHERE NOT EXISTS (\n"
						+ "    SELECT EmriMbiemri FROM Lexuesit WHERE EmriMbiemri = 'Mirsad Dibrani'\n"
						+ ") LIMIT 1;",
				autoIncFunc));

		queries.add(String
				.format(
						"INSERT into RegjistrimiLibrave(Emri_Librit_Autori, Viti_Botimit, ISBNKodi, Sasia)\n"
								+ "SELECT * FROM (SELECT 'Plaku dhe Deti, Ernest Hemingway', 1951, 9781559946360, 5) AS tmp\n"
								+ "WHERE NOT EXISTS (\n"
								+ "    SELECT Emri_Librit_Autori FROM RegjistrimiLibrave WHERE Emri_Librit_Autori = 'Plaku dhe Deti, Ernest Hemingway'\n"
								+ ") LIMIT 1;",
						autoIncFunc));

		queries.add(String.format("INSERT into RegjistrimiLibrave(Emri_Librit_Autori, Viti_Botimit, ISBNKodi, Sasia)\n"
				+ "SELECT * FROM (SELECT 'Keshtjella, Ismail Kadare', 1970, 991559946000, 5) AS tmp\n"
				+ "WHERE NOT EXISTS (\n"
				+ "    SELECT Emri_Librit_Autori FROM RegjistrimiLibrave WHERE Emri_Librit_Autori = 'Keshtjella, Ismail Kadare'\n"
				+ ") LIMIT 1;",
				autoIncFunc));


		Connection con = getConnection();
		for (String query : queries) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		}
	}

}
