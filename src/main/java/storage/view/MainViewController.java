package storage.view;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import storage.App;

/**
 * A fő felhasználó felületet kezelő controller.
 * @author Misi
 *
 */
public class MainViewController {
	
	/**
	 * Kijelentkezés menüeleme
	 */
	@FXML
	private MenuItem closeItem;
	
	@FXML
	private void closeMenuAction(){
		App.getInstance().closeHandler();
	}
}
