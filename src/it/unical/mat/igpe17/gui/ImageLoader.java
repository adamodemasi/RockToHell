package it.unical.mat.igpe17.gui;

import java.applet.Applet;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader extends Applet {
	private static final long serialVersionUID = 1624030198339830032L;

    public static final int BG_H = 794;
    public static final int BG_W = 3809;
	public static final int JAMES_W = 0;
    public static final int JAMES_H = 134;
    public static final int PLATFORM_W = 0;
    public static final int PLATFORM_H = 0;
    public static final int WALL_W = 20;
    public static final int WALL_H = 20;
    public static final int SPIKE_W = 0;
    public static final int SPIKE_H = 83;
    public static final int GOAL_W = 0;
    public static final int GOAL_H = 43;
    public static final int NOTE_W = 0;
    public static final int NOTE_H = 44;

	public static BufferedImage bg, james, wall, platform, spike, hud, note_blue, note_red, note_green, won, lose, info, goal;
	public static BufferedImage[] jamesDX, jamesSX, jamesJUMP, jamesJUMPSX, jamesFLY, jamesFLYSX, spikes; 
	public static Image fire, guitar, beer_end_level;

//	public MediaTracker tracker;

    public ImageLoader(){
//    	tracker = new MediaTracker(this);

    	jamesDX = new BufferedImage[9];
    	jamesSX = new BufferedImage[9];
    	jamesJUMP = new BufferedImage[7];
    	jamesJUMPSX = new BufferedImage[7];
    	jamesFLY = new BufferedImage[4];
    	jamesFLYSX = new BufferedImage[4];
    	spikes = new BufferedImage[6];

		try {
			bg = ImageIO.read(new File("res/bg.png"));
			james = ImageIO.read(new File("res/james2.png"));
			platform = ImageIO.read(new File("res/plat2.png"));
			wall = ImageIO.read(new File("res/wl.png"));
			hud = ImageIO.read(new File("res/hud.png"));
			note_blue = ImageIO.read(new File("res/note_blue.png"));
			note_red = ImageIO.read(new File("res/note_red.png"));
			note_green = ImageIO.read(new File("res/note.png"));
			won = ImageIO.read(new File("res/flame.jpg"));
			lose = ImageIO.read(new File("res/flame1.jpg"));
			info = ImageIO.read(new File("res/menu/info.png"));
			goal = ImageIO.read(new File("res/goal.png"));
			
			fire = Toolkit.getDefaultToolkit().createImage("res/fire.gif");
			guitar = Toolkit.getDefaultToolkit().createImage("res/guitar.gif");
			beer_end_level = Toolkit.getDefaultToolkit().createImage("res/beerfire.gif");

			for(int i = 0; i < jamesDX.length; i++){
				jamesDX[i] = ImageIO.read(new File("res/DX/" + i +".png"));
				jamesSX[i] = ImageIO.read(new File("res/SX/" + i +".png"));
			}
			
			for(int i = 0; i < jamesJUMP.length; i++){
				jamesJUMP[i] = ImageIO.read(new File("res/JUMP/" + i +".png"));
				jamesJUMPSX[i] = ImageIO.read(new File("res/JUMPSX/" + i +".png"));
			}
			
			for(int i = 0; i < jamesFLY.length; i++){
				jamesFLY[i] = ImageIO.read(new File("res/FLY/" + i +".png"));
				jamesFLYSX[i] = ImageIO.read(new File("res/FLYSX/" + i +".png"));
			}
			
			for(int i = 0; i < spikes.length; i++){
				spikes[i] = ImageIO.read(new File("res/spikes/spike" + i + ".png"));
			}

		} catch (IOException e) {
			e.printStackTrace();	{
			    System.out.println("png missing");
			}
		}
		
//		tracker.addImage(info, 41);
//		tracker.addImage(bg, 42);
//		tracker.addImage(james, 43);
//		tracker.addImage(wall, 44);
//		tracker.addImage(platform, 45);
//		tracker.addImage(spike, 46);
//		tracker.addImage(hud, 47);
//		tracker.addImage(note_blue, 48);
//		tracker.addImage(note_red, 49);
//		tracker.addImage(note_green, 50);
//
//		for(int i = 0; i < jamesDX.length; i++){
//			tracker.addImage(jamesDX[i], i);
//			tracker.addImage(jamesSX[i], 10 + i);
//		}
//		for(int i = 0; i < jamesJUMP.length; i++){
//			tracker.addImage(jamesJUMP[i], 20 + i);
//			tracker.addImage(jamesJUMPSX[i], 70 + i);}
//		for(int i = 0; i < jamesFLY.length; i++){
//			tracker.addImage(jamesFLY[i], 30 + i);
//			tracker.addImage(jamesFLYSX[i], 60 + i);
//		}
//		for(int i = 0; i < spikes.length; i++){
//			tracker.addImage(spikes[i], 50 + i);
//		}
    }
}
