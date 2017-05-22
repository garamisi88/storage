package storage.datasource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Ez az osztály kezeli a kapcsolatot az adatbázissal.
 * @author Misi
 *
 */
public class ConnectionHandler {
	
	/**
	 * A DAO osztályokban ettől az EntityManagerFactorytől fog származni az EntityManager.
	 */
	private EntityManagerFactory entityManagerFactory;
	
	/**
	 * A persistence.xml-ben definiált unit name attribútuma.
	 */
	private final String persistenceUnitName = "progtechStorage";

	/**
	 * Az osztály egy példánya, amelyet a DAO osztályok használnak.
	 */
	private static ConnectionHandler handler = new ConnectionHandler();
	
	/**
	 * Az osztály alapértelmezett konstruktora, amely beállítja az EntityManagerFactory-t.
	 */
	public ConnectionHandler() {
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	/**
	 * Az EntityManagerFactory-t visszaadó metódus.
	 * @return Az EntityManagerFactory
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	/**
	 * Lezárja a kapcsolatot, amennyiben az nyitva van.
	 */
	public void connectionClose(){
		if(entityManagerFactory.isOpen())
			entityManagerFactory.close();
	}
	
	/**
	 * Vissza adja az osztály egy példányát.
	 * @return az osztály egy példánya
	 */
	public static ConnectionHandler getHandler(){
		return handler;
	}
}
