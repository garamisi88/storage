package storage.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * A vásárlókat leíró osztály. A vásálók címét egy beágyazott {@link storage.model.Address} osztály reprezentálja.
 * @author Misi
 *
 */
/**
 * @author Misi
 *
 */
@Entity
public class Customer {
	
	/**
	 * A vásárló id-ja.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 * A megrendelő neve.
	 */
	private String name;
	
	/**
	 * A megrendelő email címe.
	 */
	private String email;
	
	/**
	 * A megrendelő telefonszáma.
	 */
	private String phone;
	
	/**
	 * A megrendelő címe, amelyet egy beágyazott {@link storage.model.Address} osztály ír le.
	 */
	@Embedded
	private Address address;

	/**
	 * A vásárló rendeléseit tartalmazó lista. 
	 */
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	private List<MyOrder> orders;
	
	/**
	 * Az osztály paraméter nélküli konstruktora.
	 */
	public Customer() {
	}

	/**
	 * Az osztály paraméteres konstruktora.
	 * @param name a megrendelő neve
	 * @param email a megrendelő email címe
	 * @param phone a megrendelő telefonszáma
	 */
	public Customer(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * A megrendelő id-ját visszadó metódus.
	 * @return A megrendelő id-ja
	 */
	public int getId() {
		return id;
	}

	/**
	 * Beállítja a megrendelő id-ját.
	 * @param id a megrendelő id-ja
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * A megrendelő nevét visszaadó függvény.
	 * @return a megrendelő neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ez a függvény állítja be a megrendelő nevét.
	 * @param name A megrendelő neve
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * A megrendelő email címét visszaadó függvény.
	 * @return A megrendelő email címe
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * A megrendelő email címét beállító függvény.
	 * @param email A megrendelő email címe.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * A megrendelő telefonszámát visszaadó metódus.
	 * @return A megrendelő telefonszáma
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * A megrendelő telefonszámát beállító metódus.
	 * @param phone a megrendelő telefonszáma
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * A megrendelő címét visszaadó függvény.
	 * @return a megrendelő címe, amely a {@link storage.model.Address} osztály egy példánya
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * A megrendelő címét beállító metódus.
	 * @param address a megrendelő címe
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * A vásárló korábbi rendelései visszadó metódus.
	 * @return rendelések listja
	 */
	public List<MyOrder> getOrders() {
		return orders;
	}

	/**
	 * A vásárló korábbi rendeléseit visszaadó metódus.
	 * @param orders rendelések listája
	 */
	public void setOrders(List<MyOrder> orders) {
		this.orders = orders;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getName()+" ("+this.getEmail()+")";
	}
	
	
	
	
}
