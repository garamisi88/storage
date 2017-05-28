package storage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storage.dao.ProductDao;
import storage.datasource.Utils;
import storage.model.Product;

/**
 * A Product DAO interfészt implementáló osztály.
 * @author Misi
 *
 */
public class ProductDaoImpl implements ProductDao{
	
	/**
	 * Az osztályon belül történő események naplózását végző Logger osztály.
	 */
	private Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	/**
	 * Az osztály EntityManagerFactory-ja.
	 */
	private EntityManagerFactory emf;
	
	/**
	 * Az osztály paraméter nélküli konstruktora, amely beállítja az EntityManagerFactory-t.
	 */
	public ProductDaoImpl() {
		this.emf = Utils.getEntityManagerFactory();
	}

	
	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#save(storage.model.Product)
	 */
	@Override
	public void save(Product product) {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
	}

	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#update(storage.model.Product)
	 */
	@Override
	public void update(Product product) {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(product);
			em.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
	}

	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#getAll()
	 */
	@Override
	public List<Product> getAll() {
		EntityManager em = emf.createEntityManager();
		List<Product> list = null;
		try{
			TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
			list = query.getResultList();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#getOrderableProducts()
	 */
	@Override
	public List<Product> getOrderableProducts() {
		EntityManager em = emf.createEntityManager();
		List<Product> list = null;
		try{
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.quantity < p.minimumQuantity", Product.class);
			list = query.getResultList();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();	
		}
		 
		return list;
	}

	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#getWasteProducts()
	 */
	@Override
	public List<Product> getWasteProducts() {
		EntityManager em = emf.createEntityManager();
		List<Product> list = null;
		try{
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.expiryDate < now()", Product.class);
			list = query.getResultList();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
		return list;
	}


	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#get(int)
	 */
	@Override
	public Product get(int id) {
		EntityManager em = emf.createEntityManager();
		Product product = null;
		try{
			product = em.find(Product.class, id);
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
		return product;
	}


	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#remove(storage.model.Product)
	 */
	@Override
	public void remove(Product product) {
		EntityManager em = emf.createEntityManager();
		
		try{em.getTransaction().begin();
			em.remove(product);
			em.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
	}


	/* (non-Javadoc)
	 * @see storage.dao.ProductDao#getActiveProducts()
	 */
	@Override
	public List<Product> getActiveProducts() {
		EntityManager em = emf.createEntityManager();
		List<Product> list = null;
		
		try{
			TypedQuery<Product> query = em.createQuery("select p from Product p where p.expiryDate >= now() or p.expiryDate IS NULL", Product.class);
			list = query.getResultList();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
		
		return list;
	}

}
