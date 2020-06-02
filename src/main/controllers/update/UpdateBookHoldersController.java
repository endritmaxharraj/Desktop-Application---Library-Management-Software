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
import main.models.BookHolders;
import main.utils.DBConnector;

public class UpdateBookHoldersController implements Initializable {

	double x, y;

	@FXML
	AnchorPane anchor;

	@FXML
	AnchorPane anchorleft;

	@FXML
	AnchorPane anchorthin;

	@FXML
	private TableView<BookHolders> tableview;
	@FXML
	private TableColumn<BookHolders, String> EmriMbiemri;
	@FXML
	private TableColumn<BookHolders, String> Emri_Librit_Autori;
	@FXML
	private TableColumn<BookHolders, Date> Data_Marrjes;
	@FXML
	private TableColumn<BookHolders, Date> Data_Kthimit;
	@FXML
	private TableColumn<BookHolders, String> user_name;

	@FXML
	TextField emriMbiemriField;
	@FXML
	TextField emriAutoriLibritField;
	@FXML
	TextField dataMarrjesField;
	@FXML
	TextField dataKthimitField;
	@FXML
	TextField emriPuntoritField;

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

	private BookHolders getBookHoldersFromUI() {
		String EmriMbiemri = emriMbiemriField.getText();
		String EmriLibritAutori = emriAutoriLibritField.getText();
		Date DataMarrjesNew = Date.valueOf(dataMarrjesField.getText());
		Date DataKthimitNew = Date.valueOf(dataKthimitField.getText());
		String EmriPuntorit = emriPuntoritField.getText();

		return new BookHolders(EmriMbiemri, EmriLibritAutori, DataMarrjesNew, DataKthimitNew, EmriPuntorit);
	}

	private void updateBookHolders(String EmriMbiemri, String EmriLibritAutori, Date DataMarrjesNew,
			Date DataKthimitNew,
			String EmriPuntorit) throws Exception {
		Connection con = DBConnector.getConnection();
		String sql = "UPDATE MbajtesitLibrave SET EmriMbiemri = ?, Emri_Librit_Autori = ?, Data_Marrjes = ?, Data_Kthimit =?, user_name = ? WHERE EmriMbiemri = ?";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, EmriMbiemri);
		stmt.setString(2, EmriLibritAutori);
		stmt.setDate(3, DataMarrjesNew);
		stmt.setDate(4, DataKthimitNew);
		stmt.setString(5, EmriPuntorit);
		stmt.setString(6, EmriMbiemri);

		stmt.executeUpdate();
	}

	@FXML
	private void updateButtonClicked(ActionEvent event) throws Exception {
		checkAll();
	}

	private void checkAll() throws Exception {
		Connection con = DBConnector.getConnection();
		String checkLibrinSql = "Select * from RegjistrimiLibrave where Emri_Librit_Autori = ?";
		PreparedStatement checkLibrinStmt = con.prepareStatement(checkLibrinSql);
		checkLibrinStmt.setString(1, emriAutoriLibritField.getText());
		ResultSet rs = checkLibrinStmt.executeQuery();
		if (rs.next()) {
			check();
		} else {
			labUserInfo.setText("Ky Liber me kete Emer dhe kete Autore nuk egzistone ne databasen tone!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		}

	}

	private void check() throws Exception {
		if (emriMbiemriField.getText().isEmpty() || emriAutoriLibritField.getText().isEmpty()
				|| dataMarrjesField.getText().isEmpty() || dataKthimitField.getText().isEmpty()
				|| emriPuntoritField.getText().isEmpty()) {
			labUserInfo.setText("Duhet ti plotesoni te gjitha te dhenat!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (emriAutoriLibritField.getText().length() < 3) {
			labUserInfo.setText("Ju lutem plotesoni te dhenat me te dhena te verteta!");
			labUserInfo.setStyle("-fx-text-fill: #FF073A;");
		} else if (dataKthimitField.getText().matches("^[a-zA-Z\\\\s]+$")
				|| dataMarrjesField.getText().matches("^[a-zA-Z\\\\s]+$")) {
			labUserInfo.setText(
					"Nuk lejohen shkronja te Data e Marrjes dhe Data e Kthimit, lejohet vetem ky fromat dd-mm-yyyy");
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
		emriMbiemriField.setText("");
		emriAutoriLibritField.setText("");
		dataMarrjesField.setText("");
		dataKthimitField.setText("");
		emriPuntoritField.setText("");

	}

	private void update() {
		try {
			BookHolders selected = tableview.getSelectionModel().getSelectedItem();
			BookHolders changed = getBookHoldersFromUI();

			updateBookHolders(changed.getEmriMbiemri(), changed.getEmriLibritAutori(), changed
					.getDataMarrjesNew(),
					changed.getDataKthimitNew(), changed.getEmriPuntorit());
			selected.setEmriMbiemri(changed.getEmriMbiemri());
			selected.setEmriLibritAutori(changed.getEmriLibritAutori());
			selected.setDataMarrjesNew(changed.getDataMarrjesNew());
			selected.setDataKthimitNew(changed.getDataKthimitNew());
			selected.setEmriPuntorit(changed.getEmriPuntorit());
			tableview.getSelectionModel().clearSelection();
			tableview.refresh();
			setBookHoldersToUI(new BookHolders());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
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

	private void setBookHoldersToUI(BookHolders bookHolders) {
		emriMbiemriField.setText(bookHolders.getEmriMbiemri());
		emriAutoriLibritField.setText(bookHolders.getEmriLibritAutori());
		dataMarrjesField.setText(bookHolders.getDataMarrjesNew().toString());
		dataKthimitField.setText(bookHolders.getDataKthimitNew().toString());
		emriPuntoritField.setText(bookHolders.getEmriPuntorit());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		EmriMbiemri.setCellValueFactory(new PropertyValueFactory<>("EmriMbiemri"));
		Emri_Librit_Autori.setCellValueFactory(new PropertyValueFactory<>("EmriLibritAutori"));
		Data_Marrjes.setCellValueFactory(new PropertyValueFactory<>("DataMarrjesNew"));
		Data_Kthimit.setCellValueFactory(new PropertyValueFactory<>("DataKthimitNew"));
		user_name.setCellValueFactory(new PropertyValueFactory<>("EmriPuntorit"));

		tableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tableview.getSelectionModel().selectedItemProperty().addListener((ov, old, _new) -> {
			if (_new != null)
				setBookHoldersToUI(_new);
		});
		try {
			tableview.setItems(getBookHolders());
		} catch (Exception e) {
			e.getCause();
		}

	}

	private ObservableList<BookHolders> getBookHolders() throws Exception {
		ObservableList<BookHolders> list = FXCollections.observableArrayList();
		Connection con = DBConnector.getConnection();
		String SQL = "Select * from MbajtesitLibrave";
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet res = stmt.executeQuery();

		while (res.next()) {
			String EmriMbiemri = res.getString("EmriMbiemri");
			String EmriLibritAutori = res.getString("Emri_Librit_Autori");
			Date DataMarrjesNew = res.getDate("Data_Marrjes");
			Date DataKthimitNew = res.getDate("Data_Kthimit");
			String EmriPuntorit = res.getString("user_name");

			list.add(new BookHolders(EmriMbiemri, EmriLibritAutori, DataMarrjesNew, DataKthimitNew, EmriPuntorit));
		}

		return list;
	}

}
