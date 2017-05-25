package storage.dao;

import java.util.List;

import storage.model.Customer;

/**
 * A vásárlókat kezel DAO interfész.
 * @author Misi
 *
 */
public interface CustomerDao {

	/**
	 * A vásárló osztály mentését végző metódus.
	 * @param customer A menteni kívánt {@link storage.model.Customer} objektum
	 */
	void save(Customer customer);
	
	/**
	 * A vásárló osztály módosítását végző metódus.
	 * @param customer A módosítandó {@link storage.model.Customer} objektum
	 */
	void update(Customer customer);
	
	/**
	 * Visszaadja a paraméterben kapott id-hoz tartozó {@link storage.model.Customer} osztályt.
	 * @param id a keresett vásárló
	 * @return Ha van találat a {@link storage.model.Customer} megfelelő példánya, ha nincs NULL érték
	 */
	Customer get(int id);
	
	/**
	 * Visszaadja az összes vásárlót.
	 * @return Vásárlókat tartalmazó lista
	 */
	List<Customer> getAll();
}
