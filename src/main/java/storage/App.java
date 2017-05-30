package storage;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import storage.datasource.Utils;
import storage.model.Customer;
import storage.model.MyOrder;
import storage.model.Product;
import storage.model.User;
import storage.view.ClosedOrderViewController;
import storage.view.CustomerFormViewController;
import storage.view.OrderFormViewController;
import storage.view.ProductFormViewController;


 /**
 * Az alkalmazás fő controllere.
 * @author Misi
 */
/**
 * @author Misi
 *
 */
public class App extends Application {
	
	/**
	 * Az osztályon belül történő események naplózását végző Logger osztály.
	 */
	private static Logger logger = LoggerFactory.getLogger(App.class); 
	/**
	 * Az alkalmazás Stage-e.
	 */
	private Stage primaryStage;
	
	/**
	 * Az alkalmazás nézete.
	 */
	private BorderPane view;
	
	/**
	 * Az alkalmazást használó user.
	 */
	private User user = null;
	
	/**
	 * Az App osztály példánya.
	 */
	private static App instance;
	
	/**
	 * Az osztály paraméter nélküli konstruktora, amely beállítja az aktuális példányt.
	 */
	public App() {
		instance = this;
	}

	/**
	 * az alkalmazás belépési pontja.
	 * @param args parancssori argunemtumok
	 */
	public static void main(String[] args){
		logger.info("Elindult az alkalmazás!");
		StorageInitialization.setUser();
		logger.info("Inicializálásra kerültek a userek.");
		StorageInitialization.setBaseProducts();
		logger.info("Beszúrtam az első terméket az adatbázisba");
		StorageInitialization.setCustomer();
		logger.info("Létrejött a vásárló.");
		StorageInitialization.setOrder();
		logger.info("Mentésre került az alap rendelés");
		
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Raktár kezelés - Gara Mihály");
	
		primaryStage.setOnCloseRequest(e -> {
			this.closeHandler();
		});
		
		try{
			changeView("LoginView");
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	/**
	 * Az alkalmazást használó User-t beállító metódus.
	 * @param user Az alkalmazást használó User
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	/**
	 * Visszaadja a bejelentkezet User-t.
	 * @return Az alkalmazást használó user
	 */
	public User getUser() {
		return user;
	}
	
	
	
	/**
	 * Visszaadja az alkalmazás stage-ét.
	 * @return Az alkalmazás stage-e
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Visszaadja az alkalmazás aktuális példányát.
	 * @return Az alkalmazás aktuális példánya
	 */
	public static App getInstance() {
		return instance;
	}

	/**
	 * Az alkalmazás nézeteit betöltő metódus.
	 * @param viewFile a nézet fxml fájl neve
	 */
	public void changeView(String viewFile){
		FXMLLoader loader = new FXMLLoader();
		loadView(viewFile, loader);
		
		if(view != null){
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			primaryStage.show();
			logger.info("Nézet váltás történt, az új nézet a "+viewFile+".fxml");
		}
	}
	
	/**
	 * Termék űrlapot betöltő metódus.
	 * @param viewFile A nézet fájl neve
	 * @param product A módosítandó {@link storage.model.Product} objektum.
	 */
	public void showProductFormView(String viewFile, Product product){
		FXMLLoader loader = new FXMLLoader();
		loadView(viewFile, loader);
		
		if(view != null){
			((ProductFormViewController)loader.getController()).setProduct(product);
			
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			
			primaryStage.show();
			logger.info("Nézet váltás történt, az új nézet a "+viewFile+".fxml");
		}
	}
	
	/**
	 * A vásárló űrlapot betöltő metódus.
	 * @param viewFile 	A nézet fájl neve
	 * @param customer	A módosítandó {@link storage.model.Customer} objektum
	 */
	public void showCustomerFormView(String viewFile, Customer customer){
		FXMLLoader loader = new FXMLLoader();
		loadView(viewFile, loader);
		
		if(view != null){
			((CustomerFormViewController)loader.getController()).setCustomer(customer);
			
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			
			primaryStage.show();
			logger.info("Nézet váltás történt, az új nézet a "+viewFile+".fxml");
		}
	}
	
	/**
	 * A rendelés módosító formot betöltő metódus.
	 * @param viewFile A nézetfile neve
	 * @param order A rendelés osztály egy példánya
	 */
	public void showOrderFormView(String viewFile, MyOrder order){
		FXMLLoader loader = new FXMLLoader();
		loadView(viewFile, loader);
		
		if(view != null){
			((OrderFormViewController)loader.getController()).setOrder(order);
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			
			primaryStage.show();
			logger.info("Nézet váltás történt, az új nézet a "+viewFile+".fxml");
		}
	}
	
	/**
	 * A lezárt rendeléseket megjelenítő metódus.
	 * @param viewFile A nézetfájl neve
	 * @param order A megtekinteni kívánt rendelés
	 */
	public void showClosedOrderDatas(String viewFile, MyOrder order){
		FXMLLoader loader = new FXMLLoader();
		loadView(viewFile, loader);
		
		if(view != null){
			((ClosedOrderViewController)loader.getController()).setOrder(order);
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			
			primaryStage.show();
		}
	}
	
	/**
	 * A nézeteket betöltő metódus. 
	 * @param fileName A nézet fxml fájl neve
	 * @param loader Az FXMLLoader osztály egy példánya
	 */
	private void loadView(String fileName, FXMLLoader loader){
		loader.setLocation(App.class.getResource("/views/"+fileName+".fxml"));
		
		try {
			view = (BorderPane)loader.load();	
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	/**
	 * Ez a függvény kezeli le az alkalmazás bezárását.
	 */
	public void closeHandler(){
		Utils.connectionClose();
		System.exit(0);
	}
	
	
}
