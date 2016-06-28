package graphicsTesting;

import java.awt.*;
import javax.swing.*;

public class ShatteredWorlds {
	public static void main(String[] args) {
		DisplayMode dm = new DisplayMode(960, 960, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		ShatteredWorlds frame = new ShatteredWorlds();
		frame.run(dm);
	}
	
	private Screen screen;
	private Image bg;
	private Animation a;
	private Sprite sprite;
	
	public void loadPics() {
		bg = new ImageIcon(getClass().getResource("Stonetile1(3x3).png")).getImage();
		Image reg = new ImageIcon(getClass().getResource("A2.png")).getImage();
		Image left = new ImageIcon(getClass().getResource("A1.png")).getImage();
		Image right = new ImageIcon(getClass().getResource("A3.png")).getImage();
		a = new Animation();
		a.addScene(reg, 250);
		a.addScene(left, 250);
		a.addScene(reg, 250);
		a.addScene(right, 250);
		
		sprite = new Sprite(a);
		sprite.setXVelocity(0.01f);
		sprite.setYVelocity(0.01f);
	}
	
	//main engine to run
	public void run(DisplayMode dm) {
		loadPics();
		screen = new Screen();
		try {
			screen.setFullScreen(dm, new JFrame());
			movieLoop();

		} finally {
			screen.restoreScreen();
		}
	}
	
	public void movieLoop() {
		long startingTime = System.currentTimeMillis();
		long cumuTime = startingTime;
		
		while (cumuTime - startingTime < 5000) {
			long timePassed = System.currentTimeMillis() - cumuTime;
			cumuTime += timePassed;
			update(timePassed);
			
			Graphics g = screen.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();
			
			try {
				Thread.sleep(20);
			} catch (Exception ex) {
				//unhandled
			}
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
	}
	
	public void update(long timePassed) {
		if (sprite.getX() <= 0) {
			sprite.setXVelocity(Math.abs(sprite.getXVelocity()));
//			if (sprite.getXVelocity() < 0) {
//				sprite.setXVelocity(0);
//			}
		} else if (sprite.getX() + sprite.getWidth() >= screen.getWidth()) {
			sprite.setXVelocity(-Math.abs(sprite.getXVelocity()));
//			if (sprite.getXVelocity() > 0) {
//				sprite.setXVelocity(0);
//			}
		}
		if (sprite.getY() <= 0) {
			sprite.setYVelocity(Math.abs(sprite.getYVelocity()));
//			if (sprite.getYVelocity() < 0) {
//				sprite.setYVelocity(0);
//			}
		} else if (sprite.getY() + sprite.getHeight() >= screen.getHeight()) {
			sprite.setYVelocity(-Math.abs(sprite.getYVelocity()));
//			if (sprite.getYVelocity() > 0) {
//				sprite.setYVelocity(0);
//			}
		}
		
		sprite.update(timePassed);
	}
}