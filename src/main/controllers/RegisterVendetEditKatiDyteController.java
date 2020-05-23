package main.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.DBConnector;
import main.Vendet;

public class RegisterVendetEditKatiDyteController implements Initializable {

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
	GridPane gridPane;

	@FXML
	Button anuloButton;

	@FXML
	Button regjistroButton;

	@FXML
	Button button1;

	@FXML
	Label Page1;

	@FXML
	Label Page2;

	@FXML
	Label Page3;

	@FXML
	TextField nrField;

	@FXML
	TextField dateField;

	@FXML
	TextField emriMbiemriField;

	@FXML
	ComboBox<String> comboBox;

	@FXML
	Label labSuccess;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> sektoriList = FXCollections.observableArrayList("Kati i dyte");
		comboBox.getItems().addAll(sektoriList);
	}

	public void myfunction(String text) {
		this.nrField.setText(text);
	}

	public void myfunction1(String text) {
		this.dateField.setText(text);
	}

	@FXML
	private void onMouseClickedPage1(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/registerVendet.fxml"));
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
	private void onMouseClickedPage2(MouseEvent event) throws Exception {

	}

	@FXML
	private void onMouseClickedPage3(MouseEvent event) throws Exception {

	}

	@FXML
	private void regjistroButtonClicked(ActionEvent event) throws Exception {
		boolean iscomboBoxEmpty = (comboBox.getValue() == null);

		if (emriMbiemriField.getText().isEmpty() || iscomboBoxEmpty) {
			labSuccess.setText("Duhet ti plotesoni te gjitha te dhenat!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().length() < 3) {
			labSuccess.setText("Ju lutem plotesoni te dhenat me te dhena te verteta!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().matches("[0-9]+")) {
			labSuccess.setText("Nuk lejohen numra tek Emri dhe Mbiemri!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else {
			Connection con = DBConnector.getConnection();
			String checkLexuesitSQL = "SELECT * FROM LEXUESIT WHERE EmriMbiemri = ?";
			PreparedStatement checkNeseLexeusiEgziston = con.prepareStatement(checkLexuesitSQL);
			checkNeseLexeusiEgziston.setString(1, emriMbiemriField.getText());

			ResultSet rs = checkNeseLexeusiEgziston.executeQuery();

			if (!rs.next()) {
				labSuccess.setText("Lexuesi me kete Emer dhe mbiemer '" + emriMbiemriField.getText()
						+ "' nuk figuron si lexues ne database!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			} else {
				checkNrDheEgzekuto();
			}

		}
	}

	private Vendet shtoNjeLexuesNeVend(int nrVendi, String EmriMbiemri, String Sektori,
			Timestamp DataKoha)
			throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "INSERT INTO Vendet(nrVendi,EmriMbiemri,Sektori,DataKoha) VALUE(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setInt(1, Integer.parseInt(nrField.getText()));
		stmt.setString(2, emriMbiemriField.getText());
		stmt.setString(3, comboBox.getSelectionModel().getSelectedItem().toString());

		String sDate1 = dateField.getText();
		Timestamp ts = Timestamp.valueOf(sDate1);
		stmt.setTimestamp(4, ts);

		stmt.executeUpdate();

		ResultSet tableKeys = stmt.getGeneratedKeys();
		if (tableKeys.next()) {
			return new Vendet(nrVendi, EmriMbiemri, Sektori, DataKoha);
		}

		return null;
	}

	private void emptyField() {
		emriMbiemriField.setText("");
		nrField.setText("");
		comboBox.getSelectionModel().clearSelection();
		dateField.setText("");
	}

	private void checkNrDheEgzekuto() throws Exception {
		Connection con = DBConnector.getConnection();
		String nrSQL = "SELECT * FROM Vendet WHERE nrVendi = ?";
		PreparedStatement stmtNr = con.prepareStatement(nrSQL);

		stmtNr.setString(1, nrField.getText());

		ResultSet rs = stmtNr.executeQuery();

		if (rs.isBeforeFirst()) {
			labSuccess.setText("Nr eshte i zene, shko back dhe provo nje tjeter!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else {
			checkNeseEgziston();
		}

	}

	private void checkNeseEgziston() throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "SELECT * FROM Vendet WHERE EmriMbiemri = ?";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriMbiemriField.getText());

		ResultSet rs = stmt.executeQuery();

		if (rs.isBeforeFirst()) {
			labSuccess.setText("Lexuesi ka nje vend ne database!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else {
			Vendet vendet = new Vendet();
			vendet = shtoNjeLexuesNeVend(vendet.getNrVendi(), vendet.getEmriMbiemri(), vendet.getSektori(),
					vendet.getDataKoha());
			labSuccess.setText("Vendi eshte regjistruar me sukses!");
			labSuccess.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();

		}

	}

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/registerVendet.fxml"));
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
	private void button1Clicked(ActionEvent event) throws Exception {

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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/registerVendet.fxml"));
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
		loader.setLocation(getClass().getResource("../views/registerVendetKatiDyte.fxml"));
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
