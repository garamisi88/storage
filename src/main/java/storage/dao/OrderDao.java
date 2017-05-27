package storage.dao;

import java.util.List;

import storage.model.MyOrder;

/**
 * A rendeléseket kezelő DAO interfész.
 * @author Misi
 *
 */
public interface OrderDao {

	/**
	 * A rendelés mentését végző függvény.
	 * @param order A rendelés objektum
	 */
	void save(MyOrder order);
	
	/**
	 * A rendelés módosítását végző függvény.
	 * @param order A rendelés objektum
	 */
	void update(MyOrder order);
	
	/**
	 * Ez a metódus törtli a rendszerből a paraméterként kapott rendelést.
	 * @param order A törlendő {@link storage.model.MyOrder} objektum
	 */
	void remove(MyOrder order);
	
	/**
	 * Visszaadja a megadott azonosítójú (id) rendelést.
	 * @param id A rendelés id-ja
	 * @return {@link storage.model.MyOrder} objektum
	 */
	MyOrder get(int id);
	
	/**
	 * Visszaadja az összes rendelést.
	 * @return Rendelések listája
	 */
	List<MyOrder> getAll();
	
	/**
	 * Visszaadja azokat a rendeléseket, amelyek már kiadásra kerültek.
	 * @return Rendelések listája
	 */
	List<MyOrder> getClosedOrders();
	
	/**
	 * Visszaadja a még aktív -kezeletlen- rendelések listáját.
	 * @return Rendelések listája.
	 */
	List<MyOrder> getActiveOrders();
}
