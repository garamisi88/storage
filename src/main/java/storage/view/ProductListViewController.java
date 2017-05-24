package storage.view;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.model.Product;
import storage.service.impl.ProductServiceImpl;

/**
 * Terméklista nézetért felelős controller.
 * @author Misi
 *
 */
public class ProductListViewController {
	
	@FXML
	private TableView<Product> productTable;
	
	@FXML
	private TableColumn<Product, String> nameCol;
	
	@FXML
	private TableColumn<Product, String> skuCol;
	
	@FXML
	private TableColumn<Product, LocalDate> expiryCol;
	
	@FXML
	private TableColumn<Product, Float> price;
	
	private ObservableList<Product> productList = FXCollections.observableArrayList();
	
	@FXML
	private void initialize(){
		ProductServiceImpl productService = new ProductServiceImpl();
		productList.addAll(productService.getAll());
		productTable.setItems(productList);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		skuCol.setCellValueFactory(new PropertyValueFactory<Product, String>("sku"));
	}
}
