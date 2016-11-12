package com.fortyways.util;

public class Box {
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	public boolean contains(float x,float y){
		return x>this.getX()-getWidth()/2&&
				x<this.getX()+getWidth()/2&&
				y>this.getY()-getHeight()/2&&
				y<this.getY()+getHeight()/2;			
	}
	public Box(float x,float y,float width,float height){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
	}
	public Box() {
		// TODO Auto-generated constructor stub
	}
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
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
}
