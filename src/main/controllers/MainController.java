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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.components.ErrorPopupComponent;
import main.utils.DBConnector;
import main.utils.DateHelper;
import main.utils.SessionManager;

public class MainController implements Initializable {
	
	double x, y;

	@FXML
	private VBox vBox;

	@FXML
	private MenuBar menuBar;

	@FXML
	private Menu File;

	@FXML
	private MenuItem Close;

	@FXML
	private Menu Edit;

	@FXML
	private MenuItem Delete;

	@FXML
	private HBox firstButtons;

	@FXML
	private Menu Help;

	@FXML
	private MenuItem About;

	@FXML
	private Label lab;

	@FXML
	private Label lab1;

	@FXML
	private Button regjistroTeDhenaButton;

	@FXML
	private Button MenaxhoUseratButton;

	@FXML
	private Button kerkoTeDhenaButton;

	@FXML
	private Button menaxhoPunenButton;

	@FXML
	private Button shenimetButton;

	@FXML
	private Button qkyquButton;

	@FXML
	private Label statusLabel;


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
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		String statusText = "User %s logged in at %s";
		String user = SessionManager.user.getUser_name();
		String loginTime = DateHelper.toSqlFormat(SessionManager.lastLogin);
		statusLabel.setText(String.format(statusText, user, loginTime));
		try {
			Connection con = DBConnector.getConnection();
			String SQL = "Select * from users where user_name = ?";
			PreparedStatement st = con.prepareStatement(SQL);
			st.setString(1, user);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// Ndarja e privilegjeve
				String getUserType = rs.getNString("user_type");
				if (getUserType.equals("admin")) {
					// Button Show
//					MenaxhoUseratButton.setVisible(true);
					MenaxhoUseratButton.setDisable(false);

			} else if (getUserType.equals("user")) {
				// Button Hide
//				MenaxhoUseratButton.setVisible(false);
				MenaxhoUseratButton.setDisable(true);
			
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Invalid credentials!");
				alert.showAndWait();
			}
			}
		}
		
		catch (Exception e) {
			ErrorPopupComponent.show(e);
							}
	}

	@FXML
	private void regjistroTeDhenaButtonClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/register/registerdata.fxml"));
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
	private void MenaxhoUseratButtonClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/menaxho/menaxhoUsers.fxml"));
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
	private void kerkoTeDhenaButtonClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/kerko/kerkoteDhena.fxml"));
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
	private void menaxhoPunenButtonClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/menaxho/menaxhoData.fxml"));
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
	private void shenimetButtonClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void qkyquButtonClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
		Scene scene = new Scene(parent);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		SessionManager.user = null;
		primaryStage.centerOnScreen();
		primaryStage.show();
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				primaryStage.close();
			}
		});
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

}
