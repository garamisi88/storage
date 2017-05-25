package storage.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.dao.impl.CustomerDaoImpl;
import storage.model.Customer;

/**
 * 
 * Vásárló lista nézetért felelős controller.
 * @author Misi
 *
 */
public class CustomerListViewController {
	
	private ObservableList<Customer> customers = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Customer> customerTable;
	
	@FXML
	private TableColumn<Customer, String> nameCol;
	
	@FXML
	private TableColumn<Customer, String> emailCol;
	
	@FXML
	private TableColumn<Customer, String> phoneCol;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label emailLabel;
	
	@FXML
	private Label phoneLabel;
	
	@FXML
	private Label countryLabel;
	
	@FXML
	private Label zipLabel;
	
	@FXML
	private Label cityLabel;
	
	@FXML
	private Label streetLabel;
	
	@FXML
	private Label ordersLabel;
	
	@FXML
	private void initialize(){
		this.resetFields();
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		customers.addAll(customerDao.getAll());
		customerTable.setItems(customers);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
		
		customerTable.getSelectionModel().selectedItemProperty().addListener((o, oldvalue, newvalue) -> showCustomerDetails(newvalue));;
	}
	
	private void resetFields(){
		nameLabel.setText("");
		emailLabel.setText("");
		phoneLabel.setText("");
		countryLabel.setText("");
		zipLabel.setText("");
		cityLabel.setText("");
		streetLabel.setText("");
		ordersLabel.setText("");
	}
	
	
	private void showCustomerDetails(Customer customer){
		nameLabel.setText( customer.getName() );
		emailLabel.setText( customer.getEmail() );
		phoneLabel.setText( customer.getPhone() );
		if(customer.getAddress() != null){
			countryLabel.setText( customer.getAddress().getCountry() );
			zipLabel.setText( customer.getAddress().getZip() );
			cityLabel.setText( customer.getAddress().getCity() );
			streetLabel.setText( customer.getAddress().getStreet() );
		}
		ordersLabel.setText( String.valueOf( customer.getOrders().size() ) );
	}
	
	@FXML
	private void addCustomer(){
		System.out.println("uj");
	}
	
	@FXML
	private void editCustomer(){
		int index = customerTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			System.out.println("modosit");
		}
	}
	
	@FXML
	private void deleteCustomer(){
		int index = customerTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			System.out.println("torles");
		}
	}
}
