package storage.view;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.App;
import storage.model.Product;
import storage.service.impl.ProductServiceImpl;

/**
 * A selejt termékeket megjelenítő nézet controller osztálya.
 * @author Misi
 *
 */
public class WasteProductListController {
	
	private ObservableList<Product> products = FXCollections.observableArrayList();
	
	private static final ProductServiceImpl productService = new ProductServiceImpl();
	
	@FXML
	private TableView<Product> productTable;
	
	@FXML
	private TableColumn<Product, String> nameCol;
	
	@FXML
	private TableColumn<Product, String> skuCol;
	
	@FXML
	private TableColumn<Product, Integer> quantityCol;
	
	@FXML
	private TableColumn<Product, Float> priceCol;
	
	@FXML
	private TableColumn<Product, Float> entryPriceCol;
	
	@FXML
	private TableColumn<Product, LocalDate> expiryDateCol;
	
	@FXML
	private void initialize(){
		products.addAll(productService.getAll("waste"));
		if(products.size() > 0){
			productTable.setItems(products);
			
			nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
			skuCol.setCellValueFactory(new PropertyValueFactory<Product, String>("sku"));
			quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
			priceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
			entryPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("entryPrice"));
			expiryDateCol.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("expiryDate"));
			
		}
	}
	
	@FXML
	private void viewWasteProductAction(){
		if(products.size() > 0){
			Product product = productTable.getSelectionModel().getSelectedItem();
			if(product != null){
				App.getInstance().showProductFormView("WasteProductView", product);
			}
		}
	}
	
}
