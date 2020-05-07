package main.controllers;

import java.io.IOException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterDataController implements Initializable {

	double x, y;

	@FXML
	AnchorPane anchorPane;

	@FXML
	AnchorPane rightanchorPane;

	@FXML
	AnchorPane leftanchorPane;

	@FXML
	AnchorPane buttonsanchorPane;

	@FXML
	Button regjistroNjeLiber;

	@FXML
	Button regjistroNjeLexues;

	@FXML
	Button regjistroMbajtesineLibrit;

	@FXML
	Button regjistroVendin;

	@FXML
	ImageView backSign;

	@FXML
	ImageView min;

	@FXML
	ImageView close;

	@FXML
	Label lab;

	@FXML
	GridPane gridPane;

	@FXML
	TextField emriField;

	@FXML
	TextField mbiemriField;

	@FXML
	TextField profesioniField;

	@FXML
	TextField adresaField;

	@FXML
	TextField sektoriField;

	@FXML
	DatePicker dateField;

	@FXML
	Button anuloButton;

	@FXML
	Button regjistroButton;

	@FXML
	private void regjistroButtonClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private void regjistroNjeLiberClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void regjistroNjeLexuesClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/registerReader.fxml"));
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
	private void regjistroMbajtesineLibritClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void regjistroVendinClicked(ActionEvent event) throws Exception {
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

}
