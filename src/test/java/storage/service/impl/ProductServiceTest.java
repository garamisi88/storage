package storage.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import storage.service.impl.ProductServiceImpl;
import storage.model.Product;

public class ProductServiceTest {
	
	private ProductServiceImpl productService;
	
	
	
	@Before
	public void setUp(){
		productService = Mockito.mock(ProductServiceImpl.class);
		
		Product product = new Product(1, "28920012", "Zakó", 3000, 3, 5, 0, null);
		Product product2 = new Product(2, "27890242", "Tej", 289, 25, 10, 30, LocalDate.parse("2017-05-25"));
		
		Mockito.when(productService.get(1)).thenReturn(product);
		Mockito.when(productService.getAll()).thenReturn(Arrays.asList(product, product2));
	}
	
	@Test
	public void getSellPrice(){
		Product product = productService.get(1);
		float price = product.getPrice();
		Assert.assertTrue( 3000 == price );
		
		Assert.assertTrue( 2100 == price * 0.7 );
		Assert.assertTrue( 1200 == price * 0.4 );
	}
	
	@Test
	public void get(){
		Product product = productService.get(1);
		
		Assert.assertEquals("Zakó", product.getName());
	}
	
	@Test
	public void getAll(){
		Assert.assertEquals(2, productService.getAll().size());
	}
}
