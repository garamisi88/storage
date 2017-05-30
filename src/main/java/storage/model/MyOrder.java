package storage.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;

/**
 * A rendeléseket leíró osztály.
 * @author Misi
 *
 */
@Entity
@XmlRootElement(name="order")
public class MyOrder {
	
	/**
	 * A rendelés id-ja.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int id;
	
	/**
	 * A rendelés tételeit tartalmazó kollekció.
	 */
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<OrderItem> orderItems;
	
	/**
	 * A rendelés ideje.
	 */
	@Column(name="order_date")
	private Date orderDate;
	
	/**
	 * A rendelés-t leadó felhasználó. A {@link storage.model.Customer} osztály egy példánya.
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	/**
	 * Rendelés referencia száma.
	 */
	private String referenceId;
	
	/**
	 * Teljesített-e a rendelés.
	 */
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean closed;

	/**
	 * A rendelés végösszege.
	 */
	private float price;
	
	/**
	 * Rendelés id-ját visszaadó metódus.
	 * @return A rendelés id-ja
	 */
	@XmlAttribute
	public int getId() {
		return id;
	}

	/**
	 * A rendelés id-ját beállító metódus.
	 * @param id A rendelés id-ja
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * A rendelés referencia számát visszaadó metódus.
	 * @return a rendelés referencia száma
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * A rendelés referencia számát beállító metódus.
	 * @param referenceId A rendelés referencia száma
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * A rendelés tételeit visszaadó metódus.
	 * @return A rendelés tételei
	 */
	@XmlElementWrapper(name="orderItems")
	@XmlElement(name="orderItem")
	public Collection<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * A rendelés tételeit beállító metódus.
	 * @param orderItems A rendelés tételei
	 */
	public void setOrderItems(Collection<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	/**
	 * A rendelés dátumát visszaadó metódus.
	 * @return A rendelés dátuma
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * A rendelés dátumát beállító metódus.
	 * @param orderDate A rendelés dátuma
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
	 * Ez a metódus új tételt add, a már meglévő rendelésekhez.
	 * @param item Új rendelés tétel
	 */
	public void addOrderItem(OrderItem item){
		this.orderItems.add(item);
	}

	/**
	 * Visszaadja a vásárlót.
	 * @return A {@link storage.model.Customer} osztály megfelelő példánya
	 */
	@XmlElement(name="customer")
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Beállítja a megrendelőt.
	 * @param customer A megrendelő {@link storage.model.Customer} példánya
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Ez a metódus visszaadja, hoy teljesített-e a rendelés.
	 * @return teljesített-e a rendelés
	 */
	public boolean isClosed() {
		return closed;
	}

	
	/**
	 * Ez a metódosu állítja be, hogy zárt-e a rendelés. 
	 * @param closed zárt-e a rendelés
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	
	/**
	 * Ez a függvény adja vissza a rendelés végöszegét.
	 * @return A rendelés végösszege
	 */
	public float getPrice() {
		return price;
	}

	
	/**
	 * A rendelés végösszegét beállító metódus.
	 * @param price a rendelés végösszege
	 */
	public void setPrice(float price) {
		this.price = price;
	}	
	
	
}
