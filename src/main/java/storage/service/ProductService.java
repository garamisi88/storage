package storage.service;

import storage.model.Product;

/**
 * Ez az interfész írja le a termékek kezelését.
 * @author gara.mihaly
 *
 */
public interface ProductService {
	
	/**
	 * A termék mentését végző függvény.
	 * <p>Először megállapítja a {@link storage.service.ProductService#validateProduct(Product)} metódus segítségével, hogy menthető-e az adott termék. Amennyiben igen, a {@link storage.dao.impl.ProductDaoImpl} osztály menti a terméket</p> 
	 * @param product A menteni kívánt {@link storage.model.Product} objektum
	 * @throws Exception Ha nem menthető a termék, a hiba okát továbbdobja a hívó metódusnak
	 */
	void save(Product product) throws Exception;
	
	/**
	 * A termék módosítását végző függvény.
	 * <p>Először megállapítja a {@link storage.service.ProductService#validateProduct(Product)} metódus segítségével, hogy módosítható-e az adott termék. Amennyiben igen, a {@link storage.dao.impl.ProductDaoImpl} osztály módosítja a terméket</p>
	 * @param product A módosítani kívánt {@link storage.model.Product} objektum
	 * @throws Exception Ha nem menthető a termék, a hiba okát továbbdobja a hívó függvények
	 */
	void update(Product product) throws Exception;
	
	/**
	 * Ez a metóddus vizsgálja meg, hogy menthető-e a {@link storage.model.Product} objektum.
	 * @param product Validálandó {@link storage.model.Product}
	 * @return boolean Menhető-e a termék
	 * @throws Exception Ha nem menthető a termék, a hiba okát tartalmazó exception-t dob a függvény
	 */
	boolean validateProduct(Product product) throws Exception;
	
	/**
	 * A termék eladási árát visszaadó metódus.
	 * <p>Ha a termék lejárati ideje, és az aktuális dátum között kevesebb, mint 10 nap van, abban az esetben az alapár 70%-át számolja a rendszer.</p>
	 * <p>Ha a termék lejárati ideje, és az aktuális dátum között már kevesebb, mint 3 nap van, akkor a termék ára az alapár 40%-a.</p>
	 * @param product A {@link storage.model.Product} osztály egy példánya, amelynek az árát meg akarjuk tudni
	 * @return float A termék eladási ára
	 */
	float getSellPrice(Product product);
}
