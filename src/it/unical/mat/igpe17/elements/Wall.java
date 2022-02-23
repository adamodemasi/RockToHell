package it.unical.mat.igpe17.elements;

import java.awt.Rectangle;
import java.io.Serializable;

import it.unical.mat.igpe17.abstracts.StaticObject;

public class Wall extends StaticObject implements Serializable{
	private static final long serialVersionUID = -125847400544632596L;
	

	public Wall(float x, float y, ObjectId id) {
		super(x, y, ObjectId.Wall);

	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x - 1, (int) y - 1, 20, 20);
	}

}
