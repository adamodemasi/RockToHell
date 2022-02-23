package it.unical.mat.igpe17.gui;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import it.unical.mat.igpe17.elements.Direction;
import it.unical.mat.igpe17.elements.Goal;
import it.unical.mat.igpe17.elements.Note;
import it.unical.mat.igpe17.elements.Platform;
import it.unical.mat.igpe17.elements.Player;
import it.unical.mat.igpe17.elements.Spike;
import it.unical.mat.igpe17.elements.Wall;

public class GameManager implements Serializable{
	private static final long serialVersionUID = 5749839763240352430L;

	private ArrayList<Note> notes;
	private ArrayList<Note> notes_copy;
	private ArrayList<Platform> platforms;
	private ArrayList<Spike> spikes;
	private ArrayList<Wall> walls;
	private Goal goal;
	private Player james;

	WindowFrame window;
	
	public GameManager() {
		
		setNotes(new ArrayList<Note>());
		notes_copy = new ArrayList<Note>();
		setPlatforms(new ArrayList<Platform>());
		setSpikes(new ArrayList<Spike>());
		setWalls(new ArrayList<Wall>());
		setJames(new Player());
		
	}

	public void tick(){
		
		james.tick();
		
		for(int i = 0; i < getPlatforms().size(); i++){
			Platform tmp = getPlatforms().get(i);
			tmp.tick();
		}
		
		collision();
	}

	private void platformManager(Color color) {
		for(int i = 0; i < getPlatforms().size(); i++)
			if( (getPlatforms().get(i).getColor() ).equals(color) )
				getPlatforms().get(i).setMove(true);
			else getPlatforms().get(i).setMove(false);
	}

	private void collision(){
		
		if(james.getBoundsTop().intersects(getGoal().getBounds()) || james.getBounds().intersects(getGoal().getBounds()) ||	// FINE LIVELLO!
				james.getBoundsRight().intersects(getGoal().getBounds()) || james.getBoundsLeft().intersects(getGoal().getBounds())){

			// CREARE SCHERMATA DI FINE LIVELLO
			MainGame.frame.getGame().setRunning();
			MainGame.frame.endLevelMenu();
		}

// ---------------- NOTES COLLISIONS --------------------//
		for(int i = 0; i < getNotes().size(); i++){
			Note tmp = getNotes().get(i);
				if(james.getBounds().intersects(tmp.getBounds()) || (james.getBoundsTop().intersects(tmp.getBounds()) ) ||
					(james.getBoundsRight().intersects(tmp.getBounds()) ) || (james.getBoundsLeft().intersects(tmp.getBounds()) ) ){

					if((tmp.getColor()).equals(Color.BLUE) ) platformManager(Color.BLUE);
					else if( (tmp.getColor()).equals(Color.RED) ) platformManager(Color.RED);
					else if((tmp.getColor()).equals(Color.GREEN) ) platformManager(Color.GREEN);

					getNotes().addAll(notes_copy);
					notes_copy.clear();
					notes_copy.add(tmp);								//store the note just taken 
					removeNote(getNotes().get(i));
				}

		}

// ---------------- WALLS COLLISIONS --------------------//
		for(int i = 0; i < getWalls().size(); i++){
			Wall tmp = getWalls().get(i);

				if(james.getBounds().intersects(tmp.getBounds())){
					james.setY((float) (tmp.getY() - Player.height));
					if(james.isJumping() || james.isGliding()){
						james.setDir(Direction.STOPDX);
						james.setGliding(false);
					}
//					if(james.isJumping() || james.isGliding()){
//						if(james.getDir() == Direction.UPDX || james.getDir() == Direction.FLYDX)
//							james.setDir(Direction.STOPDX);
//						else if(james.getDir() == Direction.UPSX || james.getDir() == Direction.FLYSX)
//							james.setDir(Direction.STOPSX);
//						james.setGliding(false);
//					}
					
					james.setVelY(0);
					james.setFalling(false);
					james.setJumping(false);
//					james.setDir(Direction.STOP);
				}
				else james.setFalling(true);

				if(james.getBoundsTop().intersects(tmp.getBounds())){

					james.setY((float) (tmp.getY() + tmp.getBounds().height));
					james.setVelY(0.5f);
				}

				if(james.getBoundsRight().intersects(tmp.getBounds()))
					james.setX(tmp.getX() - Player.width);

				if(james.getBoundsLeft().intersects(tmp.getBounds()))
					james.setX((float)(tmp.getX() + tmp.getBounds().getWidth()));
//				if(james.isJumping() || james.isGliding()){
//					if(james.getDir() == Direction.UPDX || james.getDir() == Direction.FLYDX)
//						james.setDir(Direction.STOPDX);
//					else if(james.getDir() == Direction.UPSX || james.getDir() == Direction.FLYSX)
//						james.setDir(Direction.STOPSX);
//				}
		}

// ---------------- PLATFORMS COLLISIONS --------------------//
		for(int i = 0; i < getPlatforms().size(); i++){
			Platform tmp = getPlatforms().get(i);
				
				if(james.getBounds().intersects(tmp.getBounds())){
					if(james.isJumping() || james.isGliding()){
						james.setDir(Direction.STOPDX);
						
						james.setGliding(false);
					}
					
					james.setY((float) (tmp.getY() - Player.height));

					james.setVelY(1f);
					james.setFalling(true);
					james.setJumping(false);
				}
//				else falling = true;
				
				else if(james.getBoundsTop().intersects(tmp.getBounds())){
					james.setY((float)(tmp.getY() + tmp.getBounds().height));
					james.setVelY(1f);
				}
				
				else if(james.getBoundsRight().intersects(tmp.getBounds()))
					james.setX(tmp.getX() - Player.width);

				
				else if(james.getBoundsLeft().intersects(tmp.getBounds()))
					james.setX( (float)(tmp.getX() + tmp.getBounds().getWidth()) );

		}																					//end platforms collisions

// ---------------- SPIKES COLLISIONS --------------------//
		for(int i = 0; i < getSpikes().size(); i++){
			Spike tmp = getSpikes().get(i);

				if(james.getBounds().intersects(tmp.getBounds())){
					if(james.isJumping() || james.isGliding()){
						james.setDir(Direction.STOPDX);
						james.setGliding(false);
					}
					james.setY((float) (tmp.getY() - Player.height));
					james.setVelY(0);
					james.setFalling(false);
					james.setJumping(false);
					james.decrLife();											//spike collision causing losing a life
				}
				else james.setFalling(true);

		}

		if(james.getX() < 0) james.setX(0); 									//sx limit

		if(james.getY() < 0) { 													// up limit
			james.setY(0);
			james.setVelY(0.5f);
		}

//		if(james.getY() > (GamePanel.HEIGTH - Player.height) ){
//			james.setY(GamePanel.HEIGTH - Player.height); 							// down limit - player dies
//			james.decrLife();
//		}
	}
	
	public void addNote(Note note){
		this.getNotes().add(note);
	}
	
	public void removeNote(Note note){
		this.getNotes().remove(note);
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

	public ArrayList<Spike> getSpikes() {
		return spikes;
	}

	public void setSpikes(ArrayList<Spike> spikes) {
		this.spikes = spikes;
	}

	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(ArrayList<Platform> platforms) {
		this.platforms = platforms;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
	
	public Player getJames() {
		return james;
	}

	public void setJames(Player james) {
		this.james = james;
	}
	
}
