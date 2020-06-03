package main.controllers.partials;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.utils.AppConfig;

public class AboutController implements Initializable {
	
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
	private Label appNameLabel;
	@FXML
	private Label versionLabel;
	@FXML
	private Label releasedLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		appNameLabel.setText(AppConfig.get().getAppName());
		versionLabel.setText("Version: " + AppConfig.get().getVersion());
		releasedLabel.setText("Released: " + AppConfig.get().getReleased());
	}

}
