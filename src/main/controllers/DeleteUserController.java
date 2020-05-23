package main.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DBConnector;
import main.User;

public class DeleteUserController implements Initializable {
	double x, y;

	@FXML
	AnchorPane anchor;

	@FXML
	AnchorPane anchorleft;

	@FXML
	AnchorPane anchorthin;

	@FXML
	private TableView<User> tableview;
	@FXML
	private TableColumn<User, String> user_name;
	@FXML
	private TableColumn<User, String> user_pass;
	@FXML
	private TableColumn<User, String> user_type;

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
	Button anuloButton;

	@FXML
	Button DeleteButton;

	@FXML
	TextField usernameField;

	@FXML
	PasswordField passwordField;

	@FXML
	TextField userTypeField;

	@FXML
	Label labUserInfo;

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/menaxhoUsers.fxml"));
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

	private void deleteUser(String user_name) throws Exception {
		Connection con = DBConnector.getConnection();
		String sql = "DELETE FROM users WHERE user_name = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, user_name);
		if (stmt.executeUpdate() <= 0) {
			labUserInfo.setText("Failed!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
		} else {
			labUserInfo.setText("Delete Succesfully!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
		}
	}

	@FXML
	private void DeleteButtonClicked(ActionEvent event) throws Exception {
		try {
			User user = tableview.getSelectionModel().getSelectedItem();
			deleteUser(user.getUser_name());
			tableview.getSelectionModel().clearSelection();
			tableview.getItems().remove(user);
			setUserToUI(new User());
		} catch (Exception ex) {
			ex.printStackTrace();
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
		loader.setLocation(getClass().getResource("../views/menaxhoUsers.fxml"));
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

	private void setUserToUI(User user) {
		usernameField.setText(user.getUser_name());
		passwordField.setText(user.getUser_pass());
		userTypeField.setText(user.getUser_type());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		user_name.setCellValueFactory(new PropertyValueFactory<>("user_name"));
		user_pass.setCellValueFactory(new PropertyValueFactory<>("user_pass"));
		user_type.setCellValueFactory(new PropertyValueFactory<>("user_type"));

		tableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tableview.getSelectionModel().selectedItemProperty().addListener((ov, old, _new) -> {
			if (_new != null)
				setUserToUI(_new);
		});
		try {
			tableview.setItems(getUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ObservableList<User> getUsers() throws Exception {
		ObservableList<User> list = FXCollections.observableArrayList();
		Connection con = DBConnector.getConnection();
		String SQL = "Select * from users";
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet res = stmt.executeQuery();

		while (res.next()) {
			String user_name = res.getString("user_name");
			String user_pass = res.getString("user_pass");
			String user_type = res.getString("user_type");
			list.add(new User(user_name, user_pass, user_type));
		}

		return list;
	}

}
