package storage.view;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import storage.App;

/**
 * Ez a controller felel a főmenüért, amelyet minden view használni fog.
 * @author Misi
 *
 */
public class MainMenuViewController {

	@FXML
	private MenuItem orderList;

	@FXML
	private MenuItem productList;
	
	@FXML
	private void openOrderList(){
		App.getInstance().changeView("OrderListView");
	}
	
	@FXML
	private void openProductList(){
		App.getInstance().changeView("ProductListView");
	}
	
	@FXML
	private void openProductForm(){
		App.getInstance().changeView("ProductFormView");
	}
}
