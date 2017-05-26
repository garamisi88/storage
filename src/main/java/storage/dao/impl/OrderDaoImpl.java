package storage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storage.dao.OrderDao;
import storage.datasource.Utils;
import storage.model.MyOrder;

public class OrderDaoImpl implements OrderDao {
	
	/**
	 * Az osztályon belül történő események naplózását végző Logger osztály.
	 */
	private Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
	
	
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
	@Override
	public void save(MyOrder order){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(order);
			em.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see storage.dao.OrderDao#getAll()
	 */
	@Override
	public List<MyOrder> getAll(){
		EntityManager em = emf.createEntityManager();
		List<MyOrder> list = null;
		
		try{
			TypedQuery<MyOrder> query = em.createQuery("SELECT o from MyOrder o", MyOrder.class);
			list = query.getResultList();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
		return list;
	}

	@Override
	public void update(MyOrder order) {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(order);
			em.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
	}
}
