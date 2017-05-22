package storage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


import storage.dao.OrderDao;
import storage.datasource.Utils;
import storage.model.MyOrder;

public class OrderDaoImpl implements OrderDao {
	
	
	/**
	 * Az osztály EntityManagerFactory-ja, amelytől az EntityManager származni fog.
	 */
	private EntityManagerFactory emf;
	
	/**
	 * Az osztály paraméter nélküli konstruktora, amely beállítja az EntityManagerFactory-t.
	 */
	public OrderDaoImpl() {
		this.emf = Utils.getEntityManagerFactory();
	}

	/* (non-Javadoc)
	 * @see storage.dao.OrderDao#save(storage.model.MyOrder)
	 */
	public void save(MyOrder order){
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(order);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see storage.dao.OrderDao#getAll()
	 */
	public List<MyOrder> getAll(){
		EntityManager em = emf.createEntityManager();
		List<MyOrder> list = null;
		
		try{
			TypedQuery<MyOrder> query = em.createQuery("SELECT o from MyOrder o", MyOrder.class);
			list = query.getResultList();
		}catch(Exception e){
			
		}finally{
			em.close();
		}
		return list;
	}
}
