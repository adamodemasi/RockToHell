package it.unical.mat.igpe17.abstracts;

import java.awt.Rectangle;
import java.io.Serializable;

import it.unical.mat.igpe17.elements.ObjectId;

public abstract class GameObject implements Serializable{
	private static final long serialVersionUID = 1954622961308900544L;

	protected float x, y;
	protected ObjectId id;
	
	public GameObject(){}

	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract Rectangle getBounds();
	
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

	public ObjectId getId() {
		return id;
	}

	public void setID(ObjectId id){
		this.id = id;
	}

}
