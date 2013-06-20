package kz.azimkhan.game.engine.listener;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import kz.azimkhan.game.engine.model.GameObject;
import kz.azimkhan.game.engine.ui.Board;

public class KeyListener extends Thread implements java.awt.event.KeyListener {

	private Board b;
	private GameObject go;
	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;

	public KeyListener(Board b) {
		super();
		this.b = b;
		go = b.ob;
		start();

	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			right = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = true;
			down = false;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			up = false;
			break;
		case KeyEvent.VK_SPACE:
			b.t.blink();
			break;
		default:
			break;
		}
		

		notify();
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {/* Not used */
	}

	private void left() {
		int x = go.getX();
		if (x <= 0) {
			x = b.getWidth();
		} else {
			x -= 1;
		}
		go.setX(x);
		b.repaint();
	}

	private void up() {
		int y = go.getY();
		if (y <= 0) {
			y = b.getHeight();
		} else {
			y -= 1;
		}
		go.setY(y);
		b.repaint();
	}

	private void right() {
		int x = go.getX();
		if (x >= b.getWidth()) {
			x = 0;
		} else {
			x += 1;
		}
		go.setX(x);
		b.repaint();
	}

	private void down() {
		int y = go.getY();
		if (y >= b.getHeight()) {
			y = 0;
		} else {
			y += 1;
		}
		go.setY(y);
		b.repaint();
	}

	@Override
	public void run() {
		while (true) {
			if (left || right || up || down) {
				if (left) {
					left();
				}

				if (right) {
					right();
				}

				if (up) {
					up();
				}

				if (down) {
					down();
				}

				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					synchronized (this) {
						this.wait();
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
