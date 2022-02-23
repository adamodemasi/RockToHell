package it.unical.mat.igpe17.abstracts;

import java.io.Serializable;

import it.unical.mat.igpe17.elements.ObjectId;

public abstract class DynamicObject extends GameObject implements Serializable{
	private static final long serialVersionUID = -6065795256871023718L;


	protected float velX = 0, velY = 0;

//    protected Direction direction = Direction.STOP;

    private double speed;

    public DynamicObject() { }
    
    public DynamicObject(float x, float y, ObjectId id) {
    	super(x, y, id);
    }

//    public Direction getDirection() {
//		    return direction;
//    }
//
//    public void setDirection(final Direction direction) {
//		    this.direction = direction;
//    }
    
	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

    double getSpeed() {
	    return speed;
    }
    
    void setSpeed(final double speed) {
    	this.speed = speed;
    }
}
