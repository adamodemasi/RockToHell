package it.unical.mat.igpe17.elements;

import java.awt.Rectangle;
import java.io.Serializable;

import it.unical.mat.igpe17.abstracts.Character;

public class Player extends Character implements Serializable{

	private static final long serialVersionUID = 2205734286119757226L;


	public static float width = 50, height = 80;

	private static final int MAX_SPEED = 10;

    private Direction dir;
	private int lives;

	private boolean gliding;


	public Player() { }

    public Player(float x, float y, ObjectId id){
		super(x, y, ObjectId.Player);

		lives = 5;
		setGliding(false);
		setDir(Direction.STOPDX);

    }
    
    public int scored(int timeLeft){

    	if(timeLeft > 60)
    		return (timeLeft * 15);
    	else if(timeLeft < 60 && timeLeft > 30)
    		return timeLeft * 10;
    	else
    		return timeLeft * 5;
    	
    }

	public void tick() {
		x += velX;
		y += velY;

		if(falling || jumping){
			if(y > 0 && gliding)
				velY *= 0.9f;
			velY += gravity;
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}

	}

	public Rectangle getBounds(){
		return new Rectangle((int)((x + width / 2) - ((width / 2) / 2)), (int)(y + height / 2), (int) (width / 2), (int) (height / 2));
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle((int) (x + width / 2 - ((width / 2) / 2)), (int)y, (int)width / 2, (int)height / 2);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle((int)(x + width - 5), (int)(y + 5), 5, (int)(height - 10));
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle((int)x, (int) (y + 5), 5, (int)(height - 10));
	}

    public int getLife() {
    	return lives;
    }

    public void decrLife() {
    	lives--;
    }

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
    public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	public boolean isGliding() {
		return gliding;
	}
	
    public void setGliding(boolean gliding) {
		this.gliding = gliding;
	}

}