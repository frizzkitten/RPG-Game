package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	//no idea what this is for but here it is
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH * 9 / 12;
	public static final int SCALE = 2;
	public final String TITLE = "Shattered World";
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	//does this in the tutorial but I changed it cuz I don't like that
	//private BufferedImage spriteSheet = null;
	
	//temporary until we make the background move instead of the character
	private BufferedImage background = null;
	
	
	private boolean running = false;
	private Thread thread;
	
	//TEMPORARY
	private Player p;
	//END TEMPORARY
	
	private synchronized void start() {
		if (running) { return; }
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	private synchronized void stop() {
		if (!running) { return; }
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// not handled
			e.printStackTrace();
		}
		
		System.exit(0);
		
	}
	
	private void init() {
		//makes it so you don't have to click the screen before playing
		requestFocus();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			background = loader.loadImage("/GrassTile1.png");
			//does this in the tutorial but I don't like it so I changed it
			//spriteSheet = loader.loadImage("/SpriteSheet.png");
		} 
		//would most likely happen if the file doesn't exist
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//so that the game will listen to keyboard input
		addKeyListener(new KeyInput(this));
		
		//creates the main character
		p = new Player("/PurpleDude.png", 16, 16, 200, 200, this);
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {
			p.setXVel(-1);
		} else if (key == KeyEvent.VK_RIGHT) {
			p.setXVel(1);
		} else if (key == KeyEvent.VK_UP) {
			p.setYVel(-1);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setYVel(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//TODO need to alter this so that if the opposite key is pressed,
		//won't set to 0 (right now that causes some stuttery movement)
		if (key == KeyEvent.VK_LEFT) {
			p.setGoingLeft(false);
			if (!p.isGoingRight())
				p.setXVel(0);
			else 
				return;
		} else if (key == KeyEvent.VK_RIGHT) {
			p.setGoingRight(false);
			if (!p.isGoingLeft())
				p.setXVel(0);
			else 
				return;
		} else if (key == KeyEvent.VK_UP) {
			p.setGoingUp(false);
			if (!p.isGoingDown())
				p.setYVel(0);
			else 
				return;
		} else if (key == KeyEvent.VK_DOWN) {
			p.setGoingDown(false);
			if (!p.isGoingUp())
				p.setYVel(0);
			else 
				return;
		}
	}
	
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		//number of times the tick method will run per second
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		//the number of times tick() has run in the current second
		int updates = 0;
		//the number of frames drawn in the current second
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		//the game loop
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			//delta will be >= 1 amountOfTicks times per second
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			//draws everything to the screen
			render();
			//for fps
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ticks, fps: " + frames);
				updates = 0;
				frames = 0;
			}
			
		}
		
		//ends game
		stop();
		
	}
	
	private void tick() {
		p.tick();
	}
	
	private void render () {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//area for actually drawing stuff
		/////////////////////////////////
		
		//screen
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		//background
		g.drawImage(background, 0, 0, null);
		
		p.render(g);
		
		/////////////////////////////////
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//I think this puts the frame in the middle of the screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	//does this in the tutorial but I don't like it
//	public BufferedImage getSpriteSheet() {
//		return spriteSheet;
//	}
	
}
