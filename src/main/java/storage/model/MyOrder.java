package storage.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

/**
 * A rendeléseket leíró osztály.
 * @author Misi
 *
 */
@Entity
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
	private LocalDate orderDate;
	
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
	 * Rendelés id-ját visszaadó metódus.
	 * @return A rendelés id-ja
	 */
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
	public LocalDate getOrderDate() {
		return orderDate;
	}

	/**
	 * A rendelés dátumát beállító metódus.
	 * @param orderDate A rendelés dátuma
	 */
	public void setOrderDate(LocalDate orderDate) {
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
}
