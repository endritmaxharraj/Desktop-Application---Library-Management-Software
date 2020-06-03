package main.controllers.kerko;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.models.Lexuesit;
import main.utils.DBConnector;

public class KerkoLexuesController implements Initializable {

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
	Button kerkoLibra;

	@FXML
	Button kerkoLexues;

	@FXML
	Button kerkoMbajtesiteLibrit;

	@FXML
	Button kerkoVendet;

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
	TableView<Lexuesit> tableview;

	@FXML
	TableColumn<Lexuesit, String> col_EmriMbiemri;

	@FXML
	TableColumn<Lexuesit, String> col_profesioni;

	@FXML
	TableColumn<Lexuesit, String> col_adresa;

	@FXML
	TableColumn<Lexuesit, String> col_sasia;

	@FXML
	TableColumn<Lexuesit, Integer> col_cmimi;

	@FXML
	TableColumn<Lexuesit, DatePicker> col_regjistrimi;

	@FXML
	TableColumn<Lexuesit, DatePicker> col_skadimi;

	ObservableList<Lexuesit> oblist = FXCollections.observableArrayList();

	@FXML
	TextField filterField;

	@FXML
	Label label;

	@FXML
	private void regjistroButtonClicked(ActionEvent event) throws Exception {
	}

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			Connection con = DBConnector.getConnection();
			String SQL = "Select * from Lexuesit";
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				oblist.add(new Lexuesit(rs.getString("EmriMbiemri"), rs.getString("Profesioni"), rs.getString("Adresa"),
						rs.getString("Sektori"), rs.getInt("Cmimi"), rs.getDate("Regjistrimi"), rs.getDate("Skadimi")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		col_EmriMbiemri.setCellValueFactory(new PropertyValueFactory<>("EmriMbiemri"));
		col_profesioni.setCellValueFactory(new PropertyValueFactory<>("Profesioni"));
		col_adresa.setCellValueFactory(new PropertyValueFactory<>("Adresa"));
		col_sasia.setCellValueFactory(new PropertyValueFactory<>("Sektori"));
		col_cmimi.setCellValueFactory(new PropertyValueFactory<>("Cmimi"));
		col_regjistrimi.setCellValueFactory(new PropertyValueFactory<>("dateRegjistrimi"));
		col_skadimi.setCellValueFactory(new PropertyValueFactory<>("dateSkadimi"));

		// Krijojm nje FilterdList i cili si fillim i paraqet krejt te dhenat nga oblist
		FilteredList<Lexuesit> filteredData = new FilteredList<>(oblist, b -> true);

		// Vedosim predicate(kallxuesin) sa here qe ndryshon filteredData
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Lexuesit -> {
				// Nese Filteri(SearchBoxi) eshte i zbrazet atehere paraqiti krejt te dhenat nga
				// tabela

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Perndryshe krahaso Emrin e Librit dhe Autori, VitiBotimit, ISBNKodi, Sasia me
				// tekstin qe shkruhet ne Searchbox
				String lowerCaseFilter = newValue.toLowerCase();

				if (Lexuesit.getEmriMbiemri().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me getEmriMbiemri.
				} else if (Lexuesit.getProfesioni().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me getProfesioni.
				} else if (Lexuesit.getAdresa().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me getAdresa.
				} else if (Lexuesit.getSektori().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me getSektori.
				} else if (String.valueOf(Lexuesit.getCmimi()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me getCmimi.
				} else if (String.valueOf(Lexuesit.getDateRegjistrimi()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me getDateRegjistrimi.
				} else if (String.valueOf(Lexuesit.getDateSkadimi()).indexOf(lowerCaseFilter) != -1)
					return true; // Filteri perputhet me getDateSkadimi.
				else
					return false; // Perndryshe ska match dhe nuk shfaq asgje.
			});
		});

		// Wrap filteredList ne SortedList
		SortedList<Lexuesit> sortedData = new SortedList<>(filteredData);

		// Lidhni comperatorin(kallxuesin) e SortedList me comperatorin e tableview
		// Perndryshe klasifikimi nuk ka efekt.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());

		// Shto te dhenat e sortuara dhe te filtruara ne tableview.
		tableview.setItems(sortedData);

	}

	@FXML
	private void kerkoLibraClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../../views/kerko/kerkoLibra.fxml"));
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
	private void kerkoLexuesClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../../views/kerko/kerkoLexues.fxml"));
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
	private void kerkoMbajtesiteLibritClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../../views/kerko/kerkoMbajtesiteLibrit.fxml"));
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
	private void kerkoVendetClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../../views/kerko/kerkoVendet.fxml"));
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
