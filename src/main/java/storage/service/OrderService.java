package storage.service;

import java.util.List;

import storage.model.MyOrder;
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
	 * @throws Exception Ha nem menthető a rendelés, a hiba okát továbbdobja a hívó metódusnak
	 */
	void save(MyOrder order) throws Exception;
	
	/**
	 * A rendelés módosítását végző függvény.
	 * <p>Először megállapítja a {@link storage.service.OrderService#validateOrder(MyOrder)} metódus segítségével, hogy módosítható-e az adott rendelés. Amennyiben igen, a {@link storage.dao.impl.OrderDaoImpl} osztály módosítja a rendelést</p> 
	 * @param order A menteni kívánt {@link storage.model.MyOrder} objektum
	 * @throws Exception Ha nem menthető a rendelés, a hiba okát továbbdobja a hívó metódusnak
	 */
	void update(MyOrder order) throws Exception;
	
	/**
	 * Ez a metóddus vizsgálja meg, hogy menthető-e a {@link storage.model.MyOrder} objektum.
	 * @param order Validálandó {@link storage.model.MyOrder} objektum
	 * @return boolean Menhető-e a rendelés
	 * @throws Exception Ha nem menthető a rendelés, a hiba okát tartalmazó exception-t dob a függvény
	 */
	boolean validateOrder(MyOrder order) throws Exception;
	
	/**
	 * Ez a metódus adja vissza a rendelés végösszegét.
	 * @param order A {@link storage.model.MyOrder} objektum, amelynek a végösszegét tudni akarjuk.
	 * @return A rendelés végösszege
	 */
	float getOrderSum(MyOrder order);
	
	/**
	 * Vissza adja az összes rendelés. 
	 * <p>A függvény paraméterben várja, hogy milyen listára van szükségünk.</p>
	 * @param type String paraméter, amely arra utal, hogy a dao melyik függvényét hívja
	 * @return Rendelések listája
	 */
	List<MyOrder> getAll(String type);
}
