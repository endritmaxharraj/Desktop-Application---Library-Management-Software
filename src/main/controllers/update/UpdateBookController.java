package main.controllers.update;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import main.models.Librat;
import main.utils.DBConnector;

public class UpdateBookController implements Initializable {

	double x, y;

	@FXML
	AnchorPane anchor;

	@FXML
	AnchorPane anchorleft;

	@FXML
	AnchorPane anchorthin;

	@FXML
	private TableView<Librat> tableview;
	@FXML
	private TableColumn<Librat, String> Emri_Librit_Autori;
	@FXML
	private TableColumn<Librat, Integer> Viti_Botimit;
	@FXML
	private TableColumn<Librat, Long> ISBNKodi;
	@FXML
	private TableColumn<Librat, Integer> Sasia;

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
	TextField emriLibritAutoriField;

	@FXML
	TextField vitiBotimitField;

	@FXML
	TextField ISBNKodiField;

	@FXML
	TextField sasiaField;

	@FXML
	Label labUserInfo;

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

	private Librat getLibratFromUI() {
		String EmriLibritAutori = emriLibritAutoriField.getText();
		Integer VitiBotimit = Integer.parseInt(vitiBotimitField.getText());
		Long ISBNKodi = Long.parseLong(ISBNKodiField.getText());
		Integer Sasia = Integer.parseInt(sasiaField.getText());

		return new Librat(EmriLibritAutori, VitiBotimit, ISBNKodi, Sasia);
	}

	private void check() throws Exception {
		if (emriLibritAutoriField.getText().isEmpty() || vitiBotimitField.getText()
				.isEmpty()
				|| ISBNKodiField.getText().isEmpty() || sasiaField.getText().isEmpty()) {
			labUserInfo.setText("Duhet ti plotesoni te gjitha te dhenat!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriLibritAutoriField.getText().length() < 2 || vitiBotimitField.getText().length() < 2) {
			labUserInfo.setText("Ju lutem plotesoni te dhenat me te dhena te verteta!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (ISBNKodiField.getText().length() < 13) {
			labUserInfo.setText("ISBNKodi eshte nje numer 13Shifror!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriLibritAutoriField.getText().matches("[0-9]+")) {
			labUserInfo.setText("Nuk lejohen numra, tek Emri dhe Autori i Librit");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (ISBNKodiField.getText().matches("^[a-zA-Z\\s]+$")) {
			labUserInfo.setText("Nuk lejohen shkronja, tek ISBNKodi!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (sasiaField.getText().matches("^[a-zA-Z\\s]+$")) {
			labUserInfo.setText("Nuk lejohen shkronja, tek Sasia!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (vitiBotimitField.getText().matches("^[a-zA-Z\\s]+$")) {
			labUserInfo.setText("Nuk lejohen shkronja, tek Viti i Botimit!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (vitiBotimitField.getText().matches("^[a-zA-Z\\s]+$")
				&& sasiaField.getText().matches("^[a-zA-Z\\s]+$")
				&& ISBNKodiField.getText().matches("^[a-zA-Z\\s]+$")) {
			labUserInfo.setText("Nuk lejohen shkronja, tek Sasia, ISBNKodi dhe Viti i Botimit!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (sasiaField.getText().matches("^[a-zA-Z\\s]+$")
				&& ISBNKodiField.getText().matches("^[a-zA-Z\\s]+$")) {
			labUserInfo.setText("Nuk lejohen shkronja, tek Sasia, ISBNKodi!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (vitiBotimitField.getText()
				.matches("^[a-zA-Z\\s]+$")
				&& sasiaField.getText().matches("^[a-zA-Z\\s]+$")) {
			labUserInfo.setText("Nuk lejohen shkronja, tek Sasia, Viti i Botimit!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		}
		else {
			update();
			labUserInfo.setText("Update Succesfully!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
			emptyField();

		}

	}

	private void emptyField() {
		emriLibritAutoriField.setText("");
		vitiBotimitField.setText("");
		ISBNKodiField.setText("");
		sasiaField.setText("");
	}

	private void updateLibrat(String EmriLibritAutori, int VitiBotimit, long ISBNKodi, int Sasia) throws Exception {
		Connection con = DBConnector.getConnection();
		String sql = "UPDATE RegjistrimiLibrave SET Emri_Librit_Autori = ?, Viti_Botimit = ?, ISBNKodi = ?, Sasia = ? WHERE Emri_Librit_Autori = ?";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, EmriLibritAutori);
		stmt.setInt(2, VitiBotimit);
		stmt.setLong(3, ISBNKodi);
		stmt.setInt(4, Sasia);
		stmt.setString(5, EmriLibritAutori);

		if (stmt.executeUpdate() <= 0) {
			labUserInfo.setText("Failed!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else {
			labUserInfo.setText("Update Succesfully!");
			labUserInfo.setStyle("-fx-text-fill: #2DFE54;");
		}
	}

	private void update() throws Exception {
		try {
			Librat selected = tableview.getSelectionModel().getSelectedItem();
			Librat changed = getLibratFromUI();

			updateLibrat(changed.getEmriLibritAutori(), changed.getVitiBotimit(), changed.getISBNKodi(),
					changed.getSasia());
			selected.setEmriLibritAutori(changed.getEmriLibritAutori());
			selected.setVitiBotimit(changed.getVitiBotimit());
			selected.setISBNKodi(changed.getISBNKodi());
			selected.setSasia(changed.getSasia());
			tableview.getSelectionModel().clearSelection();
			tableview.refresh();
			setLibratToUI(new Librat());
		} catch (Exception ex) {
			ex.getCause();
		}
	}

	@FXML
	private void updateButtonClicked(ActionEvent event) throws Exception {
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

	private void setLibratToUI(Librat librat) {
		emriLibritAutoriField.setText(librat.getEmriLibritAutori());
		vitiBotimitField.setText(Integer.toString(librat.getVitiBotimit()));
		ISBNKodiField.setText(Long.toString(librat.getISBNKodi()));
		sasiaField.setText(Integer.toString(librat.getSasia()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Emri_Librit_Autori.setCellValueFactory(new PropertyValueFactory<>("EmriLibritAutori"));
		Viti_Botimit.setCellValueFactory(new PropertyValueFactory<>("VitiBotimit"));
		ISBNKodi.setCellValueFactory(new PropertyValueFactory<>("ISBNKodi"));
		Sasia.setCellValueFactory(new PropertyValueFactory<>("Sasia"));

		tableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tableview.getSelectionModel().selectedItemProperty().addListener((ov, old, _new) -> {
			if (_new != null)
				setLibratToUI(_new);
		});
		try {
			tableview.setItems(getLibrat());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ObservableList<Librat> getLibrat() throws Exception {
		ObservableList<Librat> list = FXCollections.observableArrayList();
		Connection con = DBConnector.getConnection();
		String SQL = "Select * from RegjistrimiLibrave";
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet res = stmt.executeQuery();

		while (res.next()) {
			String EmriLibritAutori = res.getString("Emri_Librit_Autori");
			Integer VitiBotimit = res.getInt("Viti_Botimit");
			Long ISBNKodi = res.getLong("ISBNKodi");
			Integer Sasia = res.getInt("Sasia");
			list.add(new Librat(EmriLibritAutori, VitiBotimit, ISBNKodi, Sasia));
		}

		return list;
	}

}
