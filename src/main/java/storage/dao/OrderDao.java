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
	 * Visszaadja az összes rendelést.
	 * @return Rendelések listája
	 */
	List<MyOrder> getAll();
}
