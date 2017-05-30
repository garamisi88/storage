package storage.model;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A vásárlók címét reprezentáló osztály.
 * @author Misi
 *
 */
@Embeddable
@XmlRootElement(name="address")
public class Address {
	
	/**
	 * A címhez tartozó irányítószám.
	 */
	private String zip;
	
	/**
	 * A címhez tartozó település.
	 */
	private String city;
	
	/**
	 * A címhez tartozó utca, illetve a házszám.
	 */
	private String street;
	
	/**
	 * A címhez tartozó ország.
	 */
	private String country;

	/**
	 * Az osztály paraméter nélküli kostruktora.
	 */
	public Address() {
	}

	/**
	 * Az osztály paraméteres konstruktora.
	 * @param zip irányítószám
	 * @param city település neve
	 * @param street utca, házszám
	 * @param country ország
	 */
	public Address(String zip, String city, String street, String country) {
		this.zip = zip;
		this.city = city;
		this.street = street;
		this.country = country;
	}

	/**
	 * Az irányítószámot visszaadó metódus.
	 * @return az irányítószám
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Az irányítószámot beállító metódus.
	 * @param zip az irányítószám
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * A település nevét visszaadó metódus.
	 * @return a település neve
	 */
	public String getCity() {
		return city;
	}

	/**
	 * A települést beállító metódus.
	 * @param city A település neve
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Az utca mező értékét visszadó metódus.
	 * @return az utca, házszám
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Az utcát beállító metódus.
	 * @param street utca, házszám mező értéke
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Az országot visszaadó metódus.
	 * @return az ország neve
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Az országot beállító metódus.
	 * @param country az ország
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s %s %s, %s", country, zip, city, street));
		
		return sb.toString();
	}
	
	
	
	
}
