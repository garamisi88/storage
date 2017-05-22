package storage.service;

import storage.model.Product;

public interface ProductService {
	void save(Product product);
	
	void update(Product product);
	
	void validateProduct(Product product);
}
