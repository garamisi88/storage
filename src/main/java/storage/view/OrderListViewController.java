package storage.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.model.Customer;
import storage.model.MyOrder;
import storage.model.Product;
import storage.service.impl.OrderServiceImpl;

/**
 * A rendelés listázó controller.
 * @author Misi
 *
 */
public class OrderListViewController {

	private static Logger logger = LoggerFactory.getLogger(OrderListViewController.class);
	
	private final OrderServiceImpl orderService = new OrderServiceImpl();
	
	private ObservableList<MyOrder> orders = FXCollections.observableArrayList(); 
	
	@FXML
	private TableView<MyOrder> orderTable;
	
	@FXML
	private TableColumn<MyOrder, String> referenceCol;
	
	@FXML
	private TableColumn<Customer, String> nameCol;
	
	@FXML
	private TableColumn<MyOrder, LocalDate> dateCol;
	
	@FXML
	private TableColumn<OrderServiceImpl, Float> priceCol;
	
	@FXML
	private Label referenceLabel;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private Label dateLabel;
	
	@FXML
	private void initialize(){
		this.resetFields();
		
		orders.addAll(orderService.getAll("active"));
		
		orderTable.setItems(orders);
		
		referenceCol.setCellValueFactory(new PropertyValueFactory<MyOrder, String>("referenceId"));
		orderTable.getSelectionModel().selectedItemProperty().addListener((o, oldvalue, newvalue) -> showOrderDetails(newvalue));
	}
	
	private void showOrderDetails(MyOrder order){
		referenceLabel.setText(order.getReferenceId());
		nameLabel.setText(order.getCustomer().getName());
		priceLabel.setText(orderService.getOrderSum(order)+" Ft");
		dateLabel.setText( order.getOrderDate().format(DateTimeFormatter.ISO_DATE) );
	}
	
	private void resetFields(){
		referenceLabel.setText("");
		nameLabel.setText("");
		priceLabel.setText("");
		dateLabel.setText("");
	}
	
	@FXML
	private void addButtonAction(){
		logger.info("uj gomb klikk");
	}
	
	@FXML
	private void editButtonAction(){
		logger.info("modosit gomb klikk");
	}
	
	@FXML
	private void deleteButtonAction(){
		logger.info("torol gomb klikk");
	}
}
