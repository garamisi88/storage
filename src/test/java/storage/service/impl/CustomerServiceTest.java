package storage.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import storage.dao.impl.CustomerDaoImpl;
import storage.datasource.Utils;
import storage.model.Address;
import storage.model.Customer;

public class CustomerServiceTest {
	
	private CustomerServiceImpl customerService;
	
	private CustomerDaoImpl customerDao;
	
	@Before
	public void setUp(){
		customerService = new CustomerServiceImpl();
		
		customerDao = Mockito.mock(CustomerDaoImpl.class);
		
		Customer customer = new Customer("Kiss Pista", "kiss.pista@teszt.hu", "+36301231231");
		Address address = new Address("4024", "Debrecen", "Sumen utca", "Magyarország");
		customer.setAddress(address);
		
		Customer customer2 = new Customer("Kiss Pista Béla", "kiss.pista.bela@teszt.hu", "+36301231231");
		
		Mockito.when(customerDao.get(1)).thenReturn(customer);
		Mockito.when(customerDao.get(2)).thenReturn(customer2);
	}
	
	@Test
	public void testSuccessValidation(){
		Assert.assertTrue(customerService.validateCustomer(customerDao.get(1)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyName(){
		customerService.validateCustomer(new Customer("", "teszt@teszt.hu", "+123123123"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyEmail(){
		customerService.validateCustomer(new Customer("Teszt Elek", "", "+123123123"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyPhone(){
		customerService.validateCustomer(new Customer("Teszt Elek", "teszt@teszt.hu", ""));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyAddress(){
		customerService.validateCustomer(customerDao.get(2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyCountry(){
		Customer customer = customerDao.get(2);
		Address address = new Address("", "", "", "");
		customer.setAddress(address);
		customerService.validateCustomer(customer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyCity(){
		Customer customer = customerDao.get(2);
		Address address = new Address("", "", "", "Magyarország");
		customer.setAddress(address);
		customerService.validateCustomer(customer);
	}

	@Test(expected=IllegalArgumentException.class)
	public void emptyZip(){
		Customer customer = customerDao.get(2);
		Address address = new Address("", "Debrecen", "", "Magyarország");
		customer.setAddress(address);
		customerService.validateCustomer(customer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyStreet(){
		Customer customer = customerDao.get(2);
		Address address = new Address("4024", "Debrecen", "", "Magyarország");
		customer.setAddress(address);
		customerService.validateCustomer(customer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void failedSave(){
		customerService.save(customerDao.get(2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void failedUpdate(){
		customerService.update(customerDao.get(2));
	}
	
	@After
	public void closeConnection(){
		Utils.connectionClose();
	}
}
