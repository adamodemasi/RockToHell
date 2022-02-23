package it.unical.mat.igpe17.elements;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

import it.unical.mat.igpe17.abstracts.DynamicObject;

public class Platform extends DynamicObject implements Serializable{
	private static final long serialVersionUID = -422523899418035161L;
	
	
	private int radius, tmpR = 0;
	private boolean canMove;
	private Color color;
	private Direction direction;

	public Platform(float x, float y, ObjectId id, int radius, Color color, Direction direction) {
		super(x, y, ObjectId.Platform);

		this.radius = radius;
		this.direction = direction;
		this.color = color;
		setMove(false);

	}
	public Platform() { }

	public void tick() {
		if( canMove() ){
			if(direction == Direction.UP){
				y--;
				tmpR++;
				if(tmpR == radius){
					setDirection(Direction.DOWN);
					tmpR = 0;
				}
			}
			if(direction == Direction.DOWN){
				y++;
				tmpR++;
				if(tmpR == radius){
					setDirection(Direction.UP);
					tmpR = 0;
				}
			}
			if(direction == Direction.LEFT){
				x--;
				tmpR++;
				if(tmpR == radius){
					setDirection(Direction.RIGHT);
					tmpR = 0;
				}
			}
			if(direction == Direction.RIGHT){
				x++;
				tmpR++;
				if(tmpR == radius){
					setDirection(Direction.LEFT);
					tmpR = 0;
				}
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x + 10, (int) y, 120, 30 );
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public boolean canMove() {
		return canMove;
	}
	
	public void setMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}