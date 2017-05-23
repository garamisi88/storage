package storage.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * A rendeléseket leíró osztály.
 * @author Misi
 *
 */
@Entity
public class MyOrder implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int id;
	
	@OneToMany(mappedBy="order")
	private Collection<OrderItem> orderItems;
	
	@Column(name="order_date")
	private LocalDate orderDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Collection<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Collection<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	public void addOrderItem(OrderItem item){
		this.orderItems.add(item);
	}
	
	
	
	
}
