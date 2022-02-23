package it.unical.mat.igpe17.abstracts;

import java.io.Serializable;

import it.unical.mat.igpe17.elements.ObjectId;

public abstract class Character extends DynamicObject implements Serializable{
	private static final long serialVersionUID = 4392197697028554487L;
	
	protected boolean jumping, falling, isAlive;
	
//    protected boolean moving = true;
	
    public static float gravity = 0.5f;

	public Character(float x, float y, ObjectId id) {
		super(x, y, id);
		setJumping(false);
		setFalling(true);
		setAlive(true);		
	}
	
	public Character() {}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
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

}
