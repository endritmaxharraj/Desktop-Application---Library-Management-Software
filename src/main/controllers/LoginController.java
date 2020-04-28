package main.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	Connection conn;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			initDb();

		} catch (Exception e) {
			printError(e);
		}
	}

	@FXML
	private void loginButtonClicked(ActionEvent event) throws Exception {
		ResultSet rs;
		
		// get username and password from fxml
		String username = usernameField.getText();
		String password = passwordField.getText();
		String user_type = "admin";
		
		// query qe ben check per username dhe password qe tregon nese o admin shko te
		// adminpanel nese o user i thjesht shko te userpanel
		String query = "SELECT * FROM `users` WHERE `user_name` = ? AND `user_pass` = ? AND `user_type` = ?";
		
//		String queryAdmin = "SELECT * FROM `users` WHERE `user_type` = admin";
//		String queryUser = "SELECT * FROM `users` WHERE `user_type` = user";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, user_type);

			rs = stmt.executeQuery();

			if(rs.next())
			{
				Parent parent = FXMLLoader.load(getClass().getResource("../views/admin.fxml"));
				Scene scene = new Scene(parent);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(scene);
				primaryStage.show();

			}
			else {
				Parent parent = FXMLLoader.load(getClass().getResource("../views/user.fxml"));
				Scene scene = new Scene(parent);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			
			

		} catch (Exception e) {
			printError(e);
		}

	}

	private void printError(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Error");
		alert.setContentText(e.toString());
		alert.showAndWait();
	}

	private void initDb() throws Exception {
		if (conn == null || conn.isClosed()) {
			// Class.forName("com.mysql.cj.jdbc.Driver");
			// Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/LibraryDatabase", "root", "Qapobon123");
			// conn = DriverManager.getConnection("jdbc:sqlite:knk.db");
		}
	}
	
}
