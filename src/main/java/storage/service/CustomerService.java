package storage.service;

import storage.model.Customer;

/**
 * Ez az interfész írja le a vásárlók kezelését
 * @author Misi
 *
 */
public interface CustomerService {

	void save(Customer customer) throws IllegalArgumentException;
	
	void update(Customer customer) throws IllegalArgumentException;
	
	void validateCustomer(Customer customer) throws IllegalArgumentException;
}
