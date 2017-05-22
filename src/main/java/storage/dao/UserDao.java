package storage.dao;

import storage.model.User;

/**
 * A felhasználókat kezelő DAO interfész.
 * @author Misi
 *
 */
public interface UserDao {
	
	/**
	 * A felhasználó mentését végző metódus.
	 * @param user A mentendő User objectum
	 */
	void save(User user);
	
	/**
	 * A felhasználót megtaláló függvény.
	 * @param username A user felhasználóneve
	 * @param password A user jelszava
	 * @return TODO
	 * @throws Exception 
	 */
	User userAuth(String username, String password) throws Exception;
}
