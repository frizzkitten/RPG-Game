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
	//could be made into an animation if we want an idle animation
	private BufferedImage idle;
	private Animation walkRight;
	private Animation walkLeft;
	private Animation walkUp;
	private Animation walkDown;
	
	public Player(String location, int width, int height, double x, double y, Game game) {
		
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.xVel = 0;
		this.yVel = 0;
		this.walkRight = new Animation();
		this.walkLeft = new Animation();
		this.walkUp = new Animation();
		this.walkDown = new Animation();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = new SpriteSheet(loader.loadImage(location));
		} 
		//would most likely happen if the file doesn't exist
		catch (IOException e) {
			e.printStackTrace();
		}
		
		idle = spriteSheet.grabImage(1, 1, 210, 255);
		playerImg = idle;
		
		//add animations
		walkRight.makeWalkAnimation(spriteSheet, "right");
		walkLeft.makeWalkAnimation(spriteSheet, "left");
		walkUp.makeWalkAnimation(spriteSheet, "up");
		walkDown.makeWalkAnimation(spriteSheet, "down");
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
		
		//animations
		if (xVel > 0) {
			playerImg = walkRight.update();
		} else if (xVel < 0) {
			playerImg = walkLeft.update();
		} else if (yVel > 0) {
			playerImg = walkDown.update();
		} else if (yVel < 0) {
			playerImg = walkUp.update();
		}
		//standing still animation
		else {
			playerImg = idle;
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(playerImg, (int) x, (int) y, null);
	}
	
	//assumes all frames in a walking animation will be the same length
	public void addToWalkRight(int time, String... locations) {
		for (String location : locations)
			walkRight.addScene(location, time);
	}

	public void addToWalkLeft(int time, String... locations) {
		for (String location : locations)
			walkLeft.addScene(location, time);
	}
	
	public void addToWalkUp(int time, String... locations) {
		for (String location : locations)
			walkUp.addScene(location, time);
	}
	
	public void addToWalkDown(int time, String... locations) {
		for (String location : locations)
			walkDown.addScene(location, time);
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
