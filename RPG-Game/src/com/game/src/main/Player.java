package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

	private double x;
	private double y;
	private double xVel;
	private double yVel;
	private int width;
	private int height;
	private SpriteSheet spriteSheet;
	
	private BufferedImage playerImg;
	
	public Player(String location, int width, int height, double x, double y, Game game) {
		
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.xVel = 0;
		this.yVel = 0;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = new SpriteSheet(loader.loadImage(location));
		} 
		//would most likely happen if the file doesn't exist
		catch (IOException e) {
			e.printStackTrace();
		}
		
		playerImg = spriteSheet.grabImage(1, 1, 210, 255);
	}
	
	public void tick() {
		x += xVel;
		y += yVel;
		
		//collision with edges
		//left edge
		if (x < 0) 
			x = 0;
		//top edge
		if (y < 0) 
			y = 0;
		//this one is a bit off for some reason
		//right edge
		if (x > Game.WIDTH * Game.SCALE - width) 
			x = Game.WIDTH * Game.SCALE - width;
		//but this one seems fine
		//bottom edge
		if (y > Game.HEIGHT * Game.SCALE - height) 
			y = Game.HEIGHT * Game.SCALE - height;
		
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
