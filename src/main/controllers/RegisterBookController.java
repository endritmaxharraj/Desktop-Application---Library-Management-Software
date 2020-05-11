package main.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.DBConnector;
import main.Librat;

public class RegisterBookController implements Initializable {

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
	Label labSuccess;

	@FXML
	TextField emriLibritAutoriField;

	@FXML
	TextField vitiBotimitField;

	@FXML
	TextField ISBNKodiField;

	@FXML
	TextField sasiaField;

	@FXML
	ImageView imageView;

	@FXML
	GridPane gridPane;

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
	private void regjistroButtonClicked(ActionEvent event) throws Exception {
		if (emriLibritAutoriField.getText().isEmpty() || vitiBotimitField
				.getText()
				.isEmpty()
				|| ISBNKodiField.getText().isEmpty() || sasiaField.getText().isEmpty()) {
			labSuccess.setText("Duhet ti plotesoni te gjitha te dhenat!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriLibritAutoriField.getText()
				.length() < 3
				|| vitiBotimitField.getText().length() < 3) {
			labSuccess.setText("Ju lutem plotesoni te dhenat me te dhena te verteta!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else if (ISBNKodiField.getText().length() < 13) {
			labSuccess.setText("ISBNKodi eshte nje numer 13Shifror!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		}
		else if (emriLibritAutoriField.getText().matches("[0-9]+")) {
			labSuccess.setText("Nuk lejohen numra, tek Emri dhe Autori i Librit");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else if (ISBNKodiField.getText().matches("^[a-zA-Z\\s]+$")
				|| sasiaField.getText().matches("^[a-zA-Z\\s]+$")
				|| vitiBotimitField.getText().matches("^[a-zA-Z\\s]+$")) {
			labSuccess.setText(
					"Nuk lejohen shkronja, tek Viti i Botimit, Sasia, ISBNKodi!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		}
		else {
				checkNeseEgziston();
			}
	}

	private Librat shtoNjeLiber(String EmriLibritAutori, int VitiBotimit,
			long ISBNKodi,
				int Sasia)
			throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "INSERT INTO RegjistrimiLibrave(Emri_Librit_Autori,Viti_Botimit,ISBNKodi,Sasia) VALUE(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriLibritAutoriField.getText());
		stmt.setInt(2, Integer.parseInt(vitiBotimitField.getText()));
		stmt.setLong(3, Long.parseLong(ISBNKodiField.getText()));
		stmt.setInt(4, Integer.parseInt(sasiaField.getText()));
		
		stmt.executeUpdate();

		ResultSet tableKeys = stmt.getGeneratedKeys();
		if (tableKeys.next()) {
			return new Librat(EmriLibritAutori, VitiBotimit, ISBNKodi, Sasia);
		}
		
		return null;
	}

	private void checkNeseEgziston() throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "SELECT * FROM RegjistrimiLibrave WHERE Emri_Librit_Autori = ? AND Viti_Botimit = ? AND ISBNKodi = ? AND Sasia = ?";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriLibritAutoriField.getText());
		stmt.setInt(2, Integer.parseInt(vitiBotimitField.getText()));
		stmt.setLong(3, Long.parseLong(ISBNKodiField.getText()));
		stmt.setInt(4, Integer.parseInt(sasiaField.getText()));

		ResultSet rs = stmt.executeQuery();

		if (rs.isBeforeFirst()) {
			labSuccess.setText("Libri me kete te dhena egziston ne database !");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else {
			Librat librat = new Librat();
			librat = shtoNjeLiber(librat.getEmriLibritAutori(), librat.getVitiBotimit(), librat.getISBNKodi(),
					librat.getSasia());
			labSuccess.setText("Libri eshte regjistruar me sukses!");
			labSuccess.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();

		}

	}

	@FXML
	private void regjistroNjeLiberClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/registerBook.fxml"));
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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/registerBookHolders.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	private void emptyField() {
		emriLibritAutoriField.setText("");
		vitiBotimitField.setText("");
		ISBNKodiField.setText("");
		sasiaField.setText("");
	}
	
}
