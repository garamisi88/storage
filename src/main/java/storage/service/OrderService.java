package storage.service;

import java.util.Collection;
import java.util.List;

import storage.model.MyOrder;
import storage.model.OrderItem;
import storage.model.Product;

/**
 * A rendeléseket kezelését leíró interfész.
 * @author gara.mihaly
 *
 */
public interface OrderService {
	
	/**
	 * A rendelés mentését végző függvény.
	 * <p>Először megállapítja a {@link storage.service.OrderService#validateOrder(MyOrder)} metódus segítségével, hogy menthető-e az adott rendelés. Amennyiben igen, a {@link storage.dao.impl.OrderDaoImpl} osztály menti a rendelést</p> 
	 * @param order A menteni kívánt {@link storage.model.MyOrder} objektum
	 * @throws IllegalArgumentException Ha nem menthető a rendelés, a hiba okát továbbdobja a hívó metódusnak
	 */
	void save(MyOrder order) throws IllegalArgumentException;
	
	/**
	 * A rendelés módosítását végző függvény.
	 * <p>Először megállapítja a {@link storage.service.OrderService#validateOrder(MyOrder)} metódus segítségével, hogy módosítható-e az adott rendelés. Amennyiben igen, a {@link storage.dao.impl.OrderDaoImpl} osztály módosítja a rendelést</p> 
	 * @param order A menteni kívánt {@link storage.model.MyOrder} objektum
	 * @throws IllegalArgumentException Ha nem menthető a rendelés, a hiba okát továbbdobja a hívó metódusnak
	 */
	void update(MyOrder order) throws IllegalArgumentException;
	
	/**
	 * Ez a metóddus vizsgálja meg, hogy menthető-e a {@link storage.model.MyOrder} objektum.
	 * @param order Validálandó {@link storage.model.MyOrder} objektum
	 * @return boolean Menthető-e a rendelés
	 * @throws IllegalArgumentException Ha nem menthető a rendelés, a hiba okát tartalmazó exception-t dob a függvény
	 */
	boolean validateOrder(MyOrder order) throws IllegalArgumentException;
	
	/**
	 * Ez a metódus adja vissza a rendelés végösszegét.
	 * @param orderItems A {@link storage.model.OrderItem} objektumokat tartalmazó kollekció.
	 * @return A rendelés végösszege
	 */
	float getOrderSum(Collection<OrderItem> orderItems);
	
	/**
	 * Visszadja, hogy a paraméterként kapott termékből összesen mennyi van a kosárban.
	 * @param orderItems A rendelés tételeket tartalmazó kollekció
	 * @param product a vizsgálandó {@link storage.model.Product} osztály
	 * @return int Megfelelő-e a mennyiség
	 */
	int getProductCartQuantity(Collection<OrderItem> orderItems, Product product);
	
	/**
	 * Vissza adja az összes rendelés. 
	 * <p>A függvény paraméterben várja, hogy milyen listára van szükségünk.</p>
	 * @param type String paraméter, amely arra utal, hogy a dao melyik függvényét hívja
	 * @return Rendelések listája
	 */
	List<MyOrder> getAll(String type);
	
	/**
	 * Ez a metódus törli a {@link storage.dao.impl.OrderDaoImpl} osztályon át a terméket az adatbázisból.
	 * @param order a törlendő rendelés
	 */
	void remove(MyOrder order);
}
