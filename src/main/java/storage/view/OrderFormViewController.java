package storage.view;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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

/**
 * A rendelés nézetet vezérlő controller osztály.
 * @author Misi
 *
 */
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
	private Label priceLabel;
	
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
		priceLabel.setText("");
		quantityLabel.setText("");
		
		customers.addAll(customerService.getAll());
		products.addAll(productService.getAll("active"));
		
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
			priceLabel.setText(product.getPrice()+" Ft");
		}
	}
	
	@FXML
	private void addProductAction(){
		try{
			Product product = getSelectedProduct();
			if( product != null ){
				int selectedQuantity = Integer.valueOf( quantityField.getText() );
				float price = Float.valueOf( priceField.getText() );
				int quantityInCart = orderService.getProductCartQuantity(orderItems, product);
				
				if( selectedQuantity == 0)
					throw new IllegalArgumentException("Kérem adjon meg mennyiséget!");
				
				if( selectedQuantity + quantityInCart > product.getQuantity() )
					throw new IllegalArgumentException("A termékből csak "+product.getQuantity()+" db van készleten!");
				
				
				OrderItem item = new OrderItem();
				item.setProduct(product);
				item.setQuantity(selectedQuantity);
				item.setPrice(price);
				item.setOrder(order);
				
				orderItems.add(item);
				summaryLabel.setText(orderService.getOrderSum(orderItems)+" Ft");
				
				quantityField.setText("");
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
		Customer customer = getSelectedCustomer();
		if(customer != null){
			emailLabel.setText(customer.getEmail());
			addressLabel.setText(customer.getAddress().toString());
		}
	}
	
	@FXML
	private void saveOrderAction(){
		this.prepareOrder();
		
		try{
			if(order.getId() == 0)
				orderService.save(order);
			else
				orderService.update(order);
			App.getInstance().changeView("OrderListView");
		}catch(IllegalArgumentException e){
			errorLabel.setText(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@FXML
	private void cancelButtonAction(){
		App.getInstance().changeView("OrderListView");
	}
	
	@FXML
	private void closeOrderAction(){
		this.prepareOrder();
		
		try{
			for (OrderItem item : order.getOrderItems()) {
				int quantityInCart = orderService.getProductCartQuantity(order.getOrderItems(), item.getProduct());
				if(quantityInCart > item.getProduct().getQuantity())
					throw new IllegalArgumentException("A(z) "+item.getProduct().toString()+" elérhető készlet "+item.getProduct().getQuantity());
			}
			
			order.setClosed(true);
			if(order.getId() == 0)
				orderService.save(order);
			else
				orderService.update(order);
			
			for (OrderItem item : order.getOrderItems()) {
				Product product = item.getProduct();
				product.setQuantity(product.getQuantity() - item.getQuantity());
				productService.update(product);
			}
			
			App.getInstance().changeView("OrderListView");
		}catch(IllegalArgumentException e){
			errorLabel.setText(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@FXML
	private void removeTableRow(){
		int index = orderItemTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			orderItems.remove(index);
			summaryLabel.setText(orderService.getOrderSum(orderItems)+" Ft");
		}
	}
	
	
	private void setFormValues(){
		if(order != null && order.getId() != 0){
			emailLabel.setText(order.getCustomer().getEmail());
			addressLabel.setText(order.getCustomer().getAddress().toString());
			summaryLabel.setText(orderService.getOrderSum(order.getOrderItems())+" Ft");
			
			if(customers.size() > 0 && customerSelect.getItems().size() > 0){
				customerSelect.getSelectionModel().select(order.getCustomer());
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

	/**
	 * Ez a metódos állítja be a módosítandó rendelést.
	 * @param order A módosítandó rendelés
	 */
	public void setOrder(MyOrder order) {
		if(order == null)
			order = new MyOrder();
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
	
	private Customer getSelectedCustomer(){
		return customerSelect.getSelectionModel().getSelectedItem();
	}
	
	private void prepareOrder(){
		order.setCustomer( getSelectedCustomer() );
		order.setOrderItems(orderItems);
		order.setPrice(orderService.getOrderSum(orderItems));

		if(order.getId() == 0){
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			int rand = new Random().nextInt(9) + 1;
			order.setReferenceId(now.getTime() +"-"+rand);		
			order.setOrderDate(now);
		}
	}
}
