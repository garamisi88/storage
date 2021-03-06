package storage.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storage.dao.UserDao;
import storage.datasource.Utils;
import storage.model.User;

/**
 * A UserDao interfészt implementáló osztály.
 * @author Misi
 *
 */
public class UserDaoImpl implements UserDao{

	/**
	 * Az osztályon belül történő események naplózását végző Logger osztály.
	 */
	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	/**
	 * Az osztály EntityManagerFactory-ja.
	 */
	private EntityManagerFactory emf;
		
	/**
	 * Az osztály paraméter nélküli konstruktora, amely beállítja az EntityManagerFactory-t.
	 */
	public UserDaoImpl() {
		this.emf = Utils.getEntityManagerFactory();
	}

	/* (non-Javadoc)
	 * @see storage.dao.UserDao#save(storage.model.User)
	 */
	@Override
	public void save(User user) {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			em.close();
		}
	}

	/* (non-Javadoc)
	 * @see storage.dao.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User userAuth(String username, String password) throws Exception {
		EntityManager em = emf.createEntityManager();
		User user = null;
		try{
			user = em.createQuery("SELECT u FROM User u WHERE u.username = :userName and u.password = :userPass", User.class)
						.setParameter("userName", username)
						.setParameter("userPass", DigestUtils.sha1Hex(password))
						.getSingleResult();
		}catch(NoResultException ex){
			throw new Exception("Hibás felhasználónév vagy jelszó!");
		}finally {
			em.close();
		}
		
		return user;
	}

}
