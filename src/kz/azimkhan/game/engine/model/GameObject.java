package kz.azimkhan.game.engine.model;

import java.awt.image.BufferedImage;

public class GameObject {
	BufferedImage image;
	private int x;
	private int y;
	
	public GameObject(BufferedImage image) {
		super();
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	
}
