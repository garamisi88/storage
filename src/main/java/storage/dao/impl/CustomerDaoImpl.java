package storage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storage.dao.CustomerDao;
import storage.datasource.Utils;
import storage.model.Customer;

/**
 * A {@link storage.dao.CustomerDao} intérfészt implementáló osztály.
 * @author Misi
 *
 */
public class CustomerDaoImpl implements CustomerDao{

	/**
	 * Az osztályon belül történő események naplózását végző Logger osztály.
	 */
	private Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);
	
	/**
	 * Az osztály EntityManagerFactory-ja, amelytől az EntityManager származni fog.
	 */
	private EntityManagerFactory emf;
	
	/**
	 * Az osztály paraméter nélküli konstruktora, amely beállítja az EntityManagerFactory-t. 
	 */
	public CustomerDaoImpl() {
		this.emf = Utils.getEntityManagerFactory();
	}

	/* (non-Javadoc)
	 * @see storage.dao.CustomerDao#save(storage.model.Customer)
	 */
	@Override
	public void save(Customer customer) {
		EntityManager em = emf.createEntityManager();
		try{
			System.out.println(customer.getName());
			em.getTransaction().begin();
			em.persist(customer);
			em.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
	}

	/* (non-Javadoc)
	 * @see storage.dao.CustomerDao#update(storage.model.Customer)
	 */
	@Override
	public void update(Customer customer) {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(customer);
			em.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}	
	}

	/* (non-Javadoc)
	 * @see storage.dao.CustomerDao#get(int)
	 */
	@Override
	public Customer get(int id) {
		EntityManager em = emf.createEntityManager();
		Customer customer = null;
		try{
			customer = em.find(Customer.class, id);
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
		return customer;
	}

	@Override
	public List<Customer> getAll() {
		EntityManager em = emf.createEntityManager();
		List<Customer> list = null;
		
		try{
			TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
			list = query.getResultList();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
		
		return list;
	}

}
