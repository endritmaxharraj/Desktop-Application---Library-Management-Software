package main.controllers.register;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.models.Lexuesit;
import main.utils.DBConnector;

public class RegisterReaderController implements Initializable {

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
	Button regjistroButton;

	@FXML
	Button anuloButton;

	@FXML
	ImageView backSign;

	@FXML
	ImageView min;

	@FXML
	ImageView close;

	@FXML
	TextField emriMbiemriField;

	@FXML
	TextField profesioniField;

	@FXML
	TextField adresaField;

	@FXML
	TextField cmimiField;

	@FXML
	DatePicker dateField;

	@FXML
	DatePicker dateSkadimiField;

	@FXML
	Label labSuccess;

	@FXML
	ComboBox<String> comboBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> sektoriList = FXCollections.observableArrayList("Kati i pare", "Kati i dyte",
				"Kati i trete");
		comboBox.getItems().addAll(sektoriList);
	}

	@FXML
	private void regjistroNjeLiberClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/register/registerBook.fxml"));
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
	private void regjistroNjeLexuesClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/register/registerReader.fxml"));
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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/register/registerBookHolders.fxml"));
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
	private void regjistroVendinClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/register/registerVendet.fxml"));
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
	private void regjistroButtonClicked(ActionEvent event) throws Exception {

		boolean iscomboBoxEmpty = (comboBox.getValue() == null);

		if (emriMbiemriField.getText().isEmpty() || profesioniField.getText()
				.isEmpty()
				|| adresaField.getText().isEmpty()
				|| iscomboBoxEmpty
					|| cmimiField.getText().isEmpty() || Date.valueOf(dateField.getValue()) == null
					|| Date.valueOf(dateSkadimiField.getValue()) == null) {
				labSuccess.setText("Duhet ti plotesoni te gjitha te dhenat!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			} else if (emriMbiemriField.getText().length() < 2 || profesioniField.getText().length() < 2
					|| adresaField.getText().length() < 2) {
				labSuccess.setText("Ju lutem plotesoni te dhenat me te dhena te verteta!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			} else if (emriMbiemriField.getText().matches("[0-9]+")
					&& profesioniField.getText().matches("[0-9]+")
					&& adresaField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra tek Emri dhe Mbiemri, Adresa, Profesioni!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			} else if (emriMbiemriField.getText().matches("[0-9]+") && profesioniField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra tek Emri dhe Mbiemri, Profesioni!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			} else if (emriMbiemriField.getText().matches("[0-9]+") && adresaField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra tek Emri dhe Mbiemri, Adresa!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			}
			else if (profesioniField.getText().matches("[0-9]+") && adresaField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra tek Adresa, Profesioni!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			} else if (emriMbiemriField.getText().matches("[0-9]+") && profesioniField.getText().matches("[0-9]+")
					&& adresaField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra tek Emri dhe Mbiemri, Adresa, Profesioni!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			}
			else if (emriMbiemriField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra tek Emri dhe Mbiemri!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			} else if (profesioniField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra tek Profesioni!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			}
			else if (adresaField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra tek Adresa!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			}
			else {
				checkNeseEgziston();
			}
}

	private void emptyField() {
		emriMbiemriField.setText("");
		profesioniField.setText("");
		adresaField.setText("");
		comboBox.getSelectionModel().clearSelection();
		dateField.setValue(null);
		dateSkadimiField.setValue(null);
	}

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/admin.fxml"));
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
		loader.setLocation(getClass().getResource("../../views/admin.fxml"));
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

	private Lexuesit shtonjeLexues(String EmriMbiemri, String Profesioni, String Adresa, String Sektori, int Cmimi,
			DatePicker Regjistrimi, DatePicker Skadimi) throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "INSERT INTO Lexuesit (EmriMbiemri,Profesioni,Adresa,Sektori,Cmimi,Regjistrimi,Skadimi) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriMbiemriField.getText());
		stmt.setString(2, profesioniField.getText());
		stmt.setString(3, adresaField.getText());
		stmt.setString(4, comboBox.getSelectionModel().getSelectedItem().toString());
		stmt.setInt(5, Integer.parseInt(cmimiField.getText()));
		stmt.setDate(6, Date.valueOf(dateField.getValue()));
		stmt.setDate(7, Date.valueOf(dateSkadimiField.getValue()));

		stmt.executeUpdate();

		ResultSet tableKeys = stmt.getGeneratedKeys();
		if (tableKeys.next()) {
			return new Lexuesit(EmriMbiemri, Profesioni, Adresa, Sektori, Cmimi, Regjistrimi, Skadimi);
		}
		return null;
	}
	
	private void checkNeseEgziston() throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "SELECT * FROM Lexuesit WHERE EmriMbiemri = ? AND Profesioni = ? AND Adresa = ? AND Sektori = ? AND Cmimi = ? AND Regjistrimi = ? AND Skadimi = ?";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriMbiemriField.getText());
		stmt.setString(2, profesioniField.getText());
		stmt.setString(3, adresaField.getText());
		stmt.setString(4, comboBox.getSelectionModel().getSelectedItem().toString());
		stmt.setInt(5, Integer.parseInt(cmimiField.getText()));
		stmt.setDate(6, Date.valueOf(dateField.getValue()));
		stmt.setDate(7, Date.valueOf(dateSkadimiField.getValue()));

		ResultSet rs = stmt.executeQuery();

		if (rs.isBeforeFirst()) {
			labSuccess.setText("Lexuesi me kete te dhena egziston ne database !");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else {
			Lexuesit lexuesit = new Lexuesit();
			lexuesit = shtonjeLexues(lexuesit.getEmriMbiemri(), lexuesit.getProfesioni(), lexuesit.getAdresa(),
					lexuesit.getSektori(), lexuesit.getCmimi(), lexuesit.getRegjistrimi(), lexuesit.getSkadimi());
			labSuccess.setText("Lexuesi eshte regjistruar me sukses!");
			labSuccess.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();

		}

	}

}
