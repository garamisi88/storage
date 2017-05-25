package storage.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import storage.App;
import storage.dao.impl.UserDaoImpl;
import storage.model.User;

/**
 * A bejelenzkezés controllere.
 * @author Misi
 *
 */
public class LoginViewController {
	
	private UserDaoImpl dao = new UserDaoImpl();
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private void initialize(){
		errorLabel.setText("");
	}
	
	@FXML
	private void userLoginAction(){
		try{
			User user = dao.userAuth(usernameField.getText(), passwordField.getText());
			App.getInstance().setUser(user);
			App.getInstance().changeView("OrderListView");
		}catch(Exception e){
			errorLabel.setText(e.getMessage());
		}
	}
}
