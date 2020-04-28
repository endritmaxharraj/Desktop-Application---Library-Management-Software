package main.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class AdminController implements Initializable {
	Connection conn;

	@FXML
	private Text admin;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			initDb();
		} catch (Exception e) {
			printError(e);
		}
	}

	private void initDb() throws Exception {
		if (conn == null || conn.isClosed()) {
			// Class.forName("com.mysql.cj.jdbc.Driver");
			// Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/LibraryDatabase", "root", "Qapobon123");
			// conn = DriverManager.getConnection("jdbc:sqlite:knk.db");
		}
	}

	private void printError(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Error");
		alert.setContentText(e.toString());
		alert.showAndWait();
	}

}
