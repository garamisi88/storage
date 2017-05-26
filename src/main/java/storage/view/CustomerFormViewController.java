package storage.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import storage.App;
import storage.model.Customer;

/**
 * Vásárló űrlap nézetért felelős controller.
 * @author Misi
 *
 */
public class CustomerFormViewController {

	private Logger logger = LoggerFactory.getLogger(CustomerFormViewController.class);
	
	private Customer customer;
	
	@FXML
	private TextField nameField;
	
	@FXML
	private TextField emailField;
	
	@FXML
	private TextField phoneField;
	
	@FXML
	private TextField countryField;
	
	@FXML
	private TextField zipField;
	
	@FXML
	private TextField cityField;
	
	@FXML
	private TextField streetField;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private void initialize(){
		errorLabel.setText("");
	}
	
	@FXML
	private void saveCustomerAction(){
		try{
			
		}catch(IllegalArgumentException e){
			errorLabel.setText(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@FXML
	private void cancelButtonAction(){
		App.getInstance().changeView("CustomerListView");
	}

	
	/**
	 * A vásárlót beállító metódus.
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		/*if(customer == null)
			customer = new Customer();
		
		this.customer = customer;
		
		nameField.setText(this.customer.getName());
		emailField.setText(this.customer.getEmail());
		phoneField.setText(this.customer.getPhone());
		countryField.setText(this.customer.getAddress().getCountry());
		zipField.setText(this.customer.getAddress().getZip());
		cityField.setText(this.customer.getAddress().getCity());
		streetField.setText(this.customer.getAddress().getStreet());*/
	}
	
	
}
