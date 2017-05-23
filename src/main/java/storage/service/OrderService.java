package storage.service;

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
}
