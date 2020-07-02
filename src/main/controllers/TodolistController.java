package main.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.utils.DBConnector;
import main.utils.Database;

public class TodolistController {
	
	double x, y;

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
	    private TextField data;

	    @FXML
	    private TextField text;

	    @FXML
	    private Button save;
	    
	    @FXML
	    private TableView<Database> table;

	    @FXML
	    private TableColumn<Database, String> tabeladata;

	    @FXML
	    private TableColumn<Database, String> tabelaeventi;
	    @FXML
	    private TableColumn<Database, String> id;
	    @FXML
	    private ObservableList<Database>db;

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
		void saveOnClick(ActionEvent event) throws Exception {
		Connection con = DBConnector.getConnection();
		String query = "INSERT INTO todolist (data, text) VALUE (?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, data.getText());
		pst.setString(2, text.getText());
		pst.executeUpdate();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Te dhenat jane regjistruar me sukses ne databaze!");
		alert.showAndWait();
	 
   }
	    @FXML
		void buttondataClicked(ActionEvent event) throws Exception {
					Connection con = DBConnector.getConnection();
	    	    	 db=FXCollections.observableArrayList();
						ResultSet rs = con.createStatement().executeQuery("SELECT * FROM todolist");
	    	    	 	while(rs.next())
	    	    	 	{
	    	    	 		db.add(new Database(rs.getString(1),rs.getString(2), rs.getString(3)));
	    	    	 	}

	    	 id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		 tabeladata.setCellValueFactory(new PropertyValueFactory<>("date"));
    		 tabelaeventi.setCellValueFactory(new PropertyValueFactory<>("text"));
    		 table.setItems(db);
	      
	    }

}

