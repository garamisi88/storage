package storage;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import storage.dao.impl.OrderDaoImpl;
import storage.dao.impl.ProductDaoImpl;
import storage.dao.impl.UserDaoImpl;
import storage.model.MyOrder;
import storage.model.OrderItem;
import storage.model.Product;
import storage.model.User;

/**
 * Az alkalmazást inicializáló osztály.
 * @author Misi
 *
 */
public class StorageInitialization {

	/**
	 * Ez a függvény menti be az adatbázisba az alap termékeket.
	 */
	public static void setBaseProducts(){
		ProductDaoImpl dao = new ProductDaoImpl();

		Product product = new Product();
		product.setName("Pizza");
		product.setSku("QWER-123");
		product.setPrice(2000);
		product.setQuantity(20);
		product.setOptimalQuantity(70);
		product.setMinimumQuantity(30);
		product.setExpiryDate(LocalDate.of(2017, 5, 5));

		dao.save(product);
	}

	/**
	 * Az felhasználó-t létrehozó metódus.
	 */
	public static void setUser(){
		UserDaoImpl dao = new UserDaoImpl();

		User user = new User();
		user.setName("Teszt Elek");
		user.setUsername("teszt.elek");
		user.setPassword(DigestUtils.sha1Hex("teszt"));

		dao.save(user);
	}

	/**
	 * Létrehoz egy rendelést, és azt rögzíti az adatbázisban.
	 */
	public static void setOrder(){
		OrderDaoImpl dao = new OrderDaoImpl();
		ProductDaoImpl productDao = new ProductDaoImpl();

		MyOrder order = new MyOrder();
		order.setOrderDate(LocalDate.of(2017, 5, 4));
		Product product = productDao.get((long)1);
		OrderItem item = new OrderItem();
		item.setOrder(order);
		item.setProduct(product);
		item.setPrice(100);
		item.setQuantity(2);
		List<OrderItem> items = new LinkedList<OrderItem>();
		items.add(item);
		order.setOrderItems(items);
		dao.save(order);

	}
}
