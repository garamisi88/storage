package storage.datasource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import storage.model.Customer;
import storage.model.MyOrder;
import storage.model.Product;
import storage.model.User;

/**
 * Ez az osztály segít inicializálni az xml-ből az adatokat.
 * @author Misi
 *
 */
@XmlRootElement
public class Storage {

	
	/**
	 * Termékek listája.
	 */
	private List<Product> products = new ArrayList<Product>();
	
	/**
	 * Felhasználók listája, akik az alkalmazást használhatják.
	 */
	private List<User> users = new ArrayList<User>();
	
	/**
	 * Vásárlók listája.
	 */
	private List<Customer> customers = new ArrayList<Customer>();
	
	/**
	 * Rendelések listája.
	 */
	private List<MyOrder> orders = new ArrayList<MyOrder>();

	/**
	 * Termékeket visszadó metódus.
	 * @return termékek listája
	 */
	@XmlElementWrapper(name="products")
	@XmlElement(name="product")
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Termékeket beállító metódus.
	 * @param products termékek listája
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * Felhasználók listáját visszaadó metódus.
	 * @return felhasználók listája
	 */
	@XmlElementWrapper(name="users")
	@XmlElement(name="user")
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Felhasználókat beállító metódus.
	 * @param users Felhasználók listája
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Vásárlókat visszaadó metódus.
	 * @return vásárlók listája
	 */
	@XmlElementWrapper(name="customers")
	@XmlElement(name="customer")
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * Vásárlókat beállító metódus.
	 * @param customers vásárlók listája
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * Rendeléseket visszaadó metódus.
	 * @return rendelések listája
	 */
	@XmlElementWrapper(name="orders")
	@XmlElement(name="order")
	public List<MyOrder> getOrders() {
		return orders;
	}

	/**
	 * Rendeléseket beállító metódus.
	 * @param orders rendelések listája
	 */
	public void setOrders(List<MyOrder> orders) {
		this.orders = orders;
	}
	
	
	
}
