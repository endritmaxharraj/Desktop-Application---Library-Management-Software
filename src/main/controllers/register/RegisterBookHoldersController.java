package main.controllers.register;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXAutoCompletePopup;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.models.BookHolders;
import main.models.Lexuesit;
import main.utils.DBConnector;

public class RegisterBookHoldersController implements Initializable {


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
	TextField emriMbiemriField;

	@FXML
	TextField emriAutoriLibritField;

	@FXML
	DatePicker dataMarrjesField;

	@FXML
	DatePicker dataKthimitField;

	@FXML
	TextField emriPuntoritField;

	@FXML
	Button anuloButton;

	@FXML
	Button regjistroButton;

	@FXML
	Label labSuccess;

	ObservableList<Lexuesit> oblist = FXCollections.observableArrayList();

	private void emptyField() {
		emriMbiemriField.setText("");
		emriAutoriLibritField.setText("");
		dataMarrjesField.setValue(null);
		dataKthimitField.setValue(null);
		emriPuntoritField.setText("");

	}


	private void updateStock(String Emri, int Sasia) throws SQLException {
		Connection conUpdateStock = DBConnector.getConnection();
		int newSasia = Sasia - 1;
		String SQLUpdateStock = "Update regjistrimilibrave Set Sasia = '" + newSasia
				+ "' Where Emri_Librit_Autori = '"
				+ Emri + "'";
		PreparedStatement updateStockstmt = conUpdateStock.prepareStatement(SQLUpdateStock);
		updateStockstmt.executeUpdate();

	}

