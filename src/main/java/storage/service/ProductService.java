package storage.service;

import java.util.List;

import storage.model.Product;

/**
 * Ez az interfész írja le a termékek kezelését.
 * @author gara.mihaly
 *
 */
/**
 * @author Misi
 *
 */
public interface ProductService {
	
	/**
	 * A termék mentését végző függvény.
	 * <p>Először megállapítja a {@link storage.service.ProductService#validateProduct(Product)} metódus segítségével, hogy menthető-e az adott termék. Amennyiben igen, a {@link storage.dao.impl.ProductDaoImpl} osztály menti a terméket</p> 
	 * @param product A menteni kívánt {@link storage.model.Product} objektum
	 * @throws IllegalArgumentException Ha nem menthető a termék, a hiba okát továbbdobja a hívó metódusnak
	 */
	void save(Product product) throws IllegalArgumentException;
	
	/**
	 * A termék módosítását végző függvény.
	 * <p>Először megállapítja a {@link storage.service.ProductService#validateProduct(Product)} metódus segítségével, hogy módosítható-e az adott termék. Amennyiben igen, a {@link storage.dao.impl.ProductDaoImpl} osztály módosítja a terméket</p>
	 * @param product A módosítani kívánt {@link storage.model.Product} objektum
	 * @throws IllegalArgumentException Ha nem menthető a termék, a hiba okát továbbdobja a hívó függvények
	 */
	void update(Product product) throws IllegalArgumentException;
	
	/**
	 * Törli az a {@link storage.dao.impl.ProductDaoImpl} osztályon keresztül a terméket az adatbázisból.
	 * @param product a törlendő termék
	 */
	void remove(Product product);
	
	/**
	 * Ez a metóddus vizsgálja meg, hogy menthető-e a {@link storage.model.Product} objektum.
	 * @param product Validálandó {@link storage.model.Product}
	 * @return boolean Menhető-e a termék
	 * @throws IllegalArgumentException Ha nem menthető a termék, a hiba okát tartalmazó exception-t dob a függvény
	 */
	boolean validateProduct(Product product) throws IllegalArgumentException;
	
	/**
	 * A termék eladási árát visszaadó metódus.
	 * <p>Ha a termék lejárati ideje, és az aktuális dátum között kevesebb, mint 10 nap van, abban az esetben az alapár 70%-át számolja a rendszer.</p>
	 * <p>Ha a termék lejárati ideje, és az aktuális dátum között már kevesebb, mint 3 nap van, akkor a termék ára az alapár 40%-a.</p>
	 * @param product A {@link storage.model.Product} osztály egy példánya, amelynek az árát meg akarjuk tudni
	 * @return float A termék eladási ára
	 */
	float getSellPrice(Product product);
	
	/**
	 * Visszaadja a paraméternek megfelelő termékeket.
	 * @param type melyik listát adja vissza a metódus
	 * @return {@link storage.model.Product} objecktumok listája
	 */
	List<Product> getAll(String type);
	
	
	/**
	 * Visszadja a paraméterként kapott id-jú terméket.
	 * @param id A termék id-ja
	 * @return A kért termék
	 */
	Product get(int id);
	
	/**
	 * A függvény megállapítja, hogy rendelésre szorul-e a termék.
	 * @param product A {@link storage.model.Product} osztály egy példánya
	 * @return Rendelésre szorul-e a termék
	 */
	boolean needToOrder(Product product);
	
	/**
	 * Ez a metódus határozza meg egy termékről, hogy az selejt-e.
	 * @param product A vizsgálandó termék.
	 * @return Selejt-e az adott termék
	 */
	boolean isWasteProduct(Product product);
}
