package main.models;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.utils.Database;

public class ToDoList implements Initializable {
	
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
	    void save(ActionEvent event)  throws  ClassNotFoundException, SQLException {
	try {
		
		String query="INSERT INTO `todolist`(`date`, `text`) VALUES (?,?)";
   	Class.forName("com.mysql.jdbc.Driver");
   	java.sql.Connection conn = null;
   	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist","root", "");
   	PreparedStatement pst = conn.prepareStatement(query);
   	pst.setString(1,data.getText());
   	pst.setString(2,text.getText());
   	pst.executeUpdate();
   	Alert alert = new Alert(AlertType.INFORMATION);
   	alert.setHeaderText(null);
   	alert.setContentText("Te dhenat jane regjistruar me sukses ne databaze!");
   	alert.showAndWait();
   	
	
	 
   
   } catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}
	
	
	 
	
   }
	    @FXML
	    void buttondata(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	
	    	 try {
	    		 	Class.forName("com.mysql.jdbc.Driver");
	    	       	java.sql.Connection conn = null;
	    	       	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist","root", "");
	    	    	 db=FXCollections.observableArrayList();
	    	    	 	ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM todolist");
	    	    	 	while(rs.next())
	    	    	 	{
	    	    	 		db.add(new Database(rs.getString(1),rs.getString(2), rs.getString(3)));
	    	    	 	}
	    	    		
	    
	    	 }
	    	 catch(Exception e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	    	 
	    	 id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		 tabeladata.setCellValueFactory(new PropertyValueFactory<>("date"));
    		 tabelaeventi.setCellValueFactory(new PropertyValueFactory<>("text"));
    		 table.setItems(db);
	      
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub

		}

}
