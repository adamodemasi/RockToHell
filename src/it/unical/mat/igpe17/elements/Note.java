package it.unical.mat.igpe17.elements;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

import it.unical.mat.igpe17.abstracts.StaticObject;

public class Note extends StaticObject implements Serializable {
	private static final long serialVersionUID = 5603562880939009706L;

	Color color;

	public Note(float x, float y, ObjectId id, Color color) {
		super(x, y, ObjectId.Note);
		
		setColor(color);
	}
	
	public Note() { }

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x - 1, (int) y - 1, 33, 42);
	}

}