package storage.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import storage.datasource.LocalDateHelper;

/**
 * A termékeket leíró osztály.
 * @author Misi
 * 
 */
@Entity
@XmlRootElement(name="product")
public class Product {

	/**
	 * A termék id-ja.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;
	
	/**
	 * A termék raktározási száma.
	 */
	private String sku;
	
	/**
	 * A termék neve.
	 */
	private String name;
	
	/**
	 * A termék ára.
	 */
	private float price;
	
	/**
	 * A termék bekerülési ára.
	 */
	private float entryPrice;
	
	/**
	 * A termékből rendelkezésre álló készlet.
	 */
	private int quantity;
	
	/**
	 * A termék optimális készlete.
	 */
	@Column(nullable=true)
	private int optimalQuantity;
	
	/**
	 * A termék mininmális készlete.
	 */
	@Column(nullable=true)
	private int minimumQuantity;
	
	/**
	 * A termék lejárati dátuma.
	 */
	private LocalDate expiryDate;
	
	/**
	 * Az osztály paraméter nélküli konstruktora.
	 */
	public Product() {
	}
	
	/**
	 * Az osztály paraméteres konstruktora.
	 * @param id				A termék id-ja
	 * @param sku				A termék raktározási száma
	 * @param name				A termék neve
	 * @param price				A termék alapára
	 * @param quantity			A termékből készleten lévő mennyiség
	 * @param optimalQuantity	A termék optimális készlete
	 * @param minimumQuantity	A termék minimum készlete
	 * @param expiryDate		A termék lejárati dátuma
	 * @param entryPrice		A termék bekerülési ára
	 */
	public Product(int id, String sku, String name, float price, int quantity, int optimalQuantity,
			int minimumQuantity, LocalDate expiryDate, float entryPrice) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.optimalQuantity = optimalQuantity;
		this.minimumQuantity = minimumQuantity;
		this.expiryDate = expiryDate;
		this.entryPrice = entryPrice;
	}



	/**
	 * Visszaadja a termék id-ját.
	 * @return A termék id-ja
	 */
	@XmlAttribute
	public int getId() {
		return id;
	}

	/**
	 * Beállítja a termék id-ját.
	 * @param id A termék id-ja
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Visszaadja a termék raktározási számát.
	 * @return A termék raktározási száma
	 */
	@XmlElement(name="sku")
	public String getSku() {
		return sku;
	}

	/**
	 * A termék raktározási számát beállító metódus.
	 * @param sku A termék raktározási száma
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * A termék nevét visszaadó metódus.
	 * @return A termék neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * A termék nevét beállító metódus.
	 * @param name A termék neve
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Visszaadja a termék alapárát.
	 * @return A termék alapára
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Beállítja a termék árát.
	 * @param price A termék ára
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * A termékből elérhető készletet visszaadó függvény.
	 * @return A termékből raktáron lévő készlet
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Beállítja a raktáron lévő készletet.
	 * @param quantity A raktáron lévő mennyiség
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Visszaadja a termék optimális készletét.
	 * @return Optimális készlet
	 */
	public int getOptimalQuantity() {
		return optimalQuantity;
	}

	/**
	 * Beállítja a termék optimális készletét.
	 * @param optimalQuantity A termék optimális készlete
	 */
	public void setOptimalQuantity(int optimalQuantity) {
		this.optimalQuantity = optimalQuantity;
	}

	/**
	 * Visszaadja a termék minimális készletét.
	 * @return A termék minimális készlete
	 */
	public int getMinimumQuantity() {
		return minimumQuantity;
	}

	/**
	 * Beállítja a termék minimális készletét.
	 * @param minimumQuantity a termék minimális készlete
	 */
	public void setMinimumQuantity(int minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}

	/**
	 * Visszaadja a termék lejáratának dátumát.
	 * @return A termék lejárati dátuma
	 */
	@XmlJavaTypeAdapter( value = LocalDateHelper.class )
	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	/**
	 * A termék lejárati dátumát beállító metódus.
	 * @param expiryDate A termék lejárati ideje.
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
	/**
	 * A termék bekerülési árát visszaadó metódus.
	 * @return a termék bekerülési ára
	 */
	public float getEntryPrice() {
		return entryPrice;
	}

	/**
	 * A termék bekerülési árát beállító metódus.
	 * @param entryPrice a termék bekerülési ára
	 */
	public void setEntryPrice(float entryPrice) {
		this.entryPrice = entryPrice;
	}

	@Override
	public String toString() {
		return this.name+" ("+this.sku+")";
	}
}
