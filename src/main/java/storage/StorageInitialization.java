package storage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storage.dao.impl.CustomerDaoImpl;
import storage.dao.impl.OrderDaoImpl;
import storage.dao.impl.ProductDaoImpl;
import storage.dao.impl.UserDaoImpl;
import storage.datasource.Storage;

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

	private static final Logger logger = LoggerFactory.getLogger(StorageInitialization.class);

	/**
	 * A megadott xml fájlból inicializálja az adatbázist.
	 */
	public static void loadFromXML(){
		Storage storage;
		try {
			InputStream inputStream = StorageInitialization.class.getResourceAsStream("/storage.xml");
			BufferedReader file = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			
			JAXBContext jaxbC = JAXBContext.newInstance(Storage.class);
			Unmarshaller unmarshaller = jaxbC.createUnmarshaller();
			
			storage = (Storage) unmarshaller.unmarshal(file);
			saveDatasToDb(storage);
		
		} catch (JAXBException e) {
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
	}
	
	private static void saveDatasToDb(Storage storage){
		UserDaoImpl userDao = new UserDaoImpl();
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		ProductDaoImpl productDao = new ProductDaoImpl();
		OrderDaoImpl orderDao = new OrderDaoImpl();
		
		if(storage.getUsers().size() > 0){
			for (User user : storage.getUsers()) {
				user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
				userDao.save(user);
			}
		}
		
		if(storage.getCustomers().size() > 0){
			for (Customer customer : storage.getCustomers()){
				customerDao.save(customer);
			}
		}
		
		if(storage.getProducts().size() > 0){
			for(Product product : storage.getProducts()){
				productDao.save(product);
			}
		}
		
		if(storage.getOrders().size() > 0){
			for(MyOrder order : storage.getOrders()){
				for (OrderItem item : order.getOrderItems()) {
					Product product = productDao.get(item.getProduct().getId());
					item.setProduct(product);
					item.setOrder(order);
				}
				Customer customer = customerDao.get(order.getCustomer().getId());
				order.setCustomer(customer);
				orderDao.save(order);
			}
		}
	}
}
