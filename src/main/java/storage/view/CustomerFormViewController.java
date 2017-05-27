package storage.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import storage.App;
import storage.model.Address;
import storage.model.Customer;
import storage.service.impl.CustomerServiceImpl;

/**
 * Vásárló űrlap nézetért felelős controller.
 * @author Misi
 *
 */
public class CustomerFormViewController {

	/**
	 * Az osztályon belül történő események naplózó logger osztály.
	 */
	private Logger logger = LoggerFactory.getLogger(CustomerFormViewController.class);
	
	private CustomerServiceImpl customerService = new CustomerServiceImpl();
	
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
			if(customer == null){
				customer = new Customer();
			}
			if(customer.getAddress() == null)
				customer.setAddress(new Address());
			
			if(nameField.getText() != null && !nameField.getText().isEmpty())
				customer.setName(nameField.getText());
			
			if(emailField.getText() != null && !emailField.getText().isEmpty())
				customer.setEmail(emailField.getText());
			
			if(phoneField.getText() != null && !phoneField.getText().isEmpty())
				customer.setPhone(phoneField.getText());
			
			if(countryField.getText() != null && !countryField.getText().isEmpty())
				customer.getAddress().setCountry(countryField.getText());
			
			if(cityField.getText() != null && !cityField.getText().isEmpty())
				customer.getAddress().setCity(cityField.getText());
			
			if(zipField.getText() != null && !zipField.getText().isEmpty())
				customer.getAddress().setZip(zipField.getText());
			
			if(streetField.getText() != null && !streetField.getText().isEmpty())
				customer.getAddress().setStreet(streetField.getText());
			
			if(customer.getId() == 0){
				customerService.save(customer);
			}else{
				customerService.update(customer);
			}
			
			App.getInstance().changeView("CustomerListView");
			
		}catch(IllegalArgumentException e){
			errorLabel.setText(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	@FXML
	private void cancelButtonAction(){
		App.getInstance().changeView("CustomerListView");
	}

	
	/**
	 * A vásárlót beállító metódus.
	 * @param customer A módosítantó {@link storage.model.Customer} objektum
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
		
		if(this.customer != null){
			Address address = this.customer.getAddress();
			
			nameField.setText(this.customer.getName());
			emailField.setText(this.customer.getEmail());
			phoneField.setText(this.customer.getPhone());
			countryField.setText(address.getCountry());
			zipField.setText(address.getZip());
			cityField.setText(address.getCity());
			streetField.setText(address.getStreet());
		}
	}
	
	
}
