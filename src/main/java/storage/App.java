package storage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import storage.datasource.Utils;
import storage.model.User;


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
	public static void main(String[] args) {
		StorageInitialization.setUser();
		StorageInitialization.setBaseProducts();
		StorageInitialization.setCustomer();
		StorageInitialization.setOrder();
		
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
			e.printStackTrace();
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
	}
	
	/**
	 * Az alkalmazás fő nézetét betöltő metódus.
	 */
	public void changeView(String viewFile){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/views/"+viewFile+".fxml"));
			
			view = (BorderPane)loader.load();
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}catch(IOException e){
			
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
