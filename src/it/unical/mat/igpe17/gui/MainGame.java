package it.unical.mat.igpe17.gui;

import javax.swing.SwingUtilities;


public class MainGame {

	public static WindowFrame frame;

	public MainGame() { }


	public static void main() {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame = new WindowFrame();
			}
		});

	}
}