package it.unical.mat.igpe17.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.unical.mat.igpe17.gui.GameManager;

public class LevelHandler {
	
	GameManager gameManager;
	
	public LevelHandler() {
	}

	public void save(int lvl) throws FileNotFoundException, IOException{
		
		try {

			FileOutputStream fileOut = new FileOutputStream("level" + lvl + ".sav");
			ObjectOutputStream outStream = new ObjectOutputStream(fileOut);

			outStream.writeObject(gameManager);
			outStream.close();
			fileOut.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	public GameManager load(int lvl){
		
		GameManager gm;

		try {

			FileInputStream fileIn = new FileInputStream("level" + lvl + ".sav");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			gm = (GameManager) in.readObject();
			if(gm == null)
			System.out.println("A");

			in.close();

			fileIn.close();

		} catch (IOException i) {
			i.printStackTrace();
			return null;

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		}

		return gm;
	}

}
