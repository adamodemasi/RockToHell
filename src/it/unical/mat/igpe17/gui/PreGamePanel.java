package it.unical.mat.igpe17.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class PreGamePanel extends JPanel{

	private final WindowFrame windowFrame;
	
	ImageIcon selected;
	
	public PreGamePanel(WindowFrame frame) {
		windowFrame = frame;
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					windowFrame.startGame();
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

		g.drawImage(ImageLoader.info, -100, 0, 1000, 580, null);
		
		JButton playButton = new JButton("Play Button");
		ImageIcon k = new ImageIcon("res/menu/buttonPlay.png");
		playButton = new JButton(k);
		playButton.setBackground(null);
		playButton.setBounds(20, 20, 300, 55);
		playButton.setBorder(null);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		
		selected = new ImageIcon("res/menu/buttonPlaySel.png");
		playButton.setRolloverIcon(selected);

		this.add(playButton);
		playButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
//				windowFrame.getSoundGame().playDragMouseSound();
			}
			@Override
			public void mouseExited(MouseEvent e) {	}
			@Override
			public void mousePressed(MouseEvent e) {
//					windowFrame.getSoundGame().playClickMouseSound();

				JOptionPane namePopup = new JOptionPane();
				namePopup.setLayout(new BorderLayout());

				namePopup.add(new JLabel("Choose an Username"), BorderLayout.CENTER);

//				final String name = JOptionPane.showInputDialog(null, namePopup);
//				final String namePlayer = JOptionPane.showInputDialog(namePopup, "Choose an username", "New Game", JOptionPane.PLAIN_MESSAGE);
//				boolean profileNotExists = true;
//
//				if (namePlayer.length() > 0) {
//
//					for (int i = 0; i < windowFrame.getProfilesManager().getProfiles().size(); i++) {
//
//						if (windowFrame.getProfilesManager().getProfiles().get(i).getPlayer().equals(namePlayer)) {
//							profileNotExists = false;
//						}
//					}
//
//					if (profileNotExists) {
//						windowFrame.getPlayer().setPlayer(namePlayer);
//						windowFrame.setLevel(windowFrame.getPlayer().getLevel());
//						windowFrame.startGame();
//					}
//			 else
//						JOptionPane.showMessageDialog(windowFrame, "Profile already exists", "WARNING", JOptionPane.WARNING_MESSAGE);
//
//				}
//				else
//					JOptionPane.showMessageDialog(windowFrame, "Name field could not be empty");
//
//			}
//				windowFrame.getSoundGame().playClickMouseSound();
//				windowFrame.startGame();
		}
			@Override
			public void mouseReleased(MouseEvent e) { }
		});

		JButton loadButton = new JButton("Load Button");
		ImageIcon j = new ImageIcon("res/menu/buttonLoad.png");
		loadButton = new JButton(j);
		loadButton.setBackground(null);
		loadButton.setBounds(350, 75, 300, 55);
		loadButton.setBorder(null);
		loadButton.setOpaque(false);
		loadButton.setContentAreaFilled(false);
		loadButton.setBorderPainted(false);
		
		selected = new ImageIcon("res/menu/buttonLoadSel.png");
		loadButton.setRolloverIcon(selected);

		this.add(loadButton);

		loadButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) {
//				windowFrame.getSoundGame().playDragMouseSound();
			}
			@Override
			public void mouseExited(MouseEvent e) {	}
			@Override
			public void mousePressed(MouseEvent e) {
//				windowFrame.getSoundGame().playClickMouseSound();
//				windowFrame.switchTo(new LoadGamePanel(windowFrame));
				GameManager gm = new GameManager();
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("./levels/"));
				final FileNameExtensionFilter filter = new FileNameExtensionFilter("SAV file", "sav");
				fileChooser.setFileFilter(filter);

				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {

					try {

						FileInputStream fileIn = new FileInputStream(fileChooser.getSelectedFile());
						ObjectInputStream in = new ObjectInputStream(fileIn);

						gm = (GameManager) in.readObject();

						in.close();

						fileIn.close();

					} catch (IOException i) {
						i.printStackTrace();

					} catch (ClassNotFoundException c) {
						c.printStackTrace();
					}
					finally{
						
						windowFrame.getGame().setGameManager(gm);
//						windowFrame.getGame().getGameManager().setJames(gm.getJames());

						windowFrame.startGame();
					}
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) { }
		});
	}

	public WindowFrame getWindowFrame() {
		return windowFrame;
	}

}
