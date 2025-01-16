package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class Tank {
	
	GraphicPanel gp;
	KeyHandler keyH;
	
	public double x;
	public double y;
	public double vel;
	public double turnVel;
	public double turretTurnVel;
	public int baseWidth;
	public int baseHeight;
	public double rot = 0;
	public double turretRot = 0;
	public double totalTurretRot = 0;
	public double collisionRot = 0;
	public int fireTimer = 0;
	public BufferedImage bottomImage;
	public BufferedImage topImage;
	public BufferedImage arrowImage;
	double tankTurretX = 0;
	double tankTurretY = 0;
	int index;
	
	Point[] hitboxPoints = new Point[4];
	
	Polygon hitbox;
	
	Area hitboxArea;
	
	public boolean	upPressed, downPressed,
					leftPressed, rightPressed,
					rotLeftPressed, rotRightPressed,
					scaleUpPressed, scaleDownPressed,
					firePressed;
			
	public Tank(GraphicPanel gp, double x, double y, int index, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		this.x = x;
		this.y = y;
		this.index = index;
		
		if (index == 1) {
			this.bottomImage = gp.getImage("/tank_bottom_1.png");
			this.topImage = gp.getImage("/tank_top_1.png");
		} else {
			this.bottomImage = gp.getImage("/tank_bottom_2.png");
			this.topImage = gp.getImage("/tank_top_2.png");
		}
		
		this.baseWidth = bottomImage.getWidth()*4;
		this.baseHeight = bottomImage.getHeight()*4;
		
		this.hitboxPoints[0] = new Point((int)(-5*4),(int)(-8*4));
		this.hitboxPoints[1] = new Point((int)(-5*4),(int)(8*4));
		this.hitboxPoints[2] = new Point((int)(5*4),(int)(8*4));
		this.hitboxPoints[3] = new Point((int)(5*4),(int)(-8*4));
		
		setHitbox();
	}
	
	public void particles(int index, double x, double y, double rot) {
		
		if (index == 0) {
			gp.particles.add(new Particle(gp, tankTurretX, tankTurretY, 3, totalTurretRot-90, 15, 1, 1, gp.gray));
	 		gp.particles.add(new Particle(gp, tankTurretX, tankTurretY, 3, totalTurretRot+90, 15, 1, 1, gp.darkGray));
	 		gp.particles.add(new Particle(gp, tankTurretX, tankTurretY, 3, totalTurretRot-90, 15, 2, 1, gp.darkGray));
	 		gp.particles.add(new Particle(gp, tankTurretX, tankTurretY, 3, totalTurretRot+90, 15, 2, 1, gp.gray));
	 		gp.particles.add(new Particle(gp, tankTurretX, tankTurretY, 0, totalTurretRot, 25, 10, 1, gp.red));
	 		gp.particles.add(new Particle(gp, tankTurretX, tankTurretY, 0, totalTurretRot, 20, 10, 1, gp.orange));
		} else {
			gp.particles.add(new Particle(gp, x, y, 3, Math.toDegrees(rot)-90 , 15, 2, 1, gp.darkGray));
	 		gp.particles.add(new Particle(gp, x, y, 3, Math.toDegrees(rot)+90, 15, 2, 1, gp.gray));
	 		gp.particles.add(new Particle(gp, x, y, 3, Math.toDegrees(rot)-90, 15, 2, 1, gp.darkGray));
	 		gp.particles.add(new Particle(gp, x, y, 3, Math.toDegrees(rot)+90, 15, 2, 1, gp.gray));
		}
	}
	
	public void checkKeyPressed() {
		
		if (index == 1) {
			
			if (keyH.upPressed1) {
				upPressed = true;
			} else { upPressed = false; }
			if (keyH.downPressed1) {
				downPressed = true;
			} else { downPressed = false; }
			if (keyH.leftPressed1) {
				leftPressed = true;
			} else { leftPressed = false; }
			if (keyH.rightPressed1) {
				rightPressed = true;
			} else { rightPressed = false; }
			if (keyH.rotLeftPressed1) {
				rotLeftPressed = true;
			} else { rotLeftPressed = false; }
			if (keyH.rotRightPressed1) {
				rotRightPressed = true;
			} else { rotRightPressed = false; }
			if (keyH.scaleUpPressed1) {
				scaleUpPressed = true;
			} else { scaleUpPressed = false; }
			if (keyH.scaleDownPressed1) {
				scaleDownPressed = true;
			} else { scaleDownPressed = false; }
			if (keyH.firePressed1) {
				firePressed = true;
			} else { firePressed = false; }
			
		} else {
			
			if (keyH.upPressed2) {
				upPressed = true;
			} else { upPressed = false; }
			if (keyH.downPressed2) {
				downPressed = true;
			} else { downPressed = false; }
			if (keyH.leftPressed2) {
				leftPressed = true;
			} else { leftPressed = false; }
			if (keyH.rightPressed2) {
				rightPressed = true;
			} else { rightPressed = false; }
			if (keyH.rotLeftPressed2) {
				rotLeftPressed = true;
			} else { rotLeftPressed = false; }
			if (keyH.rotRightPressed2) {
				rotRightPressed = true;
			} else { rotRightPressed = false; }
			if (keyH.scaleUpPressed2) {
				scaleUpPressed = true;
			} else { scaleUpPressed = false; }
			if (keyH.scaleDownPressed2) {
				scaleDownPressed = true;
			} else { scaleDownPressed = false; }
			if (keyH.firePressed2) {
				firePressed = true;
			} else { firePressed = false; }
		}
	}
	
	public void setHitbox() {
		
		Point point1 = gp.rotatePoint(x, y, hitboxPoints[0].getX() + x, hitboxPoints[0].getY() + y, rot);
		Point point2 = gp.rotatePoint(x, y, hitboxPoints[1].getX() + x, hitboxPoints[1].getY() + y, rot);
		Point point3 = gp.rotatePoint(x, y, hitboxPoints[2].getX() + x, hitboxPoints[2].getY() + y, rot);
		Point point4 = gp.rotatePoint(x, y, hitboxPoints[3].getX() + x, hitboxPoints[3].getY() + y, rot);
		
		hitbox = new Polygon(new int[] {(int) point1.getX(),(int) point2.getX(),(int) point3.getX(),(int) point4.getX()},
				 			 new int[] {(int) point1.getY(),(int) point2.getY(),(int) point3.getY(),(int) point4.getY()},4);
		
		hitboxArea = new Area(hitbox);
	}
	
	public void checkRoundCollide() {
		
		for (int i = 0; i < gp.rounds.size(); i++) {
			Round round = gp.rounds.get(i);
			double rx = round.x;
			double ry = round.y;
			double rot = Math.toRadians(round.rot);
			double vel = round.vel;
			
			double x = rx;
			double y = ry;
			
			if (round.index != this.index) {
				
				for (double j = .04; j < 1; j+=.04) {
					if (hitbox.contains(x,y)) {
						gp.rounds.remove(i);
						
						particles(1, x, y, rot);
				 		break;
					}
					
					x = rx + (Math.cos(rot-Math.PI/2) * vel * j);
					y = ry + (Math.sin(rot-Math.PI/2) * vel * j);
				}
			}
		}
	}
	
	public void checkTankCollide() {
		
		for (int i = 0; i < gp.tanks.size(); i++) {
			Tank tank = gp.tanks.get(i);
			
			if (tank.index != index) {
				
				setHitbox();
				hitboxArea.intersect(tank.hitboxArea);
				
				collisionRot = Math.atan2(tank.y - hitboxArea.getBounds().getCenterY(), tank.x - hitboxArea.getBounds().getCenterX());

				if (!hitboxArea.isEmpty()) {
					particles(1, hitboxArea.getBounds().getCenterX(), hitboxArea.getBounds().getCenterY(), rot);
				}
				
				while (!hitboxArea.isEmpty()) {

					vel *= 0.1;
					turnVel *= 0.5;
					x -= Math.cos(collisionRot) * .5;
					y -= Math.sin(collisionRot) * .5;
					setHitbox();
					hitboxArea.intersect(tank.hitboxArea);
				}
			}
		}
	}
	
	public void actions() {
		
		if (leftPressed) {
			turnVel -= .075;
		}
		if (rightPressed) {
			turnVel += .075;
		}
		
		if (rotLeftPressed) {
			turretTurnVel -= .075;
		}
		if (rotRightPressed) {
			turretTurnVel += .075;
		}
		
		if (upPressed) {
			vel += .025;
		}
		if (downPressed) {
			vel -= .025;
		}
		
		if (rot >= 360) {
			rot = 0;
		} else if (rot <= 0) {
			rot = 360;
		}
		
		if (turretRot >= 360) {
			turretRot = 0;
		} else if (turretRot <= 0) {
			turretRot = 360;
		}
		
		if (firePressed) {
			
			if (fireTimer == 0) {
		 		fireRound();
			}
		}
		
		rot += turnVel;
		turretRot += turretTurnVel;
		totalTurretRot = rot + turretRot;
		
		x += (Math.cos(Math.toRadians(rot-90))*2)*vel;
		y += (Math.sin(Math.toRadians(rot-90))*2)*vel;

		if (Math.abs(vel) >= 1.5 || (!upPressed && !downPressed)) {
			vel *= .95;
		}
		if (Math.abs(turnVel) >= 1 || (!leftPressed && !rightPressed)) {
			turnVel *= .95;
		}
		if (Math.abs(turretTurnVel) >= 1 || (!rotLeftPressed && !rotRightPressed)) {
			turretTurnVel *= .85;
		}
		
		if (fireTimer > 0) {
			fireTimer -= 1;
		}
	}
	
	public void fireRound() {
		gp.rounds.add(new Round(gp, tankTurretX, tankTurretY, 50, totalTurretRot, index));
 		
 		particles(0, 0, 0, 0);
		fireTimer = 15;
	}
	
	public void update() {
	
		checkKeyPressed();
		
		tankTurretX = Math.cos(Math.toRadians(totalTurretRot-90))*45+x;
		tankTurretY = Math.sin(Math.toRadians(totalTurretRot-90))*45+y;
		
		actions();
		
		checkTankCollide();
		checkRoundCollide();
		
		setHitbox();
	}

	public void draw(Graphics2D g2) {
		
		AffineTransform origTransform = g2.getTransform();
		
		g2.rotate(Math.toRadians(rot), x, y);
		g2.drawImage(bottomImage, (int)(x - baseWidth/2), (int)(y - baseHeight/2), baseWidth, baseHeight, null);
		g2.setTransform(origTransform);
		
		g2.rotate(Math.toRadians(totalTurretRot), x, y);
		g2.drawImage(topImage, (int)(x - baseWidth/2), (int)(y - baseHeight/1.35 + fireTimer/3), baseWidth, baseHeight, null);
		g2.setTransform(origTransform);

		g2.setColor(Color.white);
		
		//g2.draw(hitboxArea);
	}
}
