package it.unical.mat.igpe17.common;

import java.io.Serializable;

public class PlayerData implements Serializable {
	private static final long serialVersionUID = 4191910034735049220L;
	
	
	private int level;
	private int livesLeft;
	private String player;
	private int score;
	private int scoreLastLevel;

	public PlayerData() { }

	public PlayerData(String player, int score, int scoreLastLevel, int level) {
		this.player = player;
		this.score = score;
		this.scoreLastLevel = scoreLastLevel;
		this.setLevel(level);
	}

	public int getLevel() {
		return level;
	}

	public int getLivesLeft() {
		return livesLeft;
	}

	public String getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}

	public int getScoreLastLevel() {
		return scoreLastLevel;
	}

	public void loadPlayer(PlayerData p) {
		this.player = p.player;
		this.score = p.scoreLastLevel;
		this.level = p.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setScoreLastLevel(int scoreLastLevel) {
		this.scoreLastLevel = scoreLastLevel;
	}

}
