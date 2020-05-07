package main.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.stage.Stage;
import main.Lexuesit;

public class ReaderController implements Initializable {

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
	TextField cmimiField;

	@FXML
	DatePicker dateField;

	@FXML
	DatePicker dateSkadimiField;

	@FXML
	Label labSuccess;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private void regjistroNjeLiberClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void regjistroNjeLexuesClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void regjistroMbajtesineLibritClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void regjistroVendinClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void regjistroButtonClicked(ActionEvent event) throws Exception {

			if (emriField.getText().isEmpty() || mbiemriField.getText().isEmpty() || profesioniField.getText().isEmpty()
					|| adresaField.getText().isEmpty() || sektoriField.getText().isEmpty()
					|| cmimiField.getText().isEmpty() || Date.valueOf(dateField.getValue()) == null
					|| Date.valueOf(dateSkadimiField.getValue()) == null) {
				labSuccess.setText("Duhet ti plotesoni te gjitha te dhenat!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			} else if (emriField.getText().length() < 3 || mbiemriField
					.getText()
					.length() < 3
					|| profesioniField.getText().length() < 3 || adresaField.getText().length() < 3
					|| sektoriField.getText().length() < 3) {
				labSuccess.setText("Ju lutem plotesoni te dhenat me te dhena te verteta!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			}
			else if (emriField.getText().matches("[0-9]+") || mbiemriField.getText().matches("[0-9]+")
					|| profesioniField.getText().matches("[0-9]+") || adresaField.getText().matches("[0-9]+")
					|| sektoriField.getText().matches("[0-9]+")) {
				labSuccess.setText("Nuk lejohen numra !");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			}
			else {
				checkNeseEgziston();
			}
}

	private void emptyField() {
		emriField.setText("");
		mbiemriField.setText("");
		profesioniField.setText("");
		adresaField.setText("");
		sektoriField.setText("");
		dateField.setValue(null);
		dateSkadimiField.setValue(null);
	}

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

	private Lexuesit shtonjeLexues(String Emri, String Mbiemri, String Profesioni, String Adresa, String Sektori,
			int Cmimi, DatePicker Regjistrimi, DatePicker Skadimi) throws Exception {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/LibraryDatabase", "root", "Qapobon123");
		String SQL = "INSERT INTO Lexuesit (Emri,Mbiemri,Profesioni,Adresa,Sektori,Cmimi,Regjistrimi,Skadimi) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriField.getText());
		stmt.setString(2, mbiemriField.getText());
		stmt.setString(3, profesioniField.getText());
		stmt.setString(4, adresaField.getText());
		stmt.setString(5, sektoriField.getText());
		stmt.setInt(6, Integer.parseInt(cmimiField.getText()));
		stmt.setDate(7, Date.valueOf(dateField.getValue()));
		stmt.setDate(8, Date.valueOf(dateSkadimiField.getValue()));

		stmt.executeUpdate();

		ResultSet tableKeys = stmt.getGeneratedKeys();
		if (tableKeys.next()) {
			int Id = tableKeys.getInt(1);
			return new Lexuesit(Id, Emri, Mbiemri, Profesioni, Adresa, Sektori, Cmimi, Regjistrimi, Skadimi);
		}
		return null;
	}
	
	private void checkNeseEgziston() throws Exception {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/LibraryDatabase", "root", "Qapobon123");
		String SQL = "SELECT * FROM Lexuesit WHERE Emri = ? AND Mbiemri = ? AND Profesioni = ? AND Adresa = ? AND Sektori = ? AND Cmimi = ?";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriField.getText());
		stmt.setString(2, mbiemriField.getText());
		stmt.setString(3, profesioniField.getText());
		stmt.setString(4, adresaField.getText());
		stmt.setString(5, sektoriField.getText());
		stmt.setInt(6, Integer.parseInt(cmimiField.getText()));

		ResultSet rs = stmt.executeQuery();

		if (rs.isBeforeFirst()) {
			labSuccess.setText("Lexuesi me kete te dhena egziston ne database !");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
			emptyField();
		} else {
			Lexuesit lexuesit = new Lexuesit();
			lexuesit = shtonjeLexues(lexuesit.getEmri(), lexuesit.getMbiemri(), lexuesit.getProfesioni(),
					lexuesit.getAdresa(), lexuesit.getSektori(), lexuesit.getCmimi(), lexuesit.getRegjistrimi(),
					lexuesit.getSkadimi());
			labSuccess.setText("Lexuesi eshte regjistruar me sukses!");
			labSuccess.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();

		}

	}

}
