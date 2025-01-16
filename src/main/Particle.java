package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Particle {

	GraphicPanel gp;
	Random rand = new Random();
	
	public double x = 0;
	public double y = 0;
	public double vel = 0;
	public double rot = 0;
	public int timer = 0;
	public int timerChange = 0;
	public double size = 0;
	public Color color = Color.white;
	
	public Particle(GraphicPanel gp, double x, double y, double vel, double rot, int timer, int timerChange, double size, Color color) {
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.vel = vel * rand.nextInt(50,100)/100;
		this.rot = rot + rand.nextInt(-20,20);
		this.timer = timer;
		this.timerChange = timerChange;
		this.size = size;
		this.color = color;
	}
	
	public void update() {
		
		double xChange = Math.cos(Math.toRadians(rot-90))*vel;
		double yChange = Math.sin(Math.toRadians(rot-90))*vel;
		
		x += xChange;
		y += yChange;
		timer -= timerChange;
		
		if (timer <= 0) {
			gp.particles.remove(this);
		}
		
		if (x < 0) {
			gp.particles.remove(this);
		} else if (x > gp.screenWidth) {
			gp.particles.remove(this);
		}
		
		if (y < 0) {
			gp.particles.remove(this);
		} else if (y > gp.screenHeight) {
			gp.particles.remove(this);
		}
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform origTransform = g2.getTransform();
		
		double realSize = size * timer;
		g2.setColor(this.color);
		g2.fillRect((int) (x - realSize/2), (int) (y - realSize/2), (int) realSize, (int) realSize);
		g2.setTransform(origTransform);
	}
}
