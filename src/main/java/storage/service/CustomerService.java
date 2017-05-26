package storage.service;

import java.util.List;

import storage.model.Customer;

/**
 * Ez az interfész írja le a vásárlók kezelését
 * @author Misi
 *
 */
public interface CustomerService {

	void save(Customer customer) throws IllegalArgumentException;
	
	void update(Customer customer) throws IllegalArgumentException;
	
	boolean validateCustomer(Customer customer) throws IllegalArgumentException;
	
	List<Customer> getAll();
}
