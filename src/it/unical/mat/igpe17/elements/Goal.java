package it.unical.mat.igpe17.elements;

import java.awt.Rectangle;
import java.io.Serializable;

import it.unical.mat.igpe17.abstracts.StaticObject;

public class Goal extends StaticObject implements Serializable{
	private static final long serialVersionUID = 1717525667527765617L;

	public Goal(float x, float y, ObjectId id) {
		super(x, y, ObjectId.Goal);
		
	}
	
	public Goal() {	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) x + 5, (int) y + 5, 80, 150);
		
	}

}
