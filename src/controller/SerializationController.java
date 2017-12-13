package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import domain.Magazine;

public class SerializationController {
	
	public static boolean serializeMagazines(String pathToOutputFile, List<Magazine> magazines) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathToOutputFile));
			out.writeObject(magazines);
			out.close();
			      
			return true;
		} catch (IOException i) {
			i.printStackTrace();
		}
		return false;
	}

	public static List<Magazine> deserializeMagazines(String pathToInputFile) {
		List<Magazine> arraylist= new ArrayList<Magazine>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToInputFile));
			arraylist = (ArrayList<Magazine>) ois.readObject();
			ois.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}
        return arraylist;
	}
	
}
