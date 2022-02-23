package it.unical.mat.igpe17.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ScrollingBackground {
	
	BufferedImage image;
	ImageLoader il = new ImageLoader();

	float x, y;
	float scrollFactor = 1f;
	
	public ScrollingBackground() {
		image = ImageLoader.bg;
	}
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void draw(Graphics g) {

	    g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);

	}

}
