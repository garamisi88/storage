package storage.view;

import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.App;
import storage.model.MyOrder;
import storage.model.OrderItem;

/**
 * A lezárt rendelés adatait megjelenítő nézetért felelős controller osztály.
 * @author Misi
 *
 */
public class ClosedOrderViewController {
	
	private MyOrder order;

	private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
	
	@FXML
	private Label summaryLabel;
	
	@FXML
	private Label referenceLabel;
	
	@FXML
	private Label dateLabel;
	
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
	private TableView<OrderItem> orderItemTable;
	
	@FXML
	private TableColumn<OrderItem, String> productCol;
	
	@FXML
	private TableColumn<OrderItem, Integer> quantityCol;
	
	@FXML
	private TableColumn<OrderItem, Float> priceCol;
	
	
	private void setOrderDatas(){
		referenceLabel.setText(order.getReferenceId());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateLabel.setText( formatter.format(order.getOrderDate()) );
		
		summaryLabel.setText(order.getPrice()+" Ft");
		
		nameLabel.setText(order.getCustomer().getName());
		emailLabel.setText(order.getCustomer().getEmail());
		phoneLabel.setText(order.getCustomer().getPhone());
		
		countryLabel.setText(order.getCustomer().getAddress().getCountry());
		zipLabel.setText(order.getCustomer().getAddress().getZip());
		cityLabel.setText(order.getCustomer().getAddress().getCity());
		streetLabel.setText(order.getCustomer().getAddress().getStreet());
		
		
		orderItems.addAll(order.getOrderItems());
		orderItemTable.setItems(orderItems);
		
		productCol.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("product"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Float>("price"));
	}
	
	/**
	 * Ez a metódus állítja be a megjelenítendő rendelést.
	 * @param order a megtekinteni kívánt rendelés
	 */
	public void setOrder(MyOrder order) {
		this.order = order;
		this.setOrderDatas();
	}
	
	@FXML
	private void backButtonAction(){
		App.getInstance().changeView("ClosedOrderList");
	}
}
