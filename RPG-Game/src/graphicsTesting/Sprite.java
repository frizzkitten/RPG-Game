package graphicsTesting;

import java.awt.Image;

public class Sprite {

	private Animation a;
	private float x;
	private float y;
	private float vx;
	private float vy;
	
	//constructor
	public Sprite(Animation a) {
		this.a = a;
	}
	
	//change position
	public void update(long timePassed) {
		x += vx * timePassed;
		y += vy * timePassed;
		a.update(timePassed);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public int getWidth() {
		return a.getImage().getWidth(null);
	}
	
	public int getHeight() {
		return a.getImage().getHeight(null);
	}
	
	public float getXVelocity() {
		return vx;
	}
	
	public float getYVelocity() {
		return vy;
	}
	
	public void setXVelocity(float vx) {
		this.vx = vx;
	}
	
	public void setYVelocity(float vy) {
		this.vy = vy;
	}
	
	public Image getImage() {
		return a.getImage();
	}
	
}