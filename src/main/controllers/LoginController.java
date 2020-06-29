import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.models.User;
import main.repositories.UserRepository;
import main.utils.DBConnector;
import main.utils.SessionManager;



public class LoginController implements Initializable {

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
	private VBox vBox;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private ImageView LoginLogo;

	@FXML
	private Label lab;
	 @FXML
	    private Button english;
	 @FXML
	 private Button shqip;
	 
	  @FXML
	    void English(ActionEvent event) {
		  Stage primaryStage=new Stage();
	    	try {
  			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
  			Scene scene = new Scene(root,1000,700);
  			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
  			primaryStage.setScene(scene);
  			primaryStage.show();ok
  		} catch(Exception e) {
  			e.printStackTrace();
  		}
      }

	    
	   @FXML
	    void Shqip(ActionEvent event) {
		   Stage primaryStage=new Stage();
	    	try {
   			Parent root = FXMLLoader.load(getClass().getResource("login2.fxml"));
   			Scene scene = new Scene(root,1000,700);
   			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   			primaryStage.setScene(scene);
   			primaryStage.show();
   		} catch(Exception e) {
   			e.printStackTrace();
   		}
       }

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	private User login(String user_name, String user_pass) throws Exception {
		User user = UserRepository.find(user_name, user_pass);
		if (user == null) {
			lab.setText("Invalid credentials!");
			lab.setStyle("-fx-text-fill: #FF073A;");
		}
		return user;
	}

	public boolean checkIfUserEgzist(String user_name, String user_pass) throws Exception {
		Connection con = DBConnector.getConnection();
		String SQL = "SELECT * from users where user_name = ? and user_pass = ?";
		PreparedStatement stcon = con.prepareStatement(SQL);
		stcon.setString(1, user_name);
		stcon.setString(2, user_pass);
		ResultSet rs = stcon.executeQuery();
		if (!rs.next()) {
			return false;
		}
		else {
			return true;
		}
	}

	public static String findRole(String user_name) throws Exception {
		String admin = "admin";
		String user = "user";
		PreparedStatement stmt = DBConnector.getConnection()
				.prepareStatement("SELECT * from Users where user_name = ? Limit 1");
		stmt.setString(1, user_name);
		ResultSet res = stmt.executeQuery();
		String role = res.getString("user_type");
		if (role.equals("admin")) {
			return admin;
		} else {
			return user;
		}
	}


	@FXML
	private void loginButtonClicked(ActionEvent event) throws Exception {
		if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty())
		{
				lab.setText("Ju lutem plotesoni username dhe password!");
				lab.setStyle("-fx-text-fill: #FF073A;");
		}
		else if (usernameField.getText().isEmpty())
		{
			lab.setText("Ju lutem plotesoni username!");
			lab.setStyle("-fx-text-fill: #FF073A;");
		}
		else if (passwordField.getText().isEmpty())
		{
			lab.setText("Ju lutem plotesoni password!");
			lab.setStyle("-fx-text-fill: #FF073A;");
		}
		else {

			String username = usernameField.getText();
			String password = passwordField.getText();
			User user = null;
			if (checkIfUserEgzist(username, password)) {
				user = login(username, password);

				SessionManager.user = user;
				SessionManager.lastLogin = new Date();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../views/main-screen.fxml"));
				loader.load();
				Parent parent = loader.getRoot();
				Scene scene = new Scene(parent);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(scene);
				primaryStage.centerOnScreen();
				primaryStage.show();
				scene.setOnKeyPressed(e -> {
					if (e.getCode() == KeyCode.ESCAPE) {
						SessionManager.user = null;
						primaryStage.close();
					}
				});

			} else {
				lab.setText("Invalid Credentials!");
				lab.setStyle("-fx-text-fill: #FF073A;");
			}
		}
	}
	



	@FXML
	private void enterKeyPressLogin(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {

			if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
				lab.setText("Ju lutem plotesoni username dhe password!");
				lab.setStyle("-fx-text-fill: #FF073A;");
			} else if (usernameField.getText().isEmpty()) {
				lab.setText("Ju lutem plotesoni username!");
				lab.setStyle("-fx-text-fill: #FF073A;");
			} else if (passwordField.getText().isEmpty()) {
				lab.setText("Ju lutem plotesoni password!");
				lab.setStyle("-fx-text-fill: #FF073A;");
			} else {

				String username = usernameField.getText();
				String password = passwordField.getText();
				User user = null;
				if (checkIfUserEgzist(username, password)) {
					user = login(username, password);

					SessionManager.user = user;
					SessionManager.lastLogin = new Date();

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("../views/main-screen.fxml"));
					loader.load();
					Parent parent = loader.getRoot();
					Scene scene = new Scene(parent);
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage.setScene(scene);
					primaryStage.centerOnScreen();
					primaryStage.show();
					scene.setOnKeyPressed(e -> {
						if (e.getCode() == KeyCode.ESCAPE) {
							SessionManager.user = null;
							primaryStage.close();
						}
					});

				} else {
					lab.setText("Invalid Credentials!");
					lab.setStyle("-fx-text-fill: #FF073A;");
				}
			}

		}

	}

	@FXML
	private void enterKeyPassLogin(KeyEvent event) throws Exception {

		if (event.getCode() == KeyCode.ENTER) {

			if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
				lab.setText("Ju lutem plotesoni username dhe password!");
				lab.setStyle("-fx-text-fill: #FF073A;");
			} else if (usernameField.getText().isEmpty()) {
				lab.setText("Ju lutem plotesoni username!");
				lab.setStyle("-fx-text-fill: #FF073A;");
			} else if (passwordField.getText().isEmpty()) {
				lab.setText("Ju lutem plotesoni password!");
				lab.setStyle("-fx-text-fill: #FF073A;");
			} else {

				String username = usernameField.getText();
				String password = passwordField.getText();
				User user = null;
				if (checkIfUserEgzist(username, password)) {
					user = login(username, password);

					SessionManager.user = user;
					SessionManager.lastLogin = new Date();

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("../views/main-screen.fxml"));
					loader.load();
					Parent parent = loader.getRoot();
					Scene scene = new Scene(parent);
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage.setScene(scene);
					primaryStage.centerOnScreen();
					primaryStage.show();
					scene.setOnKeyPressed(e -> {
						if (e.getCode() == KeyCode.ESCAPE) {
							SessionManager.user = null;
							primaryStage.close();
						}
					});

				} else {
					lab.setText("Invalid Credentials!");
					lab.setStyle("-fx-text-fill: #FF073A;");
				}
			}

		}

	}

}
