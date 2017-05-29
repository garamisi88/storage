package storage.view;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import storage.model.MyOrder;
import storage.service.impl.OrderServiceImpl;

public class ClosedOrderViewController {
	
	private static final OrderServiceImpl orderService = new OrderServiceImpl();
	
	private ObservableList<MyOrder> orders = FXCollections.observableArrayList();
	
	@FXML
	private TableView<MyOrder> orderTable;
	
	@FXML
	private TableColumn<MyOrder, String> referenceCol;
	
	@FXML
	private TableColumn<MyOrder, String> customerCol;
	
	@FXML
	private TableColumn<MyOrder, Float> priceCol;
	
	@FXML
	private TableColumn<MyOrder, LocalDate> dateCol;
	
	@FXML
	private void initialize(){
		List<MyOrder> list = orderService.getAll("closed");
		if(list != null && list.size() > 0){
			orders.addAll(list);
			System.out.println("Lezárt rendelések szám: "+list.size());
		
			orderTable.setItems(orders);
			
			referenceCol.setCellValueFactory(new PropertyValueFactory<MyOrder, String>("referenceId"));
			customerCol.setCellValueFactory(new PropertyValueFactory<MyOrder, String>("customer"));
			dateCol.setCellValueFactory(new PropertyValueFactory<MyOrder, LocalDate>("orderDate"));
			priceCol.setCellValueFactory(new PropertyValueFactory<MyOrder, Float>("price"));
		}
	}
}
