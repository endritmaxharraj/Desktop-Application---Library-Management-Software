package main.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import main.models.User;
import main.repositories.UserRepository;
import main.utils.DBConnector;
import main.utils.SessionManager;

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

	private User login(String user_name, String user_pass) throws Exception {
		User user = UserRepository.find(user_name);
		if (user == null) {
			return null;
		}
		return user;
	}

	private boolean hasUsers() throws Exception {
		return UserRepository.count() > 0;
	}

	public static String findRole(String user_name) throws Exception {
		String admin = "admin";
		String user = "user";
		PreparedStatement stmt = DBConnector.getConnection()
				.prepareStatement("SELECT * from Users where user_name = ? Limit 1");
		stmt.setString(1, user_name);
		ResultSet res = stmt.executeQuery();
		String role = res.getString("user_type");
		if (role.equals("admin")) {
			return admin;
		} else {
			return user;
		}
	}


	@FXML
	private void loginButtonClicked(ActionEvent event) throws Exception {

			String username = usernameField.getText();
			String password = passwordField.getText();

			User user = null;
			if (hasUsers()) {
				user = login(username, password);

				SessionManager.user = user;
				SessionManager.lastLogin = new Date();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../views/main-screen.fxml"));
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
			} else {
				throw new Exception("user not egzist!");
			}
		}
	



	@FXML
	private void enterKeyPressLogin(KeyEvent event) {

	}

	@FXML
	private void enterKeyPassLogin(KeyEvent event) {

	}

}
