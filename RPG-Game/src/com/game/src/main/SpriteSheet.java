package com.game.src.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage ss) {
		this.image = ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		
		BufferedImage img = image.getSubimage((col * 16) - 16, (row * 16) - 16, width, height);
		return img;
		
	}
	
}
