package it.unical.mat.igpe17.gui;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] animation;

	private static final long deltaMillisJames = 100;
	private static final long deltaMillisSpikes = 180;

	private long lastMillisJames = 0;
	private long lastMillisSpikes = 0;

	private int currentIndexJames = 0;
	private int currentIndexSpikes = 0;

	private static final int numberFramesJames = 9;
	private static final int numberFramesJamesJump = 7;
	private static final int numberFramesJamesFly = 4;
	private final static int numberFramesSpikes = 6;

	
	public BufferedImage[] getAnimation() {
		return animation;
	}

	public BufferedImage nextFrameJames(BufferedImage[] animation) {

		BufferedImage frame = animation[(currentIndexJames % numberFramesJames)];
		if (System.currentTimeMillis() >= lastMillisJames + deltaMillisJames) {
			lastMillisJames = System.currentTimeMillis();
			currentIndexJames += 1;
		}
		return frame;
	}

	public BufferedImage nextFrameJamesJump(BufferedImage[] animation) {

		BufferedImage frame = animation[(currentIndexJames % numberFramesJamesJump)];
		if (System.currentTimeMillis() >= lastMillisJames + deltaMillisJames) {
			lastMillisJames = System.currentTimeMillis();
			currentIndexJames += 1;
		}
		return frame;
	}
	
	public BufferedImage nextFrameJamesFly(BufferedImage[] animation) {

		BufferedImage frame = animation[(currentIndexJames % numberFramesJamesFly)];
		if (System.currentTimeMillis() >= lastMillisJames + deltaMillisJames) {
			lastMillisJames = System.currentTimeMillis();
			currentIndexJames += 1;
		}
		return frame;
	}
	
	public BufferedImage nextFrameSpikes(BufferedImage[] animation) {

		BufferedImage frame = animation[(currentIndexSpikes % numberFramesSpikes)];
		if (System.currentTimeMillis() >= lastMillisSpikes + deltaMillisSpikes) {
			lastMillisSpikes = System.currentTimeMillis();
			currentIndexSpikes += 1;
		}
		return frame;
	}

}