package storage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A felhasználókat reprezentáló osztály.
 * @author Misi
 *
 */
@Entity
public class User {
	
	/**
	 * A felhasználó id-ja.
	 */
	private Long id;
	
	/**
	 * A felhasználó neve.
	 */
	private String name;
	
	/**
	 * A user felhasználóneve.
	 */
	private String username;
	
	/**
	 * A felhasználó jelszava.
	 */
	private String password;

	/**
	 * A felhasználó id-ját visszaadó metódus.
	 * @return a felhasználó id-ja.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * A felhasználó id-ját beállító metódus.
	 * @param id A felhasználó id-ja
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * A felhasználó nevét visszaadó metódus.
	 * @return A felhasználó neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * A felhasználó nevét beállító metódus.
	 * @param name A felhasználó neve
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * A user felhasználónevét visszaadó metódus.
	 * @return A user felhasználóneve
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * A user felhasználónevét beállító metódus.
	 * @param username A felhasználónév
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * A felhasználó jelszavát visszaadó metódus.
	 * @return A felhasználó jelszava
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * A felhasználó jelszabát beállító metódus.
	 * @param password A felhasználó jelszava
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + "]";
	}
	
	
	
	
}
