package main.controllers.update;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.models.Lexuesit;
import main.utils.DBConnector;

public class UpdateReaderController implements Initializable {

	double x, y;

	@FXML
	AnchorPane anchor;

	@FXML
	AnchorPane anchorleft;

	@FXML
	AnchorPane anchorthin;

	@FXML
	private TableView<Lexuesit> tableview;
	@FXML
	private TableColumn<Lexuesit, String> EmriMbiemri;
	@FXML
	private TableColumn<Lexuesit, String> Profesioni;
	@FXML
	private TableColumn<Lexuesit, String> Adresa;
	@FXML
	private TableColumn<Lexuesit, String> Sektori;
	@FXML
	private TableColumn<Lexuesit, Integer> Cmimi;
	@FXML
	private TableColumn<Lexuesit, DatePicker> Regjistrimi;
	@FXML
	private TableColumn<Lexuesit, DatePicker> Skadimi;

	@FXML
	HBox hBox;

	@FXML
	Text krijo;

	@FXML
	ImageView imgBack;

	@FXML
	ImageView imgMin;

	@FXML
	ImageView imgX;

	@FXML
	Button anuloButton;

	@FXML
	Button updateButton;

	@FXML
	TextField emriMbiemriField;

	@FXML
	TextField profesioniField;

	@FXML
	TextField adresaField;

	@FXML
	TextField cmimiField;

	@FXML
	TextField sektoriField;

	@FXML
	TextField dateField;

	@FXML
	TextField dateSkadimiField;

	@FXML
	Label labUserInfo;

	private void emptyFields() {
		emriMbiemriField.setText("");
		profesioniField.setText("");
		adresaField.setText("");
		cmimiField.setText("");
		sektoriField.setText("");
		dateField.setText("");
		dateSkadimiField.setText("");

	}

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/update/updateData.fxml"));
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

	private Lexuesit getLexuesitFromUI() {
		String EmriMbiemri = emriMbiemriField.getText();
		String Profesioni = profesioniField.getText();
		String Adresa = adresaField.getText();
		String Sektori = sektoriField.getText();
		int Cmimi = Integer.parseInt(cmimiField.getText());
		Date dateRegjistrimi = Date.valueOf(dateField.getText());
		Date dateSkadimi = Date.valueOf(dateSkadimiField.getText());

		return new Lexuesit(EmriMbiemri, Profesioni, Adresa, Sektori, Cmimi, dateRegjistrimi, dateSkadimi);
	}

	private void updateLexuesit(String EmriMbiemri, String Profesioni, String Adresa, String Sektori, int Cmimi,
			Date dateRegjistrimi, Date dateSkadimi) throws Exception {
		Connection con = DBConnector.getConnection();
		String sql = "UPDATE Lexuesit SET EmriMbiemri = ?, Profesioni = ?, Adresa = ?, Sektori = ?, Cmimi = ?, Regjistrimi = ?, Skadimi = ? WHERE EmriMbiemri = ?";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, EmriMbiemri);
		stmt.setString(2, Profesioni);
		stmt.setString(3, Adresa);
		stmt.setString(4, Sektori);
		stmt.setInt(5, Cmimi);
		stmt.setDate(6, dateRegjistrimi);
		stmt.setDate(7, dateSkadimi);
		stmt.setString(8, EmriMbiemri);

