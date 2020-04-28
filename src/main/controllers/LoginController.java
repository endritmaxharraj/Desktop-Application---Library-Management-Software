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

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	private void loginButtonClicked(ActionEvent event) throws Exception {
		String username = usernameField.getText();
		String password = passwordField.getText();

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/LibraryDatabase", "root",
					"password");
			String SQL = "SELECT * FROM `users` WHERE `user_name` = ? AND `user_pass` = ?";
			PreparedStatement stmt = con.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs1 = stmt.executeQuery();

			if (rs1.next())
				{
					// Login Succesfully Admin
					String getUserType = rs1.getNString("user_type");
					if (getUserType.equals("admin")) {
					Parent parent = FXMLLoader.load(getClass().getResource("../views/admin.fxml"));
					Scene scene = new Scene(parent);
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage.setScene(scene);
					primaryStage.show();
				} else if (getUserType.equals("user")) {
					// Login Succesfully User
					Parent parent = FXMLLoader.load(getClass().getResource("../views/user.fxml"));
					Scene scene = new Scene(parent);
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage.setScene(scene);
					primaryStage.show();
				}
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Invalid credentials!");
					alert.showAndWait();
				}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
