package storage.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import storage.dao.OrderDao;
import storage.dao.ProductDao;
import storage.dao.impl.OrderDaoImpl;
import storage.model.Customer;
import storage.model.MyOrder;
import storage.model.OrderItem;
import storage.model.Product;

public class OrderServiceTest {

	private OrderServiceImpl orderService;
	
	private OrderDao orderDao;
	
	private ProductDao productDao;

	@Before
	public void setUp(){
		orderService = new OrderServiceImpl();
		orderDao = Mockito.mock(OrderDaoImpl.class);
		productDao = Mockito.mock(ProductDao.class);
		
		Product product = new Product(1, "12341235", "Termék", 2400, 3, 5, 2, null);
		Customer customer = new Customer("Béla", "teszt@teszt.hu", "+36301234567");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		
		MyOrder order = new MyOrder();
		order.setId(1);
		order.setReferenceId("1231230011");
		order.setPrice(2400);
		order.setOrderDate(date);
		order.setCustomer(customer);
		
		OrderItem item1 = new OrderItem();
		item1.setOrder(order);
		item1.setPrice(product.getPrice());
		item1.setProduct(product);
		item1.setQuantity(1);
		
		order.setOrderItems(Arrays.asList(item1));
		
		MyOrder order2 = new MyOrder();
		order2.setReferenceId("123544981");
		order2.setOrderDate(date);
		
		OrderItem item2 = new OrderItem();
		item2.setOrder(order2);
		item2.setPrice(product.getPrice());
		item2.setProduct(product);
		item2.setQuantity(2);
		
		OrderItem item3 = new OrderItem();
		item3.setOrder(order2);
		item3.setPrice(2160);
		item3.setProduct(product);
		item3.setQuantity(3);
		
		order2.setOrderItems(Arrays.asList(item2, item3));
		
		MyOrder order3 = new MyOrder();
		order3.setReferenceId("123123123");
		order3.setCustomer(customer);
		order3.setOrderDate(date);
		
		
		Mockito.when(orderDao.get(1)).thenReturn(order);
		Mockito.when(orderDao.get(2)).thenReturn(order2);
		Mockito.when(orderDao.get(3)).thenReturn(order3);
		
		Mockito.when(productDao.get(1)).thenReturn(product);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void failedSaveEmptyCart(){
		orderService.save(orderDao.get(3));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void failedSaveEmptyCustomer(){
		orderService.save(orderDao.get(2));
	}
	
	@Test
	public void testSuccessValidation(){
		Assert.assertTrue(orderService.validateOrder(orderDao.get(1)));
	}
	
	@Test
	public void checkCartSum(){
		Assert.assertEquals( 2 * 2400 + 3 * 2160, orderService.getOrderSum(orderDao.get(2).getOrderItems()), 1);
	}
	
	@Test
	public void checkQuantity(){
		Assert.assertEquals(5, orderService.getProductCartQuantity(orderDao.get(2).getOrderItems(), productDao.get(1)));
	}
}