		stmt.executeUpdate();
	}

	@FXML
	private void updateButtonClicked(ActionEvent event) throws Exception {
		check();
	}

	private void check() throws Exception {
		if (emriMbiemriField.getText().isEmpty() || profesioniField.getText().isEmpty()
				|| adresaField.getText().isEmpty() || cmimiField.getText()
						.isEmpty()
				|| dateField.getText().isEmpty() || dateSkadimiField.getText().isEmpty()) {
			labUserInfo.setText("Duhet ti plotesoni te gjitha te dhenat!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().length() < 2 || profesioniField.getText().length() < 2
				|| adresaField.getText().length() < 2) {
			labUserInfo.setText("Ju lutem plotesoni te dhenat me te dhena te verteta!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().matches("[0-9]+") && profesioniField.getText().matches("[0-9]+")
				&& adresaField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra tek Emri dhe Mbiemri, Adresa, Profesioni!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().matches("[0-9]+") && profesioniField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra tek Emri dhe Mbiemri, Profesioni!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().matches("[0-9]+") && adresaField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra tek Emri dhe Mbiemri, Adresa!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (profesioniField.getText().matches("[0-9]+") && adresaField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra tek Adresa, Profesioni!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().matches("[0-9]+") && profesioniField.getText().matches("[0-9]+")
				&& adresaField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra tek Emri dhe Mbiemri, Adresa, Profesioni!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriMbiemriField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra tek Emri dhe Mbiemri!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (profesioniField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra tek Profesioni!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (adresaField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra tek Adresa!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		}
		else {
			update();
			labUserInfo.setText("Update Succesfully!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
			emptyFields();
		}
	}

	private void update() {
		try {
			Lexuesit selected = tableview.getSelectionModel().getSelectedItem();
			Lexuesit changed = getLexuesitFromUI();

			updateLexuesit(changed.getEmriMbiemri(), changed.getProfesioni(), changed.getAdresa(), changed.getSektori(),
					changed.getCmimi(), changed.getDateRegjistrimi(), changed.getDateSkadimi());
			selected.setEmriMbiemri(changed.getEmriMbiemri());
			selected.setProfesioni(changed.getProfesioni());
			selected.setAdresa(changed.getAdresa());
			selected.setSektori(changed.getSektori());
			selected.setCmimi(changed.getCmimi());
			selected.setDateRegjistrimi(changed.getDateRegjistrimi());
			selected.setDateSkadimi(changed.getDateSkadimi());

			tableview.getSelectionModel().clearSelection();
			tableview.refresh();
			setLexuesitToUI(new Lexuesit());
		} catch (Exception ex) {
			ex.getCause();
		}
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
		loader.setLocation(getClass().getResource("../../views/update/updateData.fxml"));
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

	private void setLexuesitToUI(Lexuesit lexuesit) {

		emriMbiemriField.setText(lexuesit.getEmriMbiemri());
		profesioniField.setText(lexuesit.getProfesioni());
		adresaField.setText(lexuesit.getAdresa());
		sektoriField.setText(lexuesit.getSektori());
		cmimiField.setText(Integer.toString(lexuesit.getCmimi()));
		dateField.setText(lexuesit.getDateRegjistrimi().toString());
		dateSkadimiField.setText(lexuesit.getDateSkadimi().toString());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		EmriMbiemri.setCellValueFactory(new PropertyValueFactory<>("EmriMbiemri"));
		Profesioni.setCellValueFactory(new PropertyValueFactory<>("Profesioni"));
		Adresa.setCellValueFactory(new PropertyValueFactory<>("Adresa"));
		Sektori.setCellValueFactory(new PropertyValueFactory<>("Sektori"));
		Cmimi.setCellValueFactory(new PropertyValueFactory<>("Cmimi"));
		Regjistrimi.setCellValueFactory(new PropertyValueFactory<>("dateRegjistrimi"));
		Skadimi.setCellValueFactory(new PropertyValueFactory<>("dateSkadimi"));

		tableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tableview.getSelectionModel().selectedItemProperty().addListener((ov, old, _new) -> {
			if (_new != null)
				setLexuesitToUI(_new);
		});
		try {
			tableview.setItems(getLexuesit());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ObservableList<Lexuesit> getLexuesit() throws Exception {
		ObservableList<Lexuesit> list = FXCollections.observableArrayList();
		Connection con = DBConnector.getConnection();
		String SQL = "Select * from Lexuesit";
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet res = stmt.executeQuery();

		while (res.next()) {
			String EmriMbiemri = res.getString("EmriMbiemri");
			String Profesioni = res.getString("Profesioni");
			String Adresa = res.getString("Adresa");
			String Sektori = res.getString("Sektori");
			int Cmimi = res.getInt("Cmimi");
			Date dateRegjistrimi = res.getDate("Regjistrimi");
			Date dateSkadimi = res.getDate("Skadimi");

			list.add(new Lexuesit(EmriMbiemri, Profesioni, Adresa, Sektori, Cmimi, dateRegjistrimi, dateSkadimi));
		}

		return list;
	}

}
