package main.controllers;

import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.DBConnector;

public class LoginController implements Initializable {

	double x, y;

	@FXML
	void draged(MouseEvent event) {

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setX(event.getScreenX() - x);
		primaryStage.setY(event.getScreenY() - y);
	}

	@FXML
	void pressed(MouseEvent event) {
		x = event.getSceneX();
		y = event.getSceneY();
	}

	@FXML
	private VBox vBox;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private ImageView LoginLogo;

	@FXML
	private Label lab;

	@FXML
	private void min(MouseEvent event) {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setIconified(true);
	}

	@FXML
	private void close(MouseEvent event) {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	private void loginButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		try {
			Connection con = DBConnector.getConnection();
			String SQL = "SELECT * FROM `users` WHERE `user_name` = ? AND `user_pass` = ?";
			PreparedStatement stmt = con.prepareStatement(SQL);
			stmt.setString(1, usernameField.getText());
			stmt.setString(2, passwordField.getText());
			ResultSet rs1 = stmt.executeQuery();

			if (rs1.next())
				{
					// Login Succesfully Admin
					String getUserType = rs1.getNString("user_type");
					if (getUserType.equals("admin")) {
						loader.setLocation(getClass().getResource("../views/admin.fxml"));
						loader.load();
						Parent parent = loader.getRoot();
					Scene scene = new Scene(parent);
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage.setScene(scene);
					primaryStage.centerOnScreen();
					primaryStage.show();
					scene.setOnKeyPressed(e -> {
						if (e.getCode() == KeyCode.ESCAPE) {
							primaryStage.close();
						}
					});
				} else if (getUserType.equals("user")) {
					// Login Succesfully User
					loader.setLocation(getClass().getResource("../views/user.fxml"));
					loader.load();
					Parent parent = loader.getRoot();
					Scene scene = new Scene(parent);
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage.setScene(scene);
					primaryStage.centerOnScreen();
					primaryStage.show();
					scene.setOnKeyPressed(e -> {
						if (e.getCode() == KeyCode.ESCAPE) {
							primaryStage.close();
						}
					});
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
		AdminController admin = loader.getController();
		admin.setText(usernameField.getText());
	}

	@FXML
	private void enterKeyPressLogin(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			FXMLLoader loader = new FXMLLoader();
			try {
				Connection con = DBConnector.getConnection();
				String SQL = "SELECT * FROM `users` WHERE `user_name` = ? AND `user_pass` = ?";
				PreparedStatement stmt = con.prepareStatement(SQL);
				stmt.setString(1, usernameField.getText());
				stmt.setString(2, passwordField.getText());
				ResultSet rs1 = stmt.executeQuery();

				if (rs1.next()) {
					// Login Succesfully Admin
					String getUserType = rs1.getNString("user_type");
					if (getUserType.equals("admin")) {
						loader.setLocation(getClass().getResource("../views/admin.fxml"));
						loader.load();
						Parent parent = loader.getRoot();
						Scene scene = new Scene(parent);
						Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						primaryStage.setScene(scene);
						primaryStage.centerOnScreen();
						primaryStage.show();
						scene.setOnKeyPressed(e -> {
							if (e.getCode() == KeyCode.ESCAPE) {
								primaryStage.close();
							}
						});
					} else if (getUserType.equals("user")) {
						// Login Succesfully User
						loader.setLocation(getClass().getResource("../views/user.fxml"));
						loader.load();
						Parent parent = loader.getRoot();
						Scene scene = new Scene(parent);
						Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						primaryStage.setScene(scene);
						primaryStage.centerOnScreen();
						primaryStage.show();
						scene.setOnKeyPressed(e -> {
							if (e.getCode() == KeyCode.ESCAPE) {
								primaryStage.close();
							}
						});
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Invalid credentials!");
					alert.showAndWait();
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			AdminController admin = loader.getController();
			admin.setText(usernameField.getText());
		}

	}

	@FXML
	private void enterKeyPassLogin(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			FXMLLoader loader = new FXMLLoader();
			try {
				Connection con = DBConnector.getConnection();
				String SQL = "SELECT * FROM `users` WHERE `user_name` = ? AND `user_pass` = ?";
				PreparedStatement stmt = con.prepareStatement(SQL);
				stmt.setString(1, usernameField.getText());
				stmt.setString(2, passwordField.getText());
				ResultSet rs1 = stmt.executeQuery();

				if (rs1.next()) {
					// Login Succesfully Admin
					String getUserType = rs1.getNString("user_type");
					if (getUserType.equals("admin")) {
						loader.setLocation(getClass().getResource("../views/admin.fxml"));
						loader.load();
						Parent parent = loader.getRoot();
						Scene scene = new Scene(parent);
						Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						primaryStage.setScene(scene);
						primaryStage.centerOnScreen();
						primaryStage.show();
						scene.setOnKeyPressed(e -> {
							if (e.getCode() == KeyCode.ESCAPE) {
								primaryStage.close();
							}
						});
					} else if (getUserType.equals("user")) {
						// Login Succesfully User
						loader.setLocation(getClass().getResource("../views/user.fxml"));
						loader.load();
						Parent parent = loader.getRoot();
						Scene scene = new Scene(parent);
						Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						primaryStage.setScene(scene);
						primaryStage.centerOnScreen();
						primaryStage.show();
						scene.setOnKeyPressed(e -> {
							if (e.getCode() == KeyCode.ESCAPE) {
								primaryStage.close();
							}
						});
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Invalid credentials!");
					alert.showAndWait();
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			AdminController admin = loader.getController();
			admin.setText(usernameField.getText());
		}

	}

}
