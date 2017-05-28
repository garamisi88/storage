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
	 * @param product A mentendő {@link storage.model.Product} objektum
	 */
	void save(Product product);

	/**
	 * A termékek módosítását végző függvény.
	 * @param product A módosítani kívánt {@link storage.model.Product} objectum
	 */
	void update(Product product);
	
	/**
	 * Termék törlését végző metódus.
	 * @param product A törlendő termék
	 */
	void remove(Product product);
	
	/**
	 * Visszaadja a paraméterként kapott id-hoz tartozó {@link storage.model.Product} osztály megfelelő példányát.
	 * @param id a termék idja
	 * @return {@link storage.model.Product} objektum
	 */
	Product get(int id);
	
	/**
	 * Visszaadja az összes terméket.
	 * @return Termékek listája
	 */
	List<Product> getAll();
	
	
	/**
	 * Visszaadja az aktív termékeket.
	 * @return Termékek listája
	 */
	List<Product> getActiveProducts();
	
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
