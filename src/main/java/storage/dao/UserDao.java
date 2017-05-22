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
	 * @param user A mentendő {@link storage.model.User} objectum
	 */
	void save(User user);
	
	/**
	 * A felhasználót megtaláló függvény.
	 * @param username A user felhasználóneve ({@link storage.model.User})
	 * @param password A user jelszava
	 * @return TODO Visszatér a talált {@link storage.model.User} objektummal
	 * @throws Exception Abban esetben, ha nem sikerül authentikálni a felhasználót
	 */
	User userAuth(String username, String password) throws Exception;
}
