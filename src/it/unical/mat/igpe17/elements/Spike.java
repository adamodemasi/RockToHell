package it.unical.mat.igpe17.elements;

import java.awt.Rectangle;
import java.io.Serializable;

import it.unical.mat.igpe17.abstracts.StaticObject;


public class Spike extends StaticObject implements Serializable{
	private static final long serialVersionUID = -7299882487601103531L;
	

	public Spike(float x, float y, ObjectId id) {
		super(x, y, id);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 70, 50);
	}

}
