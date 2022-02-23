package it.unical.mat.igpe17.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import it.unical.mat.igpe17.elements.Direction;
import it.unical.mat.igpe17.elements.Player;

public class KeyInput extends KeyAdapter {

	static final int speed = 5;
	
	GameManager gameManager;
	GamePanel panel;
	WindowFrame frame;
	Player player;

	public KeyInput(GameManager gm, GamePanel gp, WindowFrame wf) {
		gameManager = gm;
		panel = gp;
		frame = wf;
	}

	public void keyPressed(KeyEvent e){

		int key = e.getKeyCode();
		player = gameManager.getJames();
		
		if(key == KeyEvent.VK_W){

			if( !(player.isJumping()) ){
				player.setDir(Direction.UP);
				
				if(player.getDir() == Direction.RIGHT)
					player.setDir(Direction.UPDX);
				else if(player.getDir() == Direction.LEFT)
					player.setDir(Direction.UPSX);

				player.setJumping(true);
				player.setVelY(-13);
				
			}
		}
		
		if(key == KeyEvent.VK_D){
			player.setVelX(speed);
			if(!player.isJumping())
				player.setDir(Direction.RIGHT);
			else if(player.isJumping())
				player.setDir(Direction.UPDX);

		}

		if(key == KeyEvent.VK_A){
			player.setVelX(-speed);
			if(player.isJumping() == false)
				player.setDir(Direction.LEFT);
			if(player.isJumping() == true)
				player.setDir(Direction.UPSX);
		}



		if(key == KeyEvent.VK_E){
//			if(player.isJumping() || player.isFalling())
				player.setGliding(true);
			
				if(player.getDir().equals(Direction.RIGHT) || player.getDir().equals(Direction.UPDX))
					player.setDir(Direction.FLYDX);
				if(player.getDir().equals(Direction.LEFT) || player.getDir().equals(Direction.UPSX))
					player.setDir(Direction.FLYSX);
		}

		if(key == KeyEvent.VK_ESCAPE) { 
			panel.setRunning();
			panel.getWindow().backToMenu(); 
		}

	}

	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		player = gameManager.getJames();
		
		if(key == KeyEvent.VK_D){
			player.setVelX(0);
			if(!player.isJumping())
				player.setDir(Direction.STOPDX);
		}

		if(key == KeyEvent.VK_A){
			player.setVelX(0);
			if(!player.isJumping())
				player.setDir(Direction.STOPSX);
		}

		if(key == KeyEvent.VK_W) {
			player.setFalling(true);

//			player.setJumping(false);
		}

		if(key == KeyEvent.VK_E)
			player.setGliding(false);

//		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
	}

}