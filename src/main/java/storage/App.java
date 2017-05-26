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
import storage.model.Product;
import storage.model.User;
import storage.view.CustomerFormViewController;
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
			showLoginView();
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
	 * Megjeleníti a bejelentkezési ablakot.
	 * @throws IOException
	 */
	private void showLoginView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("/views/LoginView.fxml"));
		
		view = (BorderPane)loader.load();
		
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.show();
		logger.info("Elindult az alkalmazás");
	}
	
	
	/**
	 * Az alkalmazás nézeteit betöltő metódus.
	 * @param viewFile a nézet fxml fájl neve
	 */
	public void changeView(String viewFile){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/views/"+viewFile+".fxml"));
			
			view = (BorderPane)loader.load();
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			primaryStage.show();
			logger.info("Nézet váltás történt, az új nézet a "+viewFile+".fxml");
			
		}catch(IOException e){
			logger.error(e.getMessage());
		}
	}
	
	public void showProductFormView(String viewFile, Product product){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/views/"+viewFile+".fxml"));
			
			view = (BorderPane)loader.load();
			((ProductFormViewController)loader.getController()).setProduct(product);
			
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			
			primaryStage.show();
			logger.info("Nézet váltás történt, az új nézet a "+viewFile+".fxml");
			
		}catch(IOException e){
			logger.error(e.getMessage());
		}
	}
	
	public void showCustomerFormView(String viewFile, Customer customer){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/views/"+viewFile+".fxml"));
			
			view = (BorderPane)loader.load();
			((CustomerFormViewController)loader.getController()).setCustomer(customer);
			
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			
			
			
			primaryStage.show();
			logger.info("Nézet váltás történt, az új nézet a "+viewFile+".fxml");
			
		}catch(IOException e){
			e.printStackTrace();
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
