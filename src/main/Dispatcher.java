package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import controller.JSONController;
import controller.SQLController;
import controller.SerializationController;
import domain.Article;
import domain.Magazine;

public class Dispatcher {

	private SQLController sqlController;
	private String pathToFiles;
	private List<Magazine> magazinesFromJSON;
	private List<Magazine> magazinesFromSerFile;
	private List<Magazine> magazinesFromDB;

	public Dispatcher(String pathToWorkDirectory) {
		sqlController = new SQLController();
		pathToFiles = pathToWorkDirectory;
	}
	
	public void getMagazinesFromJSON() {
		magazinesFromJSON = JSONController.readJSON(pathToFiles + "magazines.json");
	}
	
	public void serializeMagazines() {
		SerializationController.serializeMagazines(pathToFiles + "magazines.ser", magazinesFromJSON);
	}
	
	public void deserializeMagazines() {
		magazinesFromSerFile = SerializationController.deserializeMagazines(pathToFiles + "magazines.ser");
	}
	
	public void displayMagazines(List<Magazine> magazines) {
		for(Magazine m : magazines) {
			System.out.println(m);
		}
	}
	
	public void getMagazinesFromDB() {
		//	1. Connect to MySQL database
		//	1 param = database name, 2 param = user name, 3 = user password for database
		sqlController.createDBConnection("cnudb", "root", "dbpassword");
		
		//	2. Get magazines from MySQL database
		magazinesFromDB = sqlController.getMagazines();
		
		//	3. Disconnect MySQL database
		sqlController.closeDBConnection();
	}
	
	public void sortMagazines(List<Magazine> magazines) {
		Collections.sort(magazines);
	}
	
	public void sortArticles(List<Magazine> magazines) {
		for(Magazine magazine : magazines) {
			Collections.sort(magazine.getArtciles());
		}
	}
	
	public static void main(String[] args) {
		
		Dispatcher dispatcher = new Dispatcher("C:\\Users\\Іван\\Desktop\\cnu-java\\src\\");
		
		System.out.println("\n\n\n- - - - - - - - - Work with JSON file - - - - - - - - -\n");

		//	1. Get Magazines and Articles lists from JSON file (pathToFile + "magazines.json") file, put this list to magazinesFromJSON variable
		dispatcher.getMagazinesFromJSON();
		
		//	2. Display magazinesFromJSON variable
		dispatcher.displayMagazines(dispatcher.magazinesFromJSON);
		
		System.out.println("\n\n\n- - - - - - - - - Work with serialize and deserialize - - - - - - - - -\n");
		
		//	3. Serialize magazinesFromJSON variable to (pathToFile + "magazines.ser") file
		dispatcher.serializeMagazines();
		
		//	4. De-Serialize magazines from (pathToFile + "magazines.ser") file, and put magazines list to magazinesFromSerFile variable
		dispatcher.deserializeMagazines();
		
		//	5. Display magazinesFromSerFile variable
		dispatcher.displayMagazines(dispatcher.magazinesFromSerFile);
		
		System.out.println("\n\n\n- - - - - - - - - Magazines from MySQL database - - - - - - - - -\n");

		//	6. Connect, get magazines list, put magazines list to magazinesFromDB variable
		dispatcher.getMagazinesFromDB();
		
		//	7. Display magazinesFromDB variable
		dispatcher.displayMagazines(dispatcher.magazinesFromDB);
		
		System.out.println("\n\n\n- - - - - - - - - Sorting magazines and articles from magazinesFromJSON variable by name - - - - - - - - -\n");

		//	8. Sort magazinesFromJSON list by name
		dispatcher.sortMagazines(dispatcher.magazinesFromJSON);
		
		//	9. Sort articles for magazineFromJSON variable
		dispatcher.sortArticles(dispatcher.magazinesFromJSON);

		//	9. Display magazinesFromJSON variable after sorting
		dispatcher.displayMagazines(dispatcher.magazinesFromJSON);
		
	}

}
