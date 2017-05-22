package storage.service.impl;

import storage.model.Product;
import storage.service.ProductService;

public class ProductServiceImpl implements ProductService{

	/* (non-Javadoc)
	 * @see storage.service.ProductService#save(storage.model.Product)
	 */
	@Override
	public void save(Product product) {
		if(this.validateProduct(product)){
			
		}
		
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getSellPrice(Product product) {
		return 0;
		// TODO Auto-generated method stub
		
	}
	
}
