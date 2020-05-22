package main.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DBConnector;
import main.User;

public class CreateUserController implements Initializable {

	double x, y;

	@FXML
	AnchorPane anchor;

	@FXML
	ComboBox <String> comboBox;

	@FXML
	AnchorPane anchorleft;

	@FXML
	AnchorPane anchorthin;

	@FXML
	HBox hBox;

	@FXML
	Text krijo;

	@FXML
	ImageView imgBack;

	@FXML
	ImageView imgMin;

	@FXML
	ImageView imgX;

	@FXML
	ImageView imgUserLogo;

	@FXML
	ImageView imgPasswordLogo;

	@FXML
	ImageView imgConfirmPasswordLogo;

	@FXML
	GridPane gridPane;

	@FXML
	TextField usernameField;

	@FXML
	PasswordField passwordField;

	@FXML
	PasswordField confirmPasswordField;

	@FXML
	Button anuloButton;

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
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
	}

	@FXML
	Button krijoButton;

	@FXML
	Label labUserInfo;

	@FXML
	private void krijoButtonClicked(ActionEvent event) throws Exception {

		boolean iscomboBoxEmpty = (comboBox.getValue() == null);

		if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()
				|| iscomboBoxEmpty) {
			labUserInfo.setText("Duhet ti plotesoni te gjitha te dhenat!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (!(passwordField.getText().equals(confirmPasswordField.getText()))) {
			labUserInfo.setText("Password dhe Confirm Password nuk perputhen!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		}
		else if (usernameField.getText().length() < 2
				|| passwordField.getText()
						.length() < 2) {
			labUserInfo.setText("Nuk lejohet user dhe password me 2 karaktere!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		}

		else {
			checkNeseEgziston();
		}

	}

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
	private void min(MouseEvent event) {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setIconified(true);
	}

	@FXML
	private void close(MouseEvent event) {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.close();
	}

	@FXML
	private void back(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
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
	}

	private void emptyField() {
		usernameField.setText("");
		passwordField.setText("");
		confirmPasswordField.setText("");
		comboBox.getSelectionModel().clearSelection();
	}

	private User shtoNjeUser(String user_name, String user_pass, String user_type) throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "INSERT INTO users (user_name,user_pass,user_type) VALUES (?,?,?)";

		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, usernameField.getText());
		stmt.setString(2, passwordField.getText());
		stmt.setString(3, comboBox.getSelectionModel().getSelectedItem().toString());

		stmt.executeUpdate();

		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			return new User(user_name, user_pass, user_type);
		}

		return null;
	}

	private void checkNeseEgziston() throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "SELECT * FROM users WHERE user_name = ? AND user_pass = ? AND user_type = ?";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, usernameField.getText());
		stmt.setString(2, passwordField.getText());
		stmt.setString(3, comboBox.getSelectionModel().getSelectedItem().toString());

		ResultSet rs = stmt.executeQuery();

		if (rs.isBeforeFirst()) {
			labUserInfo.setText("Useri me kete te dhena egziston ne database !");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else {
			User user = new User();
			user = shtoNjeUser(user.getUser_name(), user.getUser_pass(), user.getUser_type());
			labUserInfo.setText("Useri eshte regjistruar me sukses!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> usertypeList = FXCollections.observableArrayList("admin", "user");
		comboBox.getItems().addAll(usertypeList);
	}

}
