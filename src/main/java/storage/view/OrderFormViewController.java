package storage.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import storage.model.Customer;
import storage.model.MyOrder;
import storage.model.OrderItem;
import storage.model.Product;
import storage.service.impl.CustomerServiceImpl;
import storage.service.impl.OrderServiceImpl;
import storage.service.impl.ProductServiceImpl;

public class OrderFormViewController {
	
	private MyOrder order;
	
	private ObservableList<Product> products = FXCollections.observableArrayList();
	
	private ObservableList<Customer> customers = FXCollections.observableArrayList();
	
	private static final ProductServiceImpl productService = new ProductServiceImpl();
	
	private static final CustomerServiceImpl customerService = new CustomerServiceImpl();
	
	private static final OrderServiceImpl orderService = new OrderServiceImpl();
	
	@FXML
	private ComboBox<Product> productSelect;
	
	@FXML
	private ComboBox<Customer> customerSelect;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private Label emailLabel;
	
	@FXML
	private Label addressLabel;
	
	@FXML
	private Label summaryLabel;
	
	@FXML
	private TextField quantityField;
	
	@FXML
	private TextField  priceField;
	
	@FXML
	private TableView<OrderItem> orderItemTable;
	
	@FXML
	private TableColumn<OrderItem, String> nameCol;
	
	@FXML
	private TableColumn<OrderItem, Integer> quantityCol;
	
	@FXML
	private TableColumn<OrderItem, Float> priceCol;
	
	@FXML
	private void initialize(){
		errorLabel.setText("");
		emailLabel.setText("");
		addressLabel.setText("");
		
		customers.addAll(customerService.getAll());
		products.addAll(productService.getAll());
		
		customerSelect.setItems(customers);
		productSelect.setItems(products);
		
		this.setFormValues();
	}
	
	private void setFormValues(){
		if(order != null){
			emailLabel.setText(order.getCustomer().getEmail());
			addressLabel.setText(order.getCustomer().getAddress().toString());
			summaryLabel.setText(orderService.getOrderSum(order)+" Ft");
			
			if(customers.size() > 0 && customerSelect.getItems().size() > 0){
				customerSelect.setValue(order.getCustomer());
			}
		}
	}

	public void setOrder(MyOrder order) {
		this.order = order;
		this.setFormValues();
	}
	
	
}
