package kz.azimkhan.game.engine.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import kz.azimkhan.game.engine.exception.GameException;
import kz.azimkhan.game.engine.listener.KeyListener;
import kz.azimkhan.game.engine.model.GameObject;
import kz.azimkhan.game.resource.R;

public class Board extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6512183621000695432L;
	public GameObject ob;

	BufferedImage bg;
	private AlphaComposite alphaComposite;
	public OpacityTask t = new OpacityTask();
	
	public Board() throws GameException{
		
		t.start();
		try {
			ob = new GameObject(ImageIO.read(R.spceshipUrl()));
			
			ob.setY(50);
			bg = ImageIO.read(R.bgUrl());
		} catch (IOException e) {
			throw new GameException(e.getMessage(), e.getCause());
		}
		
		
		setDoubleBuffered(true);
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
		
		
	}
	
	public synchronized void setOpacity(float value){
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, value);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); 
		
		Graphics2D g2d = (Graphics2D) g;
		Composite o = g2d.getComposite();
		g2d.drawImage(bg, null, 0, 0);
		g2d.setComposite(alphaComposite);
		g2d.drawImage(ob.getImage(), null, ob.getX(), ob.getY());
		g2d.setComposite(o);
		Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}

	public class OpacityTask extends Thread{
		private float value = 0f;
		boolean inc = true;
		boolean running = false;
		long speed = 25;
		int cycles = 4;
		public boolean alive = true;
		
		@Override
		public void run() {
			
			
			while(alive){
				if (running){
					for(int i = 0; i < cycles; i++){
						float value = 1f;
						while (value > 0.1f){
							value -= 0.09f;
							try {
								Thread.sleep(speed);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							setOpacity(value);
							repaint();
						}
						
						while (value < 0.9f){
							value += 0.09f;
							try {
								Thread.sleep(speed);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							setOpacity(value);
							repaint();
						}
						
					}
					
					running = false;
				} else{
					try {
						synchronized (this) {
							this.wait();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			setOpacity(value);
		}
		
		public synchronized void blink(){
			if (running == false){
				running = true;
				synchronized (this) {

					this.notify();
				}
			}
			
		}
		
	}

	
}
