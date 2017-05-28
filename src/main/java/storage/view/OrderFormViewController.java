package storage.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.App;
import storage.model.Customer;
import storage.model.MyOrder;
import storage.model.OrderItem;
import storage.model.Product;
import storage.service.impl.CustomerServiceImpl;
import storage.service.impl.OrderServiceImpl;
import storage.service.impl.ProductServiceImpl;

public class OrderFormViewController {
	
	private MyOrder order;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderFormViewController.class);
	
	private ObservableList<Product> products = FXCollections.observableArrayList();
	
	private ObservableList<Customer> customers = FXCollections.observableArrayList();
	
	private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
	
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
	private Label quantityLabel;
	
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
		summaryLabel.setText("");
		quantityLabel.setText("");
		
		customers.addAll(customerService.getAll());
		products.addAll(productService.getAll());
		
		customerSelect.setItems(customers);
		productSelect.setItems(products);
		
		this.setFormValues();
		this.setOrderTable();
	}
	
	@FXML
	private void onProductChange(){
		Product product = getSelectedProduct();
		if( product != null ){
			priceField.setText(String.valueOf(productService.getSellPrice(product)));
			quantityLabel.setText(" ("+product.getQuantity()+" db)");
		}
	}
	
	@FXML
	private void addProductAction(){
		try{
			Product product = getSelectedProduct();
			if( product != null ){
				int selectedQuantity = Integer.valueOf( quantityField.getText() );
				float price = Float.valueOf( priceField.getText() );
				
				if( selectedQuantity > product.getQuantity() )
					throw new IllegalArgumentException("A termékből csak "+product.getQuantity()+" db van készleten!");
				
				OrderItem item = new OrderItem();
				item.setProduct(product);
				item.setQuantity(selectedQuantity);
				item.setPrice(price);
				
				orderItems.add(item);
				summaryLabel.setText(orderService.getOrderSum(orderItems)+" Ft");
			}
		}
		catch(IllegalArgumentException e){
			errorLabel.setText(e.getMessage());
		}
		catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@FXML
	private void changeCustomerAction(){
		System.out.println("vevő választás");
	}
	
	@FXML
	private void saveOrderAction(){
		System.out.println("mentés");
	}
	
	@FXML
	private void cancelButtonAction(){
		App.getInstance().changeView("OrderListView");
	}
	
	@FXML
	private void closeOrderAction(){
		System.out.println("Lezárás");
	}
	
	private void setFormValues(){
		if(order != null){
			emailLabel.setText(order.getCustomer().getEmail());
			addressLabel.setText(order.getCustomer().getAddress().toString());
			summaryLabel.setText(orderService.getOrderSum(order.getOrderItems())+" Ft");
			
			if(customers.size() > 0 && customerSelect.getItems().size() > 0){
				customerSelect.setValue(order.getCustomer());
			}
			
			orderItems.addAll(order.getOrderItems());
			
		}
	}
	
	private void setOrderTable(){
		orderItemTable.setItems(orderItems);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("product"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Float>("price"));
		
	}

	public void setOrder(MyOrder order) {
		this.order = order;
		this.setFormValues();
	}
	
	private Product getSelectedProduct(){
		int index = productSelect.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			return productSelect.getItems().get(index);
		}
		
		return null;
	}
	
	
}
