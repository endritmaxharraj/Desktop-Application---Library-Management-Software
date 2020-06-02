package main.controllers.delete;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import main.models.Vendet;
import main.utils.DBConnector;

public class DeleteVendinController implements Initializable {
	double x, y;

	@FXML
	AnchorPane anchor;

	@FXML
	AnchorPane anchorleft;

	@FXML
	AnchorPane anchorthin;

	@FXML
	private TableView<Vendet> tableview;
	@FXML
	private TableColumn<Vendet, Integer> nrVendi;
	@FXML
	private TableColumn<Vendet, String> EmriMbiemri;
	@FXML
	private TableColumn<Vendet, String> Sektori;
	@FXML
	private TableColumn<Vendet, String> DataKoha;

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
	Button deleteButton;

	@FXML
	TextField nrUlsesField;

	@FXML
	TextField emriMbiemriField;

	@FXML
	TextField sektoriField;

	@FXML
	TextField dateKohaField;

	@FXML
	Label labUserInfo;

	@FXML
	private void anuloButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/delete/deleteData.fxml"));
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

	private void deleteVendet(Integer nrVendi) throws Exception {
		Connection con = DBConnector.getConnection();
		String sql = "Delete from Vendet Where nrVendi = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, nrVendi);
		if (stmt.executeUpdate() <= 0) {
			labUserInfo.setText("Failed!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
		} else {
			labUserInfo.setText("Delete Succesfully!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
		}
	}

	private void check() throws Exception {
		if (nrUlsesField.getText().isEmpty() || emriMbiemriField.getText().isEmpty()
				|| dateKohaField.getText().isEmpty() || sektoriField.getText().isEmpty()) {
			labUserInfo.setText("Duhet ti plotesoni te gjitha te dhenat!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 1 && Integer.parseInt(nrUlsesField.getText()) <= 100
				&& sektoriField.getText().equals("Kati i pare"))) {
			delete();
			labUserInfo.setText("Delete Succesfully!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 101 && Integer.parseInt(nrUlsesField.getText()) <= 200
				&& sektoriField.getText().equals("Kati i dyte"))) {
			delete();
			labUserInfo.setText("Delete Succesfully!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 201 && Integer.parseInt(nrUlsesField.getText()) <= 300
				&& sektoriField.getText().equals("Kati i trete"))) {
			delete();
			labUserInfo.setText("Delete Succesfully!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 1 && Integer.parseInt(nrUlsesField.getText()) <= 100
				&& sektoriField.getText().equals("Kati i dyte"))) {
			labUserInfo.setText("Numrat nga 1 deri 100 jane per 'Kati i pare'!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 1 && Integer.parseInt(nrUlsesField.getText()) <= 100
				&& sektoriField.getText().equals("Kati i trete"))) {
			labUserInfo.setText("Numrat nga 1 deri 100 jane per 'Kati i pare'!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 101 && Integer.parseInt(nrUlsesField.getText()) <= 200
				&& sektoriField.getText().equals("Kati i pare"))) {
			labUserInfo.setText("Numrat nga 101 deri 200 jane per 'Kati i dyte'!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 101 && Integer.parseInt(nrUlsesField.getText()) <= 200
				&& sektoriField.getText().equals("Kati i trete"))) {
			labUserInfo.setText("Numrat nga 101 deri 200 jane per 'Kati i dyte'!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 201 && Integer.parseInt(nrUlsesField.getText()) <= 300
				&& sektoriField.getText().equals("Kati i pare"))) {
			labUserInfo.setText("Numrat nga 201 deri 300 jane per 'Kati i trete'!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if ((Integer.parseInt(nrUlsesField.getText()) >= 201 && Integer.parseInt(nrUlsesField.getText()) <= 300
				&& sektoriField.getText().equals("Kati i dyte"))) {
			labUserInfo.setText("Numrat nga 201 deri 300 jane per 'Kati i trete'!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		}

	}

	private void delete() {
		try {
			Vendet vendet = tableview.getSelectionModel().getSelectedItem();
			deleteVendet(vendet.getNrVendi());
			tableview.getSelectionModel().clearSelection();
			tableview.getItems().remove(vendet);
			setVendetToUI(new Vendet());
		} catch (Exception ex) {
			ex.getCause();
		}
	}

	@FXML
	private void deleteButtonClicked(ActionEvent event) throws Exception {
		check();
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
		loader.setLocation(getClass().getResource("../../views/delete/deleteData.fxml"));
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

	private void setVendetToUI(Vendet vendet) {
		nrUlsesField.setText(Integer.toString(vendet.getNrVendi()));
		emriMbiemriField.setText(vendet.getEmriMbiemri());
		sektoriField.setText(vendet.getSektori());
		dateKohaField.setText(vendet.getDataKoha().toString());
	}

	private void emptyField() {
		nrUlsesField.setText("");
		emriMbiemriField.setText("");
		sektoriField.setText("");
		dateKohaField.setText("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		nrVendi.setCellValueFactory(new PropertyValueFactory<>("nrVendi"));
		EmriMbiemri.setCellValueFactory(new PropertyValueFactory<>("EmriMbiemri"));
		Sektori.setCellValueFactory(new PropertyValueFactory<>("Sektori"));
		DataKoha.setCellValueFactory(new PropertyValueFactory<>("DataKoha"));

		tableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tableview.getSelectionModel().selectedItemProperty().addListener((ov, old, _new) -> {
			if (_new != null)
				setVendetToUI(_new);
		});
		try {
			tableview.setItems(getVendet());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ObservableList<Vendet> getVendet() throws Exception {
		ObservableList<Vendet> list = FXCollections.observableArrayList();
		Connection con = DBConnector.getConnection();
		String SQL = "Select * from Vendet";
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet res = stmt.executeQuery();

		while (res.next()) {
			int nrVendi = res.getInt("nrVendi");
			String EmriMbiemri = res.getString("EmriMbiemri");
			String Sektori = res.getString("Sektori");
			Timestamp DataKoha = res.getTimestamp("DataKoha");
			list.add(new Vendet(nrVendi, EmriMbiemri, Sektori, DataKoha));
		}

		return list;
	}

}
