package kz.azimkhan.game.engine.ui;

import javax.swing.JFrame;

import kz.azimkhan.game.engine.exception.GameException;
import kz.azimkhan.game.engine.listener.KeyListener;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9004826397768267830L;
	
	private Board b;
	public Frame() throws GameException{
		setTitle("The Game");
		b = new Board();
		b.setSize(getSize());
		this.add(b);
		
		setResizable(false);
		this.addKeyListener(new KeyListener(b));
	}
	

}
