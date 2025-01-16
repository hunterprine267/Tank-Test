package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Round {

	GraphicPanel gp;
	
	public double x = 0;
	public double y = 0;
	public double vel = 0;
	public double rot = 0;
	public int timer = 0;
	public int index = 0;
	public BufferedImage image;
	
	public Round(GraphicPanel gp, double x, double y, double vel, double rot, int index) {
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.vel = vel;
		this.rot = rot;
		this.index = index;
		image = gp.getImage("/round.png");
	}
	
	public void update() {
		
		double xChange = Math.cos(Math.toRadians(rot-90))*vel;
		double yChange = Math.sin(Math.toRadians(rot-90))*vel;
		
		x += xChange;
		y += yChange;
		timer += 1;
		
		if (timer > 180) {
			gp.rounds.remove(this);
		}
		
		if (x < 0) {
			gp.rounds.remove(this);
		} else if (x > gp.screenWidth) {
			gp.rounds.remove(this);
		}
		
		if (y < 0) {
			gp.rounds.remove(this);
		} else if (y > gp.screenHeight) {
			gp.rounds.remove(this);
		}
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform origTransform = g2.getTransform();
		
		g2.rotate(Math.toRadians(rot), x, y);
		g2.drawImage(image, (int) x - 32, (int) y, 64, 64, null);
		g2.setTransform(origTransform);
	}
}
