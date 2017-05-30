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
		this.loadAppView("OrderListView");
	}
	
	@FXML
	private void openClosedOrders(){
		this.loadAppView("ClosedOrderList");
	}
	
	@FXML
	private void openOrderForm(){
		App.getInstance().showOrderFormView("OrderFormView", null);
	}
	
	@FXML
	private void openProductList(){
		App.getInstance().showProductListView("ProductListView", "active");
	}
	
	@FXML
	private void openOrderableProductList(){
		App.getInstance().showProductListView("ProductListView", "orderable");
	} 
	
	@FXML
	private void openWasteProductList(){
		this.loadAppView("WasteProductList");
	}
	
	@FXML
	private void openProductForm(){
		App.getInstance().showProductFormView("ProductFormView", null);
	}
	
	@FXML
	private void openCustomerList(){
		this.loadAppView("CustomerListView");
	}
	
	@FXML
	private void openCustomerForm(){
		App.getInstance().showCustomerFormView("CustomerFormView", null);
	}
	
	private void loadAppView(String viewName){
		App.getInstance().changeView(viewName);
	}
}
