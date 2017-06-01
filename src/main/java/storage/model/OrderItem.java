package storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * A rendelés tételeket leíró osztály.
 * @author Misi
 *
 */
@Entity
@Table(name="order_items")
@XmlRootElement(name="orderItem")
public class OrderItem{
	/**
	 * A rendelés tétel azonosítója. 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private int id;

	/**
	 * A tételhez tartozó rendelés. 
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	private MyOrder order;

	/**
	 * A tételhez tartozó termék. 
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;

	/**
	 * A megrendelt mennyiség. 
	 */
	private int quantity;

	/**
	 * A termék eladási ára. 
	 */
	private float price;

	/**
	 * A tétel id-ját visszaadó metódus.
	 * @return a tétel id-ja
	 */
	public int getId() {
		return id;
	}

	/**
	 * A tétel id-ját beállító metódus.
	 * @param id A tétel id-ja
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * A tételhez tartozó rendelést visszaadó metódus.
	 * @return a tételhez tartozó rendelés
	 */
	public MyOrder getOrder() {
		return order;
	}

	/**
	 * A tételhez tartozó rendelést beállító metódus.
	 * @param order A tételhez tartozó rendelés
	 */
	public void setOrder(MyOrder order) {
		this.order = order;
	}


	/**
	 * A tétel termékét visszaadó metódus.
	 * @return a tétel terméke.
	 */
	@XmlElement(name="product")
	public Product getProduct() {
		return product;
	}

	/**
	 * A tétel termékét beállító metódus.
	 * @param product A tétel terméke
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * A megrendelt mennyiséget beállító metódus.
	 * @return a megrendelt mennyiség
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * A megrendelt mennyiséget beállító metódus.
	 * @param quantity a megrendelt mennyiség
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Visszaadja a tételhez tartozó eladási darab árat.
	 * @return eladási ár
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Ez a metódus állítja be, hogy egy termék mennyibe került.
	 * @param price A termék eladási egységára
	 */
	public void setPrice(float price) {
		this.price = price;
	}


}
