package storage.service;

import java.util.List;

import storage.model.Customer;

/**
 * Ez az interfész írja le a vásárlók kezelését.
 * @author Misi
 *
 */
public interface CustomerService {

	/**
	 * A vásárló mentését végző metódus.
	 * <p>Először meg állapítja a {@link storage.service.CustomerService#validateCustomer(Customer)} függvény segítségével, hogy menthető-e a vásárló, ha igen, akkor {@link storage.dao.impl.CustomerDaoImpl} osztályon keresztül menti a vásárlót</p>
	 * @param customer a mentendő {@link storage.model.Customer} objektum
	 * @throws IllegalArgumentException Ha nem menthető az objektum, hibaüzenetet dob
	 */
	void save(Customer customer) throws IllegalArgumentException;
	
	/**
	 * A vásárló módosítását végző metódus.
	 * <p>Először meg állapítja a {@link storage.service.CustomerService#validateCustomer(Customer)} függvény segítségével, hogy módosítható-e a vásárló, ha igen, akkor {@link storage.dao.impl.CustomerDaoImpl} osztályon keresztül módosítja a vásárlót</p>
	 * @param customer a módosítandó {@link storage.model.Customer} objektum
	 * @throws IllegalArgumentException Ha nem menthető az objektum, hibaüzenetet dob
	 */
	void update(Customer customer) throws IllegalArgumentException;
	
	/**
	 * Megállapítja, hogy menthető-e az objektum.
	 * @param customer a validálandó vásárló objektum
	 * @return Menthető-e az objektum
	 * @throws IllegalArgumentException Ha nem menthető az objektum egy hibaüzenetet dob a függvény
	 */
	boolean validateCustomer(Customer customer) throws IllegalArgumentException;
	
	/**
	 * Visszaadja az összes vásárlót.
	 * @return Vásárlókat tartalmazó lista
	 */
	List<Customer> getAll();
}
