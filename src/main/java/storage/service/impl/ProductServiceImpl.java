package storage.service.impl;

import java.time.Duration;
import java.time.LocalDate;

import storage.dao.impl.ProductDaoImpl;
import storage.model.Product;
import storage.service.ProductService;

public class ProductServiceImpl implements ProductService{

	/* (non-Javadoc)
	 * @see storage.service.ProductService#save(storage.model.Product)
	 */
	@Override
	public void save(Product product) throws Exception {
		if(validateProduct(product)){
			ProductDaoImpl dao = new ProductDaoImpl();
			dao.save(product);
		}
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#update(storage.model.Product)
	 */
	@Override
	public void update(Product product) throws Exception {
		if(validateProduct(product)){
			ProductDaoImpl dao = new ProductDaoImpl();
			dao.update(product);
		}
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#validateProduct(storage.model.Product)
	 */
	@Override
	public boolean validateProduct(Product product) throws Exception {
		if(product.getName().trim().isEmpty())
			throw new Exception("A név kitöltése kötelező!");
		if(product.getSku().trim().isEmpty())
			throw new Exception("A raktározási szám megadása kötelező!");
		if(product.getPrice() <= 0)
			throw new Exception("A termék árának nullánál nagyobbnak kell lennie!");
		if(product.getQuantity() < 1)
			throw new Exception("A mennyiség értéke csak nullánál nagyobb egész szám lehet!");
		
		return false;
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#getSellPrice(storage.model.Product)
	 */
	@Override
	public float getSellPrice(Product product) {
		double multiplier = 1;
		LocalDate currentDate = LocalDate.now();
		int days = (int) Duration.between(currentDate.atTime(0, 0), product.getExpiryDate().atTime(0, 0)).toDays();
		if(days >= 0 && days <= 3){
			multiplier = 0.4;
		}else if (days <= 7){
			multiplier = 0.7;
		}
		return (float) (product.getPrice() * multiplier);
	}
}
