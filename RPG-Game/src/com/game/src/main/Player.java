package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

	private double x;
	private double y;
	
	private double xVel;
	private double yVel;
	
	private BufferedImage playerImg;
	
	public Player(double x, double y, Game game) {
		this.x = x;
		this.y = y;
		this.xVel = 0;
		this.yVel = 0;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		playerImg = ss.grabImage(1, 1, 210, 255);
	}
	
	public void tick() {
		x += xVel;
		y += yVel;
	}
	
	public void render(Graphics g) {
		g.drawImage(playerImg, (int) x, (int) y, null);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.x = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setXVel(double xVel) {
		this.xVel = xVel;
	}
	
	public void setYVel(double yVel) {
		this.yVel = yVel;
	}
	
	public double getXVel() {
		return xVel;
	}
	
	public double getYVel() {
		return yVel;
	}
	
}
