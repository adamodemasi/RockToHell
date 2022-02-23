package it.unical.mat.igpe17.abstracts;

import java.io.Serializable;

import it.unical.mat.igpe17.elements.ObjectId;

public abstract class StaticObject extends GameObject  implements Serializable{
	private static final long serialVersionUID = 8316284038885202274L;

	public StaticObject() { }
	
	public StaticObject(float x, float y, ObjectId id){
		super(x, y, id);	
	}

}