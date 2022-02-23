package it.unical.mat.igpe17.gui;

import it.unical.mat.igpe17.elements.Player;

public class Camera {

	private float x, y;
	
	ScrollingBackground sb;
	HUD hud;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
		sb = new ScrollingBackground();
		hud = new HUD();
	}

	public void tick(Player player) {

		float xTarg = -player.getX() + GamePanel.WIDTH / 2;
		x += (xTarg - x) * 0.05f;
		
		float yTarg = -player.getY() / 5 + GamePanel.HEIGHT / 2;
		y += (yTarg - y);
		
//		x = -player.getX() + Game.WIDTH / 2;
//		y = -player.getY() + Game.HEIGTH / 2;

		if(player.getX() < GamePanel.WIDTH / 2) x = 0;					//la cam non va oltre il bordo sx
		if(player.getX() > GamePanel.WIDTH / 2) x = -player.getX() + GamePanel.WIDTH / 2;
		
		sb.setX(x);
		sb.setY(y);
		
		hud.setX(x);
		hud.setY(y);
		
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
}
