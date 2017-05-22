package storage.datasource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Ez az osztály kezeli a kapcsolatot az adatbázissal.
 * @author Misi
 *
 */
public class Utils {
	
	/**
	 * A DAO osztályokban ettől az EntityManagerFactorytől fog származni az EntityManager.
	 */
	private static final EntityManagerFactory entityManagerFactory;
	
	/**
	 * A persistence.xml-ben definiált unit name attribútuma.
	 */
	private static final String persistenceUnitName = "progtechStorage";

	
	/**
	 * Az osztály statikus konstruktora, amely beállítja az EntityManagerFactory-t.
	 */
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		
	}

	/**
	 * Az EntityManagerFactory-t visszaadó metódus.
	 * @return Az EntityManagerFactory
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	/**
	 * Lezárja a kapcsolatot, amennyiben az nyitva van.
	 */
	public static void connectionClose(){
		if(entityManagerFactory.isOpen())
			entityManagerFactory.close();
	}
}
