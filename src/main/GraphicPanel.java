package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public int screenWidth = 1024;//768;
	public int screenHeight = 768;//512;
	
	public int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gThread;
	
	ArrayList<Tank> tanks = new ArrayList<Tank>();
	ArrayList<Round> rounds = new ArrayList<Round>();
	ArrayList<Particle> particles = new ArrayList<Particle>();
	
	Color red = new Color(181, 89, 69);
	Color orange = new Color(222, 159, 71);
	Color gray = new Color(135, 133, 124);
	Color darkGray = new Color(99, 102, 99);
	
	BufferedImage background = getImage("/background.png");
	
	public GraphicPanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.setFocusable(true);

		this.addKeyListener(keyH);
		
		tanks.add(new Tank(this, screenWidth/2*.75, screenHeight/2, 1, keyH));
		tanks.add(new Tank(this, screenWidth/2*1.25, screenHeight/2, 2, keyH));
	}
	
	public void startGThread() {
		
		gThread = new Thread(this);
		gThread.start();
	}
	
	@Override @SuppressWarnings("unused")
	public void run() {

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while (gThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				//System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).update();
		}
		
		for (int i = 0; i < rounds.size(); i++) {
			rounds.get(i).update();
		}
		
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, screenWidth, screenHeight);
		//g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);
		
		for (int i = 0; i < rounds.size(); i++) {
			rounds.get(i).draw(g2);
		}
		
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).draw(g2);
		}
		
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).draw(g2);
		}

		g2.dispose();
	}

	public BufferedImage getImage(String path) {
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public Point rotatePoint(double centerX, double centerY, double pointX, double pointY, double rot) {
		
		rot = Math.toRadians(rot);
		
		int newX = (int) (centerX + (pointX-centerX)*Math.cos(rot) - (pointY-centerY)*Math.sin(rot));
		int newY = (int) (centerY + (pointX-centerX)*Math.sin(rot) + (pointY-centerY)*Math.cos(rot));
		
		return new Point(newX, newY);
	}
}
