package storage.view;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import storage.App;
import storage.model.Product;

/**
 * Selejt termékek nézetért felelős controller.
 * @author Misi
 *
 */
public class WasteProductViewController {

	private Product product;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label skuLabel;
	
	@FXML
	private Label dateLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private Label entryPriceLabel;
	
	@FXML
	private Label quantityLabel;
	
	@FXML
	private Label lostLabel;
	
	@FXML
	private void backButtonAction(){
		App.getInstance().changeView("WasteProductList");
	}

	/**
	 * A nézeten belül megjelenítendő termék.
	 * @param product A megjelenítendő termék
	 */
	public void setProduct(Product product) {
		this.product = product;
		this.setLabels();
	}
	
	
	private void setLabels(){
		if(product != null){
			nameLabel.setText(product.getName());
			skuLabel.setText(product.getSku());
			dateLabel.setText(product.getExpiryDate().format(DateTimeFormatter.ISO_DATE));
			priceLabel.setText(product.getPrice()+" Ft");
			entryPriceLabel.setText(product.getEntryPrice()+" Ft");
			quantityLabel.setText(String.valueOf(product.getQuantity()));
			lostLabel.setText((product.getQuantity() * product.getEntryPrice())+" Ft");
		}
	}
	
	
}
