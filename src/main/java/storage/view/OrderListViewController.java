package storage.view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.App;
import storage.model.MyOrder;
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
	private TableColumn<MyOrder, String> nameCol;
	
	@FXML
	private TableColumn<MyOrder, LocalDate> dateCol;
	
	@FXML
	private TableColumn<MyOrder, Float> priceCol;
	
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
		nameCol.setCellValueFactory(new PropertyValueFactory<MyOrder, String>("customer"));
		dateCol.setCellValueFactory(new PropertyValueFactory<MyOrder, LocalDate>("orderDate"));
		priceCol.setCellValueFactory(new PropertyValueFactory<MyOrder, Float>("price"));
		
		orderTable.getSelectionModel().selectedItemProperty().addListener((o, oldvalue, newvalue) -> showOrderDetails(newvalue));
		
		logger.info("nézet inicializálva");
	}
	
	private void showOrderDetails(MyOrder order){
		if(order != null){
			referenceLabel.setText(order.getReferenceId());
			nameLabel.setText(order.getCustomer().getName());
			priceLabel.setText(orderService.getOrderSum(order.getOrderItems())+" Ft");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateLabel.setText( formatter.format(order.getOrderDate()) );
		}
	}
	
	private void resetFields(){
		referenceLabel.setText("");
		nameLabel.setText("");
		priceLabel.setText("");
		dateLabel.setText("");
	}
	
	@FXML
	private void addButtonAction(){
		App.getInstance().showOrderFormView("OrderFormView", null);
	}
	
	@FXML
	private void editButtonAction(){
		int index = orderTable.getSelectionModel().getSelectedIndex();
		if(index >= 0)
			App.getInstance().showOrderFormView("OrderFormView", orderTable.getItems().get(index));
	}
	
	@FXML
	private void deleteButtonAction(){
		int index = orderTable.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			MyOrder order = orderTable.getSelectionModel().getSelectedItem();
			this.resetFields();
			orderService.remove(order);
			orderTable.getItems().remove(index);
		}
	}
}
