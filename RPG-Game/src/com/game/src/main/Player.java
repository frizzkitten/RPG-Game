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
	
	private boolean goingRight;
	private boolean goingLeft;
	private boolean goingDown;
	private boolean goingUp;
	
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
		
		this.setGoingDown(false);
		this.setGoingUp(false);
		this.setGoingRight(false);
		this.setGoingLeft(false);
		
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
		
		idle = BufferedImageLoader.scale(spriteSheet.grabImage(1, 1, 16, 16), BufferedImage.TYPE_INT_ARGB, 16, 16, 2.0, 2.0);
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
	
	/**
	 * Draws the character, is called every time the game's render is called
	 * @param g
	 */
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

	public boolean isGoingRight() {
		return goingRight;
	}

	public void setGoingRight(boolean goingRight) {
		this.goingRight = goingRight;
	}

	public boolean isGoingLeft() {
		return goingLeft;
	}

	public void setGoingLeft(boolean goingLeft) {
		this.goingLeft = goingLeft;
	}

	public boolean isGoingDown() {
		return goingDown;
	}

	public void setGoingDown(boolean goingDown) {
		this.goingDown = goingDown;
	}

	public boolean isGoingUp() {
		return goingUp;
	}

	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}
	
}
