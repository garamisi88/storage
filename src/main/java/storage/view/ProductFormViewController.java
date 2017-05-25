package storage.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import storage.App;
import storage.model.Product;
import storage.service.impl.ProductServiceImpl;

/**
 * A termék form nézetért felelős controller osztály.
 * @author Misi
 *
 */
public class ProductFormViewController {
	
	private Logger logger = LoggerFactory.getLogger(ProductFormViewController.class);
	
	private Product product;
	
	@FXML
	private TextField nameInput;
	
	@FXML
	private TextField skuInput;
	
	@FXML
	private TextField priceInput;
	
	@FXML
	private TextField quantityInput;
	
	@FXML
	private TextField minQuantityInput;
	
	@FXML
	private TextField optQuantityInput;
	
	@FXML
	private TextField expiryDateInput;
	
	@FXML
	private Label errorLabel;
	
	
	@FXML
	private void initialize(){
		errorLabel.setText("");
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if(product == null)
			product = new Product();
		this.product = product;
		
		nameInput.setText(this.product.getName());
		skuInput.setText(this.product.getSku());
		priceInput.setText(String.valueOf(this.product.getPrice()));
		quantityInput.setText(String.valueOf(this.product.getQuantity()));
		minQuantityInput.setText(String.valueOf(this.product.getMinimumQuantity()));
		optQuantityInput.setText(String.valueOf(this.product.getOptimalQuantity()));
		
		if(this.product.getExpiryDate() != null)
			expiryDateInput.setText(this.product.getExpiryDate().format(DateTimeFormatter.ISO_DATE));
	}
	
	@FXML
	private void cancelAction(){
		App.getInstance().changeView("ProductListView");
	}
	
	@FXML
	private void saveAction(){
		ProductServiceImpl productService = new ProductServiceImpl();
		try{
			if(nameInput.getText() != null && !nameInput.getText().isEmpty())
				product.setName( nameInput.getText() );
			
			if(skuInput.getText() != null && !skuInput.getText().isEmpty())
				product.setSku( skuInput.getText() );
			
			if(priceInput.getText() != null && !priceInput.getText().isEmpty())
				product.setPrice( Float.valueOf( priceInput.getText() ));
			
			if(quantityInput.getText() != null && !quantityInput.getText().isEmpty())
				product.setQuantity( Integer.valueOf( quantityInput.getText() ) );
			
			if(minQuantityInput.getText() != null && !minQuantityInput.getText().isEmpty())
				product.setMinimumQuantity( Integer.valueOf( minQuantityInput.getText() ) );
			
			if(optQuantityInput.getText() != null && !optQuantityInput.getText().isEmpty())
				product.setOptimalQuantity( Integer.valueOf( optQuantityInput.getText() ) );
			
			if(expiryDateInput.getText() != null && !expiryDateInput.getText().isEmpty())
				product.setExpiryDate( LocalDate.parse( expiryDateInput.getText() ) );
			
			if(product.getId() == 0){
				productService.save(product);
			}else{
				productService.update(product);
			}
			
			App.getInstance().changeView("ProductListView");
		}catch(IllegalArgumentException e){
			errorLabel.setText(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("itt kapom el a hibát");
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
	}
	
	
}
