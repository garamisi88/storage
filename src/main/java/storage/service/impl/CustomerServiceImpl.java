package storage.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import storage.dao.impl.CustomerDaoImpl;
import storage.model.Customer;
import storage.service.CustomerService;

/**
 * A {@link storage.service.CustomerService} interfészt megvalósító osztály.
 * @author Misi
 *
 */
public class CustomerServiceImpl implements CustomerService {

	/**
	 * Az osztály {@link storage.dao.impl.CustomerDaoImpl} objektuma, amely az adatbázis műveleteket végezni fogja.
	 */
	private static final CustomerDaoImpl customerDao = new CustomerDaoImpl();

	@Override
	public void save(Customer customer) throws IllegalArgumentException {
		if(this.validateCustomer(customer))
			customerDao.save(customer);
	}

	@Override
	public void update(Customer customer) throws IllegalArgumentException {
		if(this.validateCustomer(customer))
			customerDao.update(customer);
		
	}

	@Override
	public boolean validateCustomer(Customer customer) throws IllegalArgumentException {
		if(customer.getName() == null || customer.getName().trim().isEmpty())
			throw new IllegalArgumentException("A név mező kitöltése kötelező!");
		
		if(customer.getEmail() == null || customer.getEmail().trim().isEmpty())
			throw new IllegalArgumentException("Az e-mail cím mező kitöltése kötelező!");
		
		if(customer.getPhone() == null || customer.getPhone().trim().isEmpty())
			throw new IllegalArgumentException("A telefonszám mező kitöltése kötelező!");
		
		if(customer.getAddress() == null)
			throw new IllegalArgumentException("Cím megadása kötelező!");
		
		if(customer.getAddress().getCountry() == null || customer.getAddress().getCountry().trim().isEmpty())
			throw new IllegalArgumentException("Az ország mező kitöltése kötelező!");
		
		if(customer.getAddress().getCity() == null || customer.getAddress().getCity().trim().isEmpty())
			throw new IllegalArgumentException("A település mező kitöltése kötelező!");
		
		if(customer.getAddress().getZip() == null || customer.getAddress().getZip().trim().isEmpty())
			throw new IllegalArgumentException("Az irányítószám mező kitöltése kötelező!");
		
		if(customer.getAddress().getStreet() == null || customer.getAddress().getStreet().trim().isEmpty())
			throw new IllegalArgumentException("Az utca, házszám mező kitöltése kötelező!");
		
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		if(!pattern.matcher(customer.getEmail()).matches())
			throw new IllegalArgumentException("Az e-mail cím formátuma nem megfelelő!");
		
		Pattern patternPhone = Pattern.compile("^(\\+)+(\\d{10}|\\d{11})");
		if(!patternPhone.matcher(customer.getPhone()).matches())
			throw new IllegalArgumentException("A telefonszám formátuma nem megfelelő! Helyes formátum +361234567");
		
		return true;
	}

	/* (non-Javadoc)
	 * @see storage.service.CustomerService#getAll()
	 */
	@Override
	public List<Customer> getAll() {
		return customerDao.getAll();
	}

	/* (non-Javadoc)
	 * @see storage.service.CustomerService#remove(storage.model.Customer)
	 */
	@Override
	public void remove(Customer customer) {
		customerDao.remove(customer);
	}
}
