package main.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShenimetController implements Initializable {

		double x, y;

		@FXML
		AnchorPane anchorPane;

		@FXML
		AnchorPane panelanchorPane;

		@FXML
		ImageView backSign;

		@FXML
		ImageView min;

		@FXML
		ImageView close;

		@FXML
		Label lab;

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

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