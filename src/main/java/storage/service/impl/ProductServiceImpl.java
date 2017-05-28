package storage.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import storage.dao.impl.ProductDaoImpl;
import storage.model.Product;
import storage.service.ProductService;

/**
 * A {@link storage.service.ProductService} interfészt implementáló osztály.
 * @author gara.mihaly
 *
 */
public class ProductServiceImpl implements ProductService{

	/**
	 * Az osztály {@link storage.dao.impl.ProductDaoImpl} objektuma, amely az adatbázis műveleteket végzi.
	 */
	private final static ProductDaoImpl productDao = new ProductDaoImpl();
	
	/* (non-Javadoc)
	 * @see storage.service.ProductService#save(storage.model.Product)
	 */
	@Override
	public void save(Product product) throws IllegalArgumentException {
		if(validateProduct(product)){
			productDao.save(product);
		}
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#update(storage.model.Product)
	 */
	@Override
	public void update(Product product) throws IllegalArgumentException {
		if(validateProduct(product)){
			productDao.update(product);
		}
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#validateProduct(storage.model.Product)
	 */
	@Override
	public boolean validateProduct(Product product) throws IllegalArgumentException {
		if(product.getName() == null || product.getName().trim().isEmpty())
			throw new IllegalArgumentException("A név kitöltése kötelező!");
		if(product.getSku() == null || product.getSku().trim().isEmpty())
			throw new IllegalArgumentException("A raktározási szám megadása kötelező!");
		if(product.getPrice() <= 0)
			throw new IllegalArgumentException("A termék árának nullánál nagyobbnak kell lennie!");
		if(product.getQuantity() < 1)
			throw new IllegalArgumentException("A mennyiség értéke csak nullánál nagyobb egész szám lehet!");
		
		return true;
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#getSellPrice(storage.model.Product)
	 */
	@Override
	public float getSellPrice(Product product) {
		double multiplier = 1;
		if(product.getExpiryDate() != null) {
			LocalDate currentDate = LocalDate.now();
			int days = (int) Duration.between(currentDate.atTime(0, 0), product.getExpiryDate().atTime(0, 0)).toDays();
			if(days >= 0 && days <= 3){
				multiplier = 0.4;
			}else if (days > 3 && days <= 7){
				multiplier = 0.7;
			}
		}
		
		return (float) (product.getPrice() * multiplier);
	}

	
	/* (non-Javadoc)
	 * @see storage.service.ProductService#needToOrder(storage.model.Product)
	 */
	@Override
	public boolean needToOrder(Product product) {
		if(product.getMinimumQuantity() == 0)
			return false;
		
		return (product.getMinimumQuantity() > 0 && product.getQuantity() < product.getMinimumQuantity());
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#get(int)
	 */
	@Override
	public Product get(int id) {
		return productDao.get(id);
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#remove(storage.model.Product)
	 */
	@Override
	public void remove(Product product) {
		productDao.remove(product);
	}

	/* (non-Javadoc)
	 * @see storage.service.ProductService#getAll(java.lang.String)
	 */
	@Override
	public List<Product> getAll(String type) {
		switch( type ){
			case "waste":
				return productDao.getWasteProducts();
			case "orderable":
				return productDao.getOrderableProducts();
			case "all":
				return productDao.getAll();
			case "active":
			default:
				return productDao.getActiveProducts();
		}
	}
}
