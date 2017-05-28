package storage;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import storage.dao.impl.CustomerDaoImpl;
import storage.dao.impl.OrderDaoImpl;
import storage.dao.impl.ProductDaoImpl;
import storage.dao.impl.UserDaoImpl;
import storage.model.Address;
import storage.model.Customer;
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
		product.setExpiryDate(LocalDate.of(2017, 5, 31));

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
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		MyOrder order = new MyOrder();


		Customer customer = customerDao.get(1);
		order.setCustomer(customer);

		order.setOrderDate(LocalDate.of(2017, 5, 5));
		order.setReferenceId("2017/0001");
		Product product = productDao.get(1);
		List<OrderItem> items = new LinkedList<OrderItem>();

		OrderItem item = new OrderItem();
		item.setOrder(order);
		item.setProduct(product);
		item.setPrice(100);
		item.setQuantity(2);

		items.add(item);
		
		order.setOrderItems(items);
		order.setPrice(200);
		dao.save(order);

	}

	/**
	 * Ez a metódus beszúrja az adatbázisba az első vásárlót.
	 */
	public static void setCustomer() {
		CustomerDaoImpl dao = new CustomerDaoImpl();

		Customer customer = new Customer("Gara Mihály", "garamisi88@gmail.com", "+36301234567");
		Address address = new Address("4024", "Debrecen", "Sumen utca 16", "Magyarország");
		customer.setAddress(address);

		dao.save(customer);


	}
}
