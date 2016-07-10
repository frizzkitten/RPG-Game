package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH * 9 / 12;
	public static final int SCALE = 2;
	public final String TITLE = "Shattered World";
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
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
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/SpriteSheet.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p = new Player(200, 200, this);
		
	}
	
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		//the game loop
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ticks, fps: " + frames);
				updates = 0;
				frames = 0;
			}
			
		}
		
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
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
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
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
}
