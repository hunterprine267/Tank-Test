package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean	upPressed1, downPressed1,
					leftPressed1, rightPressed1,
					rotLeftPressed1, rotRightPressed1,
					scaleUpPressed1, scaleDownPressed1,
					firePressed1;
	
	public boolean	upPressed2, downPressed2,
					leftPressed2, rightPressed2,
					rotLeftPressed2, rotRightPressed2,
					scaleUpPressed2, scaleDownPressed2,
					firePressed2;
	
	public final int 	upButton1 = KeyEvent.VK_W,
						downButton1 = KeyEvent.VK_S,
						leftButton1 = KeyEvent.VK_A,
						rightButton1 = KeyEvent.VK_D,
						rotLeftButton1 = KeyEvent.VK_Q,
						rotRightButton1 = KeyEvent.VK_E,
						scaleUpButton1 = KeyEvent.VK_2,
						scaleDownButton1 = KeyEvent.VK_3,
						fireButton1= KeyEvent.VK_F;
	
	public final int 	upButton2 = KeyEvent.VK_I,
						downButton2 = KeyEvent.VK_K,
						leftButton2 = KeyEvent.VK_J,
						rightButton2 = KeyEvent.VK_L,
						rotLeftButton2 = KeyEvent.VK_U,
						rotRightButton2 = KeyEvent.VK_O,
						scaleUpButton2 = KeyEvent.VK_8,
						scaleDownButton2 = KeyEvent.VK_9,
						fireButton2= KeyEvent.VK_H;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();

		switch (code) {
		case upButton1: upPressed1 = true;
			break;
		case downButton1: downPressed1 = true;
		}
		
		switch (code) {
		case leftButton1: leftPressed1 = true;
			break;
		case rightButton1: rightPressed1 = true;
		}
		
		switch (code) {
		case rotLeftButton1: rotLeftPressed1 = true;
			break;
		case rotRightButton1: rotRightPressed1 = true;
		}
		
		switch (code) {
		case scaleUpButton1: scaleUpPressed1 = true;
			break;
		case scaleDownButton1: scaleDownPressed1 = true;
		}
		
		if (code == fireButton1) {
			firePressed1 = true;
		}
		
		switch (code) {
		case upButton2: upPressed2 = true;
			break;
		case downButton2: downPressed2 = true;
		}
		
		switch (code) {
		case leftButton2: leftPressed2 = true;
			break;
		case rightButton2: rightPressed2 = true;
		}
		
		switch (code) {
		case rotLeftButton2: rotLeftPressed2 = true;
			break;
		case rotRightButton2: rotRightPressed2 = true;
		}
		
		switch (code) {
		case scaleUpButton2: scaleUpPressed2 = true;
			break;
		case scaleDownButton2: scaleDownPressed2 = true;
		}
		
		if (code == fireButton2) {
			firePressed2 = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		switch (code) {
		case upButton1: upPressed1 = false;
			break;
		case downButton1: downPressed1 = false;
		}
		
		switch (code) {
		case leftButton1: leftPressed1 = false;
			break;
		case rightButton1: rightPressed1 = false;
		}
		
		switch (code) {
		case rotLeftButton1: rotLeftPressed1 = false;
			break;
		case rotRightButton1: rotRightPressed1 = false;
		}
		
		switch (code) {
		case scaleUpButton1: scaleUpPressed1 = false;
			break;
		case scaleDownButton1: scaleDownPressed1 = false;
		}
		
		if (code == fireButton1) {
			firePressed1 = false;
		}
		
		switch (code) {
		case upButton2: upPressed2 = false;
			break;
		case downButton2: downPressed2 = false;
		}
		
		switch (code) {
		case leftButton2: leftPressed2 = false;
			break;
		case rightButton2: rightPressed2 = false;
		}
		
		switch (code) {
		case rotLeftButton2: rotLeftPressed2 = false;
			break;
		case rotRightButton2: rotRightPressed2 = false;
		}
		
		switch (code) {
		case scaleUpButton2: scaleUpPressed2 = false;
			break;
		case scaleDownButton2: scaleDownPressed2 = false;
		}
		
		if (code == fireButton2) {
			firePressed2 = false;
		}
	}
}