	public static ArrayList<String> getAllEmriMbiemriPrejDb() throws ClassNotFoundException, SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "Select * From Lexuesit";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rst = stmt.executeQuery();
		ArrayList<String> lexuesitList = new ArrayList<>();
		while (rst.next()) {
			String lexuesi = rst.getString("EmriMbiemri");
			lexuesitList.add(lexuesi);
		}
		return lexuesitList;
	}

	public static ArrayList<String> getAllEmriAutorLibritPrejDb() throws ClassNotFoundException, SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "Select * From RegjistrimiLibrave";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rst = stmt.executeQuery();
		ArrayList<String> libratList = new ArrayList<>();
		while (rst.next()) {
			String libri = rst.getString("Emri_Librit_Autori");
			libratList.add(libri);
		}
		return libratList;
	}

	void suggestEmriMbiemriPrejDatabase() throws Exception {
		JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
		
		autoCompletePopup.getSuggestions().addAll(getAllEmriMbiemriPrejDb());

		autoCompletePopup.setSelectionHandler(event -> {
			emriMbiemriField.setText(event.getObject());
		});

		// filter
		emriMbiemriField.textProperty().addListener(observable -> {
			autoCompletePopup.filter(string -> string.toLowerCase().contains(emriMbiemriField.getText().toLowerCase()));
			if (autoCompletePopup.getFilteredSuggestions().isEmpty() || emriMbiemriField.getText().isEmpty()) {
				autoCompletePopup.hide();
				// nese e fshin emrimbiemri qiti krejt suggests tmundeshem
			} else {
				autoCompletePopup.show(emriMbiemriField);
			}
		});
	}

	void suggestEmriAutoriLibritPrejDatabase() throws Exception {
		JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();

		autoCompletePopup.getSuggestions().addAll(getAllEmriAutorLibritPrejDb());

		autoCompletePopup.setSelectionHandler(event -> {
			emriAutoriLibritField.setText(event.getObject());
		});

		// filter
		emriAutoriLibritField.textProperty().addListener(observable -> {
			autoCompletePopup
					.filter(string -> string.toLowerCase().contains(emriAutoriLibritField.getText().toLowerCase()));
			if (autoCompletePopup.getFilteredSuggestions().isEmpty() || emriAutoriLibritField.getText().isEmpty()) {
				autoCompletePopup.hide();
				// nese e fshin emriautorilibrit qiti krejt suggests tmundeshem
			} else {
				autoCompletePopup.show(emriAutoriLibritField);
			}
		});
	}

	private BookHolders shtoNjeMbajtesTeLibrit(String EmriMbiemri, String EmriLibritAutori, DatePicker DataMarrjes,
			DatePicker DataKthimit, String EmriPuntorit) throws Exception {

		Connection con = DBConnector.getConnection();
		String SQL = "INSERT INTO MbajtesitLibrave (EmriMbiemri,Emri_Librit_Autori,Data_Marrjes,Data_Kthimit,user_name) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriMbiemriField.getText());
		stmt.setString(2, emriAutoriLibritField.getText());
		stmt.setDate(3, Date.valueOf(dataMarrjesField.getValue()));
		stmt.setDate(4, Date.valueOf(dataKthimitField.getValue()));
		stmt.setString(5, emriPuntoritField.getText());

		stmt.executeUpdate();

		ResultSet tableKeys = stmt.getGeneratedKeys();
		if (tableKeys.next()) {
			return new BookHolders(EmriMbiemri, EmriLibritAutori, DataMarrjes, DataKthimit, EmriPuntorit);
		}

		return null;
	}

	private void checkNeseEgziston() throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "SELECT * FROM MbajtesitLibrave WHERE EmriMbiemri = ? AND Emri_Librit_Autori = ? AND Data_Marrjes = ? AND Data_Kthimit = ? AND user_name = ?";
		PreparedStatement stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, emriMbiemriField.getText());
		stmt.setString(2, emriAutoriLibritField.getText());
		stmt.setDate(3, Date.valueOf(dataMarrjesField.getValue()));
		stmt.setDate(4, Date.valueOf(dataKthimitField.getValue()));
		stmt.setString(5, emriPuntoritField.getText());

		ResultSet rs = stmt.executeQuery();

		if (rs.isBeforeFirst()) {
			labSuccess.setText("Mbajtesi i librit me kete te dhena egziston ne database !");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else {
			BookHolders bookHolders = new BookHolders();
			bookHolders = shtoNjeMbajtesTeLibrit(bookHolders.getEmriMbiemri(), bookHolders.getEmriLibritAutori(),
					bookHolders.getDataMarrjes(), bookHolders.getDataKthimit(), bookHolders.getEmriPuntorit());
			labSuccess.setText("Mbajtesi i librit eshte regjistruar me sukses!");
			labSuccess.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();

		}

	}

	@FXML
	private void regjistroButtonClicked(ActionEvent event) throws Exception {
		if (emriMbiemriField.getText().isEmpty() || emriAutoriLibritField.getText().isEmpty()
				|| Date.valueOf(dataMarrjesField
						.getValue()) == null
				|| Date.valueOf(dataKthimitField.getValue()) == null || emriPuntoritField.getText().isEmpty()) {
			labSuccess.setText("Duhet ti plotesoni te gjitha te dhenat!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().length() < 3
				|| emriAutoriLibritField.getText()
						.length() < 3
				|| emriPuntoritField.getText().length() < 3) {
			labSuccess.setText("Ju lutem plotesoni te dhenat me te dhena te verteta!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().matches("[0-9]+")
				|| emriAutoriLibritField.getText().matches("[0-9]+")
				|| emriPuntoritField.getText().matches("[0-9]+")) {
			labSuccess.setText("Nuk lejohen numra!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		} else {
			Connection con1 = DBConnector.getConnection();
			String SQL1 = "SELECT * FROM RegjistrimiLibrave WHERE `Emri_Librit_Autori` = ?";
			PreparedStatement stmt1 = con1.prepareStatement(SQL1);
			stmt1.setString(1, emriAutoriLibritField.getText());
			ResultSet rs = stmt1.executeQuery();
			if (rs.next()) {
				int Sasia = rs.getInt("Sasia");
			if (Sasia > 0) {
				updateStock(emriAutoriLibritField.getText(), Sasia);
				checkNeseEgziston();
				labSuccess.setText("Mbajtesi i librit eshte regjistruar me sukses, stock u bo update!");
				labSuccess.setStyle("-fx-text-fill: #2DFE54;");
			} else if (Sasia == 0) {
				labSuccess.setText("Nuk egziston ne Stock!");
				labSuccess.setStyle("-fx-text-fill: #FF073A;");
			}
		}
		else {
			labSuccess.setText("Kontrolloni Emrin e Librit dhe Emrin e Lexuesit!");
			labSuccess.setStyle("-fx-text-fill: #FF073A;");
		}

		}
	}

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			suggestEmriMbiemriPrejDatabase();
			suggestEmriAutoriLibritPrejDatabase();
		} catch (Exception e) {
			e.getCause();
		}
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
		loader.setLocation(getClass().getResource("../../views/main-screen.fxml"));
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
