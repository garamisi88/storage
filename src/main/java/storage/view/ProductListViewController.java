package storage.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import storage.App;
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
	private TableColumn<Product, Float> priceCol;
	
	@FXML
	private TableColumn<Product, Integer> quantityCol;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label skuLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private Label sellPriceLabel;
	
	@FXML
	private Label quantityLabel;
	
	@FXML
	private Label minQuantityLabel;
	
	@FXML
	private Label optQuantityLabel;
	
	@FXML
	private Label expiryLabel;
	
	private ObservableList<Product> productList = FXCollections.observableArrayList();
	
	@FXML
	private void initialize(){
		this.resetFields();
		
		ProductServiceImpl productService = new ProductServiceImpl();
		productList.addAll(productService.getAll());
		productTable.setItems(productList);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		skuCol.setCellValueFactory(new PropertyValueFactory<Product, String>("sku"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
		expiryCol.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("expiryDate"));
		
		productTable.getSelectionModel().selectedItemProperty().addListener((o, oldvalue, newvalue) -> showProductDetails(newvalue));
	}
	
	private void resetFields(){
		nameLabel.setText("");
		skuLabel.setText("");
		priceLabel.setText("");
		sellPriceLabel.setText("");
		quantityLabel.setText("");
		minQuantityLabel.setText("");
		optQuantityLabel.setText("");
		expiryLabel.setText("");
	}
	
	private void showProductDetails(Product product){
		if(product != null){
			ProductServiceImpl productService = new ProductServiceImpl();
			
			nameLabel.setText(product.getName());
			skuLabel.setText(product.getSku());
			priceLabel.setText(product.getPrice()+" Ft");
			sellPriceLabel.setText(productService.getSellPrice(product)+" Ft");
			quantityLabel.setText(product.getQuantity()+" db");
			minQuantityLabel.setText(product.getMinimumQuantity()+" db");
			optQuantityLabel.setText(product.getOptimalQuantity()+" db");
			if(product.getExpiryDate() != null)
				expiryLabel.setText(product.getExpiryDate().format(DateTimeFormatter.ISO_DATE));
			
			if(productService.needToOrder(product)){
				quantityLabel.setTextFill(Color.web("#c50b0b"));
			}else{
				quantityLabel.setTextFill(Color.BLACK);
			}
		}
	}
	
	@FXML
	private void addButtonAction(){
		App.getInstance().showProductFormView("ProductFormView", null);
	}
	
	@FXML
	private void editButtonAction(){
		int index = productTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			Product product = productTable.getItems().get(index);
			App.getInstance().showProductFormView("ProductFormView", productTable.getItems().get(index));
		}
	}
	
	@FXML
	private void deleteButtonAction(){
		int index = productTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			Product product = productTable.getItems().get(index);
			System.out.println("db törles");
			productTable.getItems().remove(index);
			
		}
	}
}
