package it.unical.mat.igpe17.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import it.unical.mat.igpe17.elements.Player;

public class HUD {
	
	private float x, y;
	
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

	private BufferedImage image;
	
	public HUD() {
		image = ImageLoader.hud;
	}
	
	public void draw(Graphics2D g, Player player, Camera cam, String time) {
		
		g.drawImage(image, (int) -cam.getX(), (int) -cam.getY() + 25, null);
		g.setFont(new Font("Taranis", Font.BOLD, 15));
		g.setColor(Color.WHITE);
		g.drawString(player.getLife() + " / 5", -cam.getX() + 30, -cam.getY() + 43);
		g.drawString(time, -cam.getX() + 48, -cam.getY() + 67);
		
	}
	
}