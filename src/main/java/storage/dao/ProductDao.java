package storage.dao;

import java.util.List;

import storage.model.Product;

/**
 * A termékeket kezelő DAO interfész.
 * @author Misi
 */
public interface ProductDao {
	
	/**
	 * A termék mentését végző függvény.
	 * @param product A mentendő product objektum
	 */
	void save(Product product);

	/**
	 * A termékek módosítását végző függvény.
	 * @param product A módosítani kívánt product objectum
	 */
	void update(Product product);
	
	Product get(Long id);
	/**
	 * Visszaadja az összes terméket.
	 * @return Termékek listája
	 */
	List<Product> getAll();
	
	/**
	 * Visszaadja azokat a termékek, amelyek rendelésre szorulnak.
	 * @return Termékek listája
	 */
	List<Product> getOrderableProducts();
	
	
	/**
	 * Visszaadja a selejt termékeket.
	 * @return Termékek listája
	 */
	List<Product> getWasteProducts(); 
}
