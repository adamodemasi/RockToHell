package it.unical.mat.igpe17.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EndLevelPanel extends JPanel{
	
	private final WindowFrame windowFrame;

	public EndLevelPanel(WindowFrame frame) {

		windowFrame = frame;

		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
//					frame.backToMenu();
					windowFrame.switchTo(new PauseMenuPanel(frame));
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

		g.drawImage(ImageLoader.won, -50, 0, 1000, 600, null);

		g.setFont(new Font("Taranis", Font.BOLD, 80));
		g.setColor(Color.WHITE);
		g.drawString("YOU WON!", 90, 100);

		g.setFont(new Font("Taranis", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("Your score: " +
				windowFrame.getGame().getGameManager().getJames().scored(windowFrame.getGame().getTime()), 200, 450);
	}

	public WindowFrame getWindowFrame() {
		return windowFrame;
	}

}