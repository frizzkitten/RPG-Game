package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Animation {
	
	public static long lastUpdatedTime;
	
	private boolean isRunning;
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	private int currScene;
	//order of columns in a spritesheet for a walking animation in any direction
	private static final int[] WALKING_ORDER = {1,2,3,2};
	//row in spritesheet
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	private static final int UP = 4;
	
	public Animation() {
		isRunning = false;
		currScene = 0;
		lastUpdatedTime = System.currentTimeMillis();
	}
	
	public void addSceneFromSpriteSheet(BufferedImage img, int time) {
		scenes.add(new Scene(img, time));
	}
	
	public void addScene(String location, int time) {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			scenes.add(new Scene(loader.loadImage(location), time));
		} 
		//would most likely happen if the file doesn't exist
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Makes a walking cycle from a sprite sheet in a direction.
	 * @param ss the sprite sheet used
	 * @param direction can be 'right', 'left', 'up', or 'down'
	 */
	public void makeWalkAnimation(SpriteSheet ss, String direction) {
		switch(direction) {
		//TODO should be 16 x 16 but the dimensions of the test sprite sheet are wrong
		case "down":  addWalkingScenes(DOWN, ss);
					  break;
		case "left":  addWalkingScenes(LEFT, ss);
					  break;
		case "right": addWalkingScenes(RIGHT, ss);
		 			  break;
		case "up":    addWalkingScenes(UP, ss);
		 		  	  break;
		}
	}
	
	/**
	 * Helper for makeWalkingAnimation()
	 * @param row the row of the walking animation in the sprite sheet
	 * @param ss the character's sprite sheet
	 */
	private void addWalkingScenes(int row, SpriteSheet ss) {
		for (int i = 0; i < 4; i++) {
			//TODO should be 16x16 but the test sprite sheet is dimensioned incorrectly
			scenes.add(new Scene(ss.grabImage(row, WALKING_ORDER[i], 16, 16), 200));
		}
	}
	
	public BufferedImage update() {
		//if the time passed since the last update is more than the
		//time required of the most recent scene, switch to next scene
		if (System.currentTimeMillis() - lastUpdatedTime > scenes.get(currScene).time) {
			currScene++;
			if (currScene >= scenes.size()) 
				currScene = 0;
			lastUpdatedTime = System.currentTimeMillis();
		}
		
		return scenes.get(currScene).currImg;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	private class Scene {
		BufferedImage currImg;
		int time;
		
		public Scene(BufferedImage img, int time) {
			currImg = img;
			this.time = time;
		}
	}
	
}
