package storage.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
			em.getTransaction().begin();
			em.persist(customer);
			em.getTransaction().commit();
		}catch(Exception e){
			
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
			
		}finally{
			em.close();
		}
		return customer;
	}

}
