package storage.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
	
	private static final ProductServiceImpl productService = new ProductServiceImpl();
	
	private String listType = null;
	
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
	private Label entryPriceLabel;
	
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
	}
	
	private void resetFields(){
		nameLabel.setText("");
		skuLabel.setText("");
		entryPriceLabel.setText("");
		priceLabel.setText("");
		sellPriceLabel.setText("");
		quantityLabel.setText("");
		minQuantityLabel.setText("");
		optQuantityLabel.setText("");
		expiryLabel.setText("");
	}
	
	private void showProductDetails(Product product){
		if(product != null){
			
			
			nameLabel.setText(product.getName());
			skuLabel.setText(product.getSku());
			entryPriceLabel.setText(product.getEntryPrice()+" Ft");
			priceLabel.setText(product.getPrice()+" Ft");
			sellPriceLabel.setText(productService.getSellPrice(product)+" Ft");
			quantityLabel.setText(product.getQuantity()+" db");
			minQuantityLabel.setText(product.getMinimumQuantity()+" db");
			optQuantityLabel.setText(product.getOptimalQuantity()+" db");
			if(product.getExpiryDate() != null)
				expiryLabel.setText(product.getExpiryDate().format(DateTimeFormatter.ISO_DATE));
			else
				expiryLabel.setText("");
			
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
			App.getInstance().showProductFormView("ProductFormView", product);
		}
	}
	
	@FXML
	private void deleteButtonAction(){
		int index = productTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			this.resetFields();
			Product product = productTable.getSelectionModel().getSelectedItem();
			productService.remove(product);
			productTable.getItems().remove(index);
			
			
		}
	}

	/**
	 * A lista típusát beállító változó.
	 * @param listType a lista típusa
	 */
	public void setListType(String listType) {
		this.listType = listType;
		this.loadTable();
	}
	
	private void loadTable(){
		List<Product> products = productService.getAll( this.listType );
		if(products != null && products.size() > 0){
			productList.addAll(products);
			productTable.setItems(productList);
			
			nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
			skuCol.setCellValueFactory(new PropertyValueFactory<Product, String>("sku"));
			quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
			priceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
			expiryCol.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("expiryDate"));
			
			productTable.getSelectionModel().selectedItemProperty().addListener((o, oldvalue, newvalue) -> showProductDetails(newvalue));
		}
	}
	
	
}
