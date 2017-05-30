package storage.service.impl;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import storage.service.impl.ProductServiceImpl;
import storage.dao.impl.ProductDaoImpl;
import storage.model.Product;

public class ProductServiceTest {
	
	private ProductServiceImpl productService;
	
	private ProductDaoImpl productDao;
	
	@Before
	public void setUp(){
		productDao = Mockito.mock(ProductDaoImpl.class);
		productService = new ProductServiceImpl();
		
		LocalDate date1 = LocalDate.now().plusDays(2);
		LocalDate date2 = LocalDate.now().plusDays(5);
		LocalDate date3 = LocalDate.now().plusDays(20);
		
		Product product = new Product(1, "28920012", "Zakó", 3000, 3, 10, 5, date1, 1720);
		Product product2 = new Product(2, "27890242", "", 289, 25, 30, 15, date2, 123);
		Product product3 = new Product(3, "", "Ásványvíz", 89, 40, 130, 0, date3, 63);
		Product product4 = new Product(4, "21131221", "Zeller", 0, 0, 0, 0, date2, 123);
		Product product5 = new Product(5, "123123", "Macska eledel", 2349,0, 0,0, date3, 0);
		
		Mockito.when(productDao.get(1)).thenReturn(product);
		Mockito.when(productDao.get(2)).thenReturn(product2);
		Mockito.when(productDao.get(3)).thenReturn(product3);
		Mockito.when(productDao.get(4)).thenReturn(product4);
		Mockito.when(productDao.get(5)).thenReturn(product5);
		Mockito.when(productDao.getAll()).thenReturn(Arrays.asList(product, product2, product3));
	}
	
	@Test
	public void testSellPrice(){
		Product product = productDao.get(1);
		Product product2 = productDao.get(2);
		Product product3 = productDao.get(3);
		
		Assert.assertEquals( product3.getPrice(), productService.getSellPrice(product3), 1 );
		Assert.assertEquals( product2.getPrice() * 0.7, productService.getSellPrice(product2), 1 );
		Assert.assertEquals( product.getPrice() * 0.4, productService.getSellPrice(product), 1 );
	}
	
	@Test
	public void testValidateProduct(){
		Assert.assertTrue( productService.validateProduct(productDao.get(1)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInValidProduct(){
		productService.validateProduct(productDao.get(2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailedSave(){
		productService.save(productDao.get(3));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailedUpdate(){
		productService.update(productDao.get(3));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailedEntryPrice(){
		productService.validateProduct(productDao.get(5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailedPrice(){
		productService.validateProduct(productDao.get(4));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLowPrice(){
		Product product = productDao.get(1);
		product.setEntryPrice(4000);
		productService.validateProduct(product);
	}
	
	
	@Test
	public void needToOrder(){
		Product product = productDao.get(1);
		
		Assert.assertTrue( productService.needToOrder(product) );
		
		Product product2 = productDao.get(2);
		Assert.assertFalse( productService.needToOrder(product2) );
		
		Product product3 = productDao.get(3);
		Assert.assertFalse( productService.needToOrder(product3) );
	}
}
