package storage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import storage.dao.ProductDao;
import storage.datasource.ConnectionHandler;
import storage.model.Product;

/**
 * A Product DAO interfészt implementáló osztály
 * @author Misi
 *
 */
public class ProductDaoImpl implements ProductDao{
	
	/**
	 * Az osztály EntityManagerFactory-ja.
	 */
	private EntityManagerFactory emf;
	
	/**
	 * Az osztály paraméter nélküli konstruktora, amely beállítja az EntityManagerFactory-t.
	 */
	public ProductDaoImpl() {
		this.emf = ConnectionHandler.getHandler().getEntityManagerFactory();
	}

	
	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#save(storage.model.Product)
	 */
	public void save(Product product) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#update(storage.model.Product)
	 */
	public void update(Product product) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(product);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#getAll()
	 */
	public List<Product> getAll() {
		EntityManager em = emf.createEntityManager();
		List<Product> list = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
		em.close();
		return list;
	}

	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#getOrderableProducts()
	 */
	public List<Product> getOrderableProducts() {
		EntityManager em = emf.createEntityManager();
		List<Product> list = em.createQuery("SELECT p FROM Product p WHERE p.quantity < p.minimumQuantity", Product.class).getResultList();
		em.close();
		return list;
	}

	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#getWasteProducts()
	 */
	public List<Product> getWasteProducts() {
		EntityManager em = emf.createEntityManager();
		List<Product> list = em.createQuery("SELECT p FROM Product p WHERE p.expiryDate < now()", Product.class).getResultList();
		em.close();
		return list;
	}


	public Product get(Long id) {
		EntityManager em = emf.createEntityManager();
		Product product = null;
		try{
			product = em.find(Product.class, id);
		}catch(Exception e){
			
		}finally{
			em.close();
		}
		return product;
	}

}
