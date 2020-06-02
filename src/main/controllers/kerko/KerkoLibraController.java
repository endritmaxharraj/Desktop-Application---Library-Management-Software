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
import main.models.Librat;
import main.utils.DBConnector;

public class KerkoLibraController implements Initializable {

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
	TableView<Librat> tableview;

	@FXML
	TableColumn<Librat, String> EmriLibritAutori;

	@FXML
	TableColumn<Librat, Integer> vitiBotimit;

	@FXML
	TableColumn<Librat, Integer> ISBNKodi;

	@FXML
	TableColumn<Librat, Integer> Sasia;

	ObservableList<Librat> oblist = FXCollections.observableArrayList();

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
			String SQL = "Select * from RegjistrimiLibrave";
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				oblist.add(new Librat(rs.getString("Emri_Librit_Autori"), rs.getInt("Viti_Botimit"),
						rs.getLong("ISBNKodi"), rs.getInt("Sasia")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		EmriLibritAutori.setCellValueFactory(new PropertyValueFactory<>("EmriLibritAutori"));
		vitiBotimit.setCellValueFactory(new PropertyValueFactory<>("VitiBotimit"));
		ISBNKodi.setCellValueFactory(new PropertyValueFactory<>("ISBNKodi"));
		Sasia.setCellValueFactory(new PropertyValueFactory<>("Sasia"));
		
		// Krijojm nje FilterdList i cili si fillim i paraqet krejt te dhenat nga oblist
		FilteredList<Librat> filteredData = new FilteredList<>(oblist, b -> true);

		// Vedosim predicate(kallxuesin) sa here qe ndryshon filteredData
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Librat -> {
				// Nese Filteri(SearchBoxi) eshte i zbrazet atehere paraqiti krejt te dhenat nga
				// tabela

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Perndryshe krahaso Emrin e Librit dhe Autori, VitiBotimit, ISBNKodi, Sasia me
				// tekstin qe shkruhet ne Searchbox
				String lowerCaseFilter = newValue.toLowerCase();

				if (Librat.getEmriLibritAutori().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me EmriLibritAutori.
				} else if (String.valueOf(Librat.getVitiBotimit()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me VitiBotimit.
				} else if (String.valueOf(Librat.getISBNKodi()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filteri perputhet me ISBNKodi.
				} else if (String.valueOf(Librat.getSasia()).indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false; // Perndryshe ska match dhe nuk shfaq asgje.
			});
		});

		// Wrap filteredList ne SortedList
		SortedList<Librat> sortedData = new SortedList<>(filteredData);

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

}
