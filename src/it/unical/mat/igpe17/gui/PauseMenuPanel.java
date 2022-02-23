package it.unical.mat.igpe17.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PauseMenuPanel extends JPanel{

	private final WindowFrame windowFrame;

	public PauseMenuPanel(WindowFrame frame) {
		
		windowFrame = frame;
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getKeyCode();
				
				if (key == KeyEvent.VK_ESCAPE){
//					frame.startGame();
//					MainGame.frame.startGame();
					frame.startGame();
//					MainGame.frame.switchTo(new GamePanel(frame));
//					MainGame.frame.startGame();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) { }

			@Override
			public void keyTyped(KeyEvent arg0) { }
		});
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(ImageLoader.bg, 0, 0, this);

		g.setFont(new Font("Taranis", Font.BOLD, 80));
		g.setColor(Color.DARK_GRAY);
		g.drawString("PAUSE", 190, 250);

	}

	public WindowFrame getWindowFrame() {
		return windowFrame;
	}
	
	
}
