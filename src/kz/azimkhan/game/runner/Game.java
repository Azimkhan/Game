package kz.azimkhan.game.runner;

import javax.swing.JFrame;

import kz.azimkhan.game.engine.exception.GameException;
import kz.azimkhan.game.engine.ui.Frame;

public class Game {
	public static void main(String[] args){
		try {
			Frame f = new Frame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
			f.setSize(640, 480);
			
		} catch (GameException e) {
			System.err.println(e.getMessage());
		}
	}
}
