package it.unical.mat.igpe17.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JPanel;

import it.unical.mat.igpe17.common.LevelHandler;
import it.unical.mat.igpe17.elements.Direction;
import it.unical.mat.igpe17.elements.Goal;
import it.unical.mat.igpe17.elements.Note;
import it.unical.mat.igpe17.elements.ObjectId;
import it.unical.mat.igpe17.elements.Platform;
import it.unical.mat.igpe17.elements.Player;
import it.unical.mat.igpe17.elements.Spike;
import it.unical.mat.igpe17.elements.Wall;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{

	private final WindowFrame window;

	public static int WIDTH = 800, HEIGTH = 600;

	private boolean running = false;

	private Thread thread;


	@SuppressWarnings("unused")
	private ImageLoader imageLoader;
	private GameManager gameManager;
	private Camera cam;
	private Animation spriteAnimator;
	private LevelHandler levelHandler;

	private int time = 120;

	public GamePanel(final WindowFrame frame) {
		
		window = frame;

		init();
	}

	public void start(){

		if(running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();

	}

	private void init() {
		gameManager = new GameManager();
		levelHandler = new LevelHandler();
		imageLoader = new ImageLoader();
		cam = new Camera(0, 0);
		spriteAnimator = new Animation();
		gameManager = levelHandler.load(11);

		this.addKeyListener(new KeyInput(gameManager, this, window));
		
		
//		for(int i = 0; i < 500; i += 22)
//			gameManager.getWalls().add(new Wall(i, 605, ObjectId.Wall));
//		for(int i = 500; i < 800; i += 75)
//			gameManager.getSpikes().add(new Spike(i, 610, ObjectId.Spike));
//		for(int i = 850; i < 2000; i += 22)
//			gameManager.getWalls().add(new Wall(i, 585, ObjectId.Wall));
//		for(int i = 110; i < 250; i += 20)
//			gameManager.getWalls().add(new Wall(i, 460, ObjectId.Wall));
//		gameManager.getPlatforms().add(new Platform(445, 350, ObjectId.Platform, 85, Color.BLUE, Direction.UP));
//		gameManager.getPlatforms().add(new Platform(730, 200, ObjectId.Platform, 120, Color.RED, Direction.DOWN));
//		gameManager.getNotes().add(new Note(350, 350, ObjectId.Note, Color.RED));
//		gameManager.getNotes().add(new Note(350, 270, ObjectId.Note, Color.RED));
//		gameManager.getNotes().add(new Note(450, 350, ObjectId.Note, Color.BLUE));
//		gameManager.getNotes().add(new Note(450, 270, ObjectId.Note, Color.BLUE));
//		gameManager.setJames(new Player(200, 250, ObjectId.Player));
//
//		gameManager.setGoal(new Goal(1600, 250, ObjectId.Goal));						// endpoint

//		try { levelHandler.save(0);
//		} catch (IOException e) { e.printStackTrace();
//		}
	}

	@Override
	public void run() {

//		try {
//			imageLoader.tracker.waitForAll();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		long lastFpsTime = 0;

		while (running && time > 0) {
			// calculate how long its been since the last update
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;

			// update the frame counter
			lastFpsTime += updateLength;
//			fps++;

			// update FPS counter if a second has passed since last recorded
			if (lastFpsTime >= 1000000000) {

//				System.out.println("(FPS: "+ fps+")");
				lastFpsTime = 0;
//				fps = 0;

			    time --;
			}

			repaint();										// draw everything

			update();										// update the game logic

	    	try {
	    		Thread.sleep( (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocus(true);
		
		Graphics2D g2 = (Graphics2D) g;
		//DRAW FROM HERE

		g.drawImage(ImageLoader.bg, 0, 0, null);											//background

		g2.translate(cam.getX(), cam.getY());												//cam begin
		cam.sb.draw(g);																		//scrolling background

		String line = Integer.toString(time);

		Player player = gameManager.getJames();
		
		cam.hud.draw(g2, player, cam, line);								//HUD

//--------------- DRAW PLATFORMS ------------------ //		
		for(int i = 0; i < gameManager.getPlatforms().size(); i++){
			Platform tmp = gameManager.getPlatforms().get(i);
			g.drawImage(ImageLoader.platform, (int) tmp.getX(), (int) tmp.getY(), this);

//			g.setColor(Color.white);
//			g2.draw(tmp.getBounds());
		}

//			g.setColor(Color.red);
//			g.fillRect((int) gameManager.james.getX(), (int) gameManager.james.getY(), 32,64);

//--------------- END DRAW PLATFORMS ----------- //

//--------------- DRAW PLAYER ------------------ //
		if(player.isGliding() && (player.getDir() == Direction.FLYDX) )
				g.drawImage(spriteAnimator.nextFrameJamesFly(ImageLoader.jamesFLY), (int)player.getX() - 25, (int)player.getY(), 100, 60, null);
		if(player.isGliding() && player.getDir() == Direction.FLYSX)
				g.drawImage(spriteAnimator.nextFrameJamesFly(ImageLoader.jamesFLYSX), (int)player.getX() - 25, (int)player.getY(), 100, 60, null);
		else {
			if( (player.getDir() == Direction.UPDX) )
				g.drawImage(spriteAnimator.nextFrameJamesJump(ImageLoader.jamesJUMP), (int)player.getX(), (int)player.getY(), 60, 80, null);
			else if((player.getDir() == Direction.UPSX) )
				g.drawImage(spriteAnimator.nextFrameJamesJump(ImageLoader.jamesJUMPSX), (int)player.getX(), (int)player.getY(), 60, 80, null);
			
			if(player.getDir() == Direction.UPDX )
					g.drawImage(spriteAnimator.nextFrameJamesJump(ImageLoader.jamesJUMP), (int)player.getX(), (int)player.getY(), 60, 80, null);
			
			if(player.getDir() == Direction.UP )
				g.drawImage(spriteAnimator.nextFrameJamesJump(ImageLoader.jamesJUMP), (int)player.getX(), (int)player.getY(), 60, 80, null);
			
			if(player.getDir() == Direction.UPSX )
					g.drawImage(spriteAnimator.nextFrameJamesJump(ImageLoader.jamesJUMPSX), (int)player.getX(), (int)player.getY(), 60, 80, null);

			if( player.getDir() == Direction.STOPDX )
				g.drawImage(ImageLoader.jamesDX[8], (int)player.getX(), (int)player.getY(), 60, 80, null);

			if(player.getDir() == Direction.STOPSX)
				g.drawImage(ImageLoader.jamesSX[8], (int)player.getX(), (int)player.getY(), 60, 80, null);

			if(player.getDir() == Direction.RIGHT)
				g.drawImage(spriteAnimator.nextFrameJames(ImageLoader.jamesDX), (int)player.getX(), (int)player.getY(), 60, 80, null);
			
			if (player.getDir() == Direction.LEFT)
				g.drawImage(spriteAnimator.nextFrameJames(ImageLoader.jamesSX), (int)player.getX(), (int)player.getY(), 60, 80, null);
		}

//			g.setColor(Color.white);
//			g2.draw(player.getBounds());
//			g2.draw(player.getBoundsTop());
//			g2.draw(player.getBoundsLeft());
//			g2.draw(player.getBoundsRight());
// -------------- END DRAW PLAYER ------------------- //
			
// -------------- DRAW WALLS ------------------------ //
		for(int i = 0; i < gameManager.getWalls().size(); i++){
			Wall tmp = gameManager.getWalls().get(i);
			g.drawImage(ImageLoader.wall, (int) tmp.getX(), (int) tmp.getY(), null);

//			g.setColor(Color.white);
//			g2.draw(tmp.getBounds());
		}
// -------------- END DRAW WALLS -------------------- //
		
//		for(int i = 0; i < 2200; i += 225)
//			g.drawImage(ImageLoader.fire, i, 480, 225, 225, this);
		
// -------------- DRAW SPIKES ------------------------ //		
		for(int i = 0; i < gameManager.getSpikes().size(); i++){
			Spike tmp = gameManager.getSpikes().get(i);
			g.drawImage(spriteAnimator.nextFrameSpikes(ImageLoader.spikes), (int) tmp.getX(), (int) tmp.getY(), null);

//			g.setColor(Color.white);
//			g2.draw(tmp.getBounds());
		}
// -------------- END DRAW SPIKES -------------------- //
// -------------- DRAW NOTES ------------------------- //		
		for(int i = 0; i < gameManager.getNotes().size(); i++){
			Note tmp = gameManager.getNotes().get(i);

				if(tmp.getColor().equals(Color.BLUE))
					g.drawImage(ImageLoader.note_blue, (int) tmp.getX(), (int) tmp.getY(), null);
				else if(tmp.getColor().equals(Color.RED))
					g.drawImage(ImageLoader.note_red, (int) tmp.getX(), (int) tmp.getY(), null);
				else if(tmp.getColor().equals(Color.GREEN))
					g.drawImage(ImageLoader.note_green, (int) tmp.getX(), (int) tmp.getY(), null);
			
//				g.setColor(Color.white);
//				g2.draw(tmp.getBounds());
		}
// -------------- END DRAW NOTES --------------------- //

//		g.drawImage(ImageLoader.guitar, (int)gameManager.getGoal().getX(), (int)gameManager.getGoal().getY(), 90, 160, this);

		g.drawImage(ImageLoader.goal, (int)gameManager.getGoal().getX(), (int)gameManager.getGoal().getY(), ImageLoader.goal.getWidth(), ImageLoader.goal.getHeight(), null);
//		g2.draw(gameManager.getGoal().getBounds());
		
		g2.translate(-cam.getX(), -cam.getY());											//cam end

		g.dispose();

	}

	private void update() {

		gameManager.tick();

		cam.tick(gameManager.getJames());

	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning() {
		this.running = !running;
	}

	public WindowFrame getWindow() {
		return window;
	}

	public int getTime() {
		return time;
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

}