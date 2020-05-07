package main.controllers;

import java.net.URL;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminController implements Initializable {

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
	private Button krijoUserButton;

	@FXML
	private Button kerkoTeDhenaButton;

	@FXML
	private Button menaxhoPunenButton;

	@FXML
	private Button shenimetButton;

	@FXML
	private Button qkyquButton;

	public void setText(String name) {
		this.lab.setText(name);
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@FXML
	private void regjistroTeDhenaButtonClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/registerdata.fxml"));
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
	private void krijoUserButtonClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/createuser.fxml"));
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
	}

	@FXML
	private void menaxhoPunenButtonClicked(ActionEvent event) throws Exception {
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
