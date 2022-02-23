package it.unical.mat.igpe17.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ProfilesManager {

	final static int max = 10;
	private static final String PROFILES_FILE = "profiles.dat";
	
	ObjectInputStream inputStream = null;
	ObjectOutputStream outputStream = null;

	private ArrayList<PlayerData> profiles;

	public ProfilesManager() {

		profiles = new ArrayList<PlayerData>();
	}

	public void addProfile(PlayerData pd) {

		loadProfileFile();

		profiles.add(new PlayerData(pd.getPlayer(), pd.getScore(), pd.getScoreLastLevel(), pd.getLevel()));

		updateProfileFile();
	}

	public ArrayList<PlayerData> getProfiles() {
		loadProfileFile();
		return profiles;
	}

	@SuppressWarnings("unchecked")
	public void loadProfileFile() {

		try {

			inputStream = new ObjectInputStream(new FileInputStream(
					PROFILES_FILE));

			profiles = (ArrayList<PlayerData>) inputStream.readObject();

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] IO Error: " + e.getMessage());
			}
		}

	}

	public void removeProfile(int i) {

		loadProfileFile();

		profiles.remove(i);

		updateProfileFile();

	}

	public void updateProfile(int i, PlayerData pd) {

		loadProfileFile();

		profiles.add(i, pd);

		updateProfileFile();

	}

	public void updateProfileFile() {

		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(
					PROFILES_FILE));
			outputStream.writeObject(profiles);

		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage()
					+ ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}

	}

}
