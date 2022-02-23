package it.unical.mat.igpe17.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class HighscoreManager {
	// The name of the file where the highscores will be saved

	private static final String HIGHSCORE_FILE = "scores.dat";
	final static int max = 10;
	private boolean newHighScore = false;

	ObjectInputStream inputStream = null;
	ObjectOutputStream outputStream = null;

	private ArrayList<PlayerData> scores;

	public HighscoreManager() {
		
		scores = new ArrayList<PlayerData>();
	}

	public void addScore(PlayerData pd) {
		loadScoreFile();

		scores.add(new PlayerData(pd.getPlayer(), pd.getScore(), pd.getScoreLastLevel(), pd.getLevel()));
		sort();

		if (scores.size() > 0) {
			PlayerData tmp = scores.get(0);
			if (tmp.getPlayer() == pd.getPlayer() && tmp.getScore() == pd.getScore())
				newHighScore = true;
		} else
			newHighScore = true;

		updateScoreFile();
	}

	public ArrayList<PlayerData> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}

	public boolean isNewHighScore() {
		return newHighScore;
	}

	@SuppressWarnings("unchecked")
	public void loadScoreFile() {

		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			scores = (ArrayList<PlayerData>) inputStream.readObject();
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

	public void setNewHighScore(boolean newHighScore) {
		this.newHighScore = newHighScore;
	}

	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}

	public void updateScoreFile() {

		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(scores);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage());
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