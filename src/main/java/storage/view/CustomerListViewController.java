package storage.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.App;
import storage.dao.impl.CustomerDaoImpl;
import storage.model.Customer;

/**
 * 
 * Vásárló lista nézetért felelős controller.
 * @author Misi
 *
 */
public class CustomerListViewController {

	/**
	 * Vásárlókat tartalmazó lista.
	 */
	private ObservableList<Customer> customers = FXCollections.observableArrayList();
	
	/**
	 * Vásárlókat tartalmazó táblázat.
	 */
	@FXML
	private TableView<Customer> customerTable;
	
	/**
	 * Név oszlop.
	 */
	@FXML
	private TableColumn<Customer, String> nameCol;
	
	/**
	 * E-mail oszlop.
	 */
	@FXML
	private TableColumn<Customer, String> emailCol;
	
	/**
	 * Telefonszám oszlop.
	 */
	@FXML
	private TableColumn<Customer, String> phoneCol;
	
	/**
	 * Név címke.
	 */
	@FXML
	private Label nameLabel;
	
	/**
	 * E-mail címke.
	 */
	@FXML
	private Label emailLabel;
	
	/**
	 * Telefonszám címke.
	 */
	@FXML
	private Label phoneLabel;
	
	/**
	 * Ország címke.
	 */
	@FXML
	private Label countryLabel;
	
	/**
	 * Irányítószám címke.
	 */
	@FXML
	private Label zipLabel;
	
	/**
	 * Város címke.
	 */
	@FXML
	private Label cityLabel;
	
	/**
	 * Utca, házszám címke.
	 */
	@FXML
	private Label streetLabel;
	
	/**
	 * Rendelések címke.
	 */
	@FXML
	private Label ordersLabel;
	
	/**
	 * A nézet betöltésekor lefutó inicializáló függvény. Feltölti a táblázatot, valamint üríti a címkéket.
	 */
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
	
	/**
	 * Ez a metódus törli ki a címkék tartalmát.
	 */
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
	
	
	/**
	 * A vásárló adatait jeleníti meg a címkékben.
	 * @param customer
	 */
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
	
	/**
	 * Új vásárló hozzáadáss gombra történi klikkek kezelő metódus.
	 */
	@FXML
	private void addCustomer(){
		System.out.println("itt vagyok");
		App.getInstance().showCustomerFormView("CustomerFormView", null);
	}
	
	/**
	 * Vásárló módosítása gombra történő klikket kezelő metódus.
	 */
	@FXML
	private void editCustomer(){
		int index = customerTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			App.getInstance().showCustomerFormView("CustomerFormView", customerTable.getItems().get(index));
		}
	}
	
	/**
	 * Vásárló törlését végző metódus.
	 */
	@FXML
	private void deleteCustomer(){
		int index = customerTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			System.out.println("torles");
		}
	}
}
