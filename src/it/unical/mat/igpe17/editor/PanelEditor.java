package it.unical.mat.igpe17.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import it.unical.mat.igpe17.elements.Direction;
import it.unical.mat.igpe17.elements.Goal;
import it.unical.mat.igpe17.elements.Note;
import it.unical.mat.igpe17.elements.ObjectId;
import it.unical.mat.igpe17.elements.Platform;
import it.unical.mat.igpe17.elements.Player;
import it.unical.mat.igpe17.elements.Spike;
import it.unical.mat.igpe17.elements.Wall;
import it.unical.mat.igpe17.gui.GameManager;
import it.unical.mat.igpe17.gui.GamePanel;
import it.unical.mat.igpe17.gui.ImageLoader;
import it.unical.mat.igpe17.gui.MainGame;
import it.unical.mat.igpe17.gui.WindowFrame;

@SuppressWarnings("serial")
public class PanelEditor extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	private final int width = 3400;
	private final int tile = 32;

	WindowFrame windowFrame;
	GameManager gameManager;
	ToolsPanel toolsPanel;
	BufferedImage bg;
	ImageLoader imageLoader;

	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JPopupMenu popup;

	String color;
	String direction;

	Player player;
	Goal goal;

	int x, y;

	private int pressedX1, pressedY1, releasedX2, releasedY2;
	
	////------------ SERVE PER L'UNDO --------------------////
	private LinkedList<GameManager> elementsQueue = new LinkedList<GameManager>(); 
	// ---------------------------------------------------//

	public PanelEditor(ToolsPanel tools) {
		setLayout(new BorderLayout());

		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(final MouseEvent e) {

				pressedX1 = e.getX();
				pressedY1 = e.getY();

				if (pressedX1 < ImageLoader.BG_W && pressedY1 < ImageLoader.BG_H) {

					switch(toolsPanel.getStatus()){

						case 'p':	// PLAYER
							gameManager.setJames(new Player(pressedX1, pressedY1 - ImageLoader.JAMES_H, ObjectId.Player));

							break;
						
						case 'w':	// WALL
							gameManager.getWalls().add(new Wall(pressedX1, pressedY1, ObjectId.Wall));

							break;

						case 'x':	// PLATFORM
							Platform tmp = createPlatformPopup();
							tmp.setX(pressedX1);
							tmp.setY(pressedY1);
							tmp.setID(ObjectId.Platform);
							
							gameManager.getPlatforms().add(tmp);

							break;

						case 'n':	// RED NOTE
							gameManager.getNotes().add(new Note(pressedX1, pressedY1 - ImageLoader.NOTE_H, ObjectId.Note, Color.RED));

							break;

						case 's':	// SPIKE
							gameManager.getSpikes().add(new Spike(pressedX1, pressedY1 - ImageLoader.SPIKE_H, ObjectId.Spike));

							break;

						case 'm': // BLU NOTE
							gameManager.getNotes().add(new Note(pressedX1, pressedY1 - ImageLoader.NOTE_H, ObjectId.Note, Color.BLUE));

							break;
							
						case 'b': // GREEN NOTE
							gameManager.getNotes().add(new Note(pressedX1, pressedY1 - ImageLoader.NOTE_H, ObjectId.Note, Color.GREEN));

							break;
							
						case 'g':	// GOAL
							gameManager.setGoal(new Goal(pressedX1, pressedY1 - ImageLoader.GOAL_H, ObjectId.Goal));

							break;

						default:
							break;
					}
				}
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				releasedX2 = e.getX();
				releasedY2 = e.getY();

				if (toolsPanel.getStatus() == 'w') {

					for (int x = pressedX1; x < releasedX2; x += ImageLoader.WALL_W) {
						for (int y = pressedY1; y < releasedY2; y += ImageLoader.WALL_H) {
							gameManager.getWalls().add(new Wall(x, y, ObjectId.Wall));
						}
					}
					elementsQueue.add(gameManager);
					resetMouseCoords();
				}
				repaint();
			}

		});

		addMouseMotionListener(this);
		createMenuBar();
		
		imageLoader = new ImageLoader();
		gameManager = new GameManager();
		toolsPanel = tools;
		gameManager.setJames(new Player(-300, -300, ObjectId.Player));
		gameManager.setGoal(new Goal(-300, -300, ObjectId.Goal));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

//		 g.setColor(Color.DARK_GRAY);
//		 g.fillRect(0, 0, 3600, 800);
		g.drawImage(ImageLoader.bg, 0, 0, this);

		for (int i = 0; i < width; i += tile) {

			g.setColor(Color.WHITE);
			g.fillRect(i, 0, 1, 800);
			g.fillRect(0, i, width, 1);
		}


// --- // -------------------------------------- DRAW ELEMENTS ------------------------------// --- //

// --------------- DRAW PLATFORMS ------------------ //

		for (int i = 0; i < gameManager.getPlatforms().size(); i++) {

			Platform tmp = gameManager.getPlatforms().get(i);
			g.drawImage(ImageLoader.platform, (int) tmp.getX(), (int) tmp.getY(), null);

		}
// --------------- END DRAW PLATFORMS ----------- //

// -------------- DRAW WALLS ------------------------ //
		for (int i = 0; i < gameManager.getWalls().size(); i++) {
			Wall tmp = gameManager.getWalls().get(i);
			g.drawImage(ImageLoader.wall, (int) tmp.getX(), (int) tmp.getY(), null);

		}
// -------------- END DRAW WALLS -------------------- //

// -------------- DRAW SPIKES ------------------------ //
		for (int i = 0; i < gameManager.getSpikes().size(); i++) {
			Spike tmp = gameManager.getSpikes().get(i);
			g.drawImage(ImageLoader.spikes[5], (int) tmp.getX(), (int) tmp.getY(), null);

		}
// -------------- END DRAW SPIKES -------------------- //

// -------------- DRAW NOTES ------------------------- //
		for (int i = 0; i < gameManager.getNotes().size(); i++) {
			Note tmp = gameManager.getNotes().get(i);

			if (tmp.getColor() == Color.BLUE)
				g.drawImage(ImageLoader.note_blue, (int) tmp.getX(), (int) tmp.getY(), null);
			else if (tmp.getColor() == Color.RED)
				g.drawImage(ImageLoader.note_red, (int) tmp.getX(), (int) tmp.getY(), null);
			else if (tmp.getColor() == Color.GREEN)
				g.drawImage(ImageLoader.note_green, (int) tmp.getX(), (int) tmp.getY(), null);
		}
// -------------- END DRAW NOTES --------------------- //

// --------------- DRAW PLAYER ------------------ //
		g.drawImage(ImageLoader.james, (int) gameManager.getJames().getX(), (int) gameManager.getJames().getY(), null);

// -------------- END DRAW PLAYER ------------------- //
		
// -------------- GOAL DRAW ------------------------- //
		g.drawImage(ImageLoader.goal, (int) gameManager.getGoal().getX(), (int) gameManager.getGoal().getY(), null);

		
		g.dispose();
	}

	public void cleanScreen() {

		gameManager = new GameManager();

		gameManager.setJames(new Player(-300, -300, ObjectId.Player));
		gameManager.setGoal(new Goal(-300, -300, ObjectId.Goal));

		repaint();

		elementsQueue.add(gameManager);
	}
	
	private void undo() {

		requestFocusInWindow();
		
		elementsQueue.removeLast();

		gameManager = elementsQueue.getLast();
		
		repaint();

	}

	public void createMenuBar() {

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		menuItem = new JMenuItem("New", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.setIcon(new ImageIcon("res/icon/newfile.png"));
		menu.add(menuItem);

		menuItem = new JMenuItem("Open...", KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuItem.setIcon(new ImageIcon("res/icon/edit.png"));

		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				gameManager = loadLevel();
				repaint();
			}
		});

		menu.add(menuItem);
		menuItem = new JMenuItem("Save Level", KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItem.setIcon(new ImageIcon("res/icon/save.png"));

		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveLevel();
			}
		});

		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String optButtons[] = { "Yes", "No" };
				int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Rock To Hell Level Editor",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, optButtons, optButtons[1]);
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});

		menu.add(menuItem);

		// SECONDO MENU
		menu = new JMenu("Try");
		menu.setMnemonic(KeyEvent.VK_T);
		menuBar.add(menu);

		menuItem = new JMenuItem("Test Level");
		
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				windowFrame = new WindowFrame();
				GamePanel gp = new GamePanel(windowFrame);
				gp.setGameManager(gameManager);
//				windowFrame.switchTo(gp);
				windowFrame.startEditorGame(gp);
			}
		});
		
		
		menu.add(menuItem);
	}

	private void saveLevel() {

		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File("myLevel.sav"));
		fileChooser.setCurrentDirectory(new File("./levels/"));

		final FileNameExtensionFilter filter = new FileNameExtensionFilter("SAV file", "sav");
		fileChooser.setFileFilter(filter);

		final int response = fileChooser.showSaveDialog(null);

		if (response == JFileChooser.APPROVE_OPTION) {
			try {
				FileOutputStream fileOut = new FileOutputStream(fileChooser.getSelectedFile().getName());
				ObjectOutputStream outStream = new ObjectOutputStream(fileOut);

				outStream.writeObject(gameManager);
				outStream.close();
				fileOut.close();

			} catch (IOException e) { e.printStackTrace(); }
		}
	}

	private GameManager loadLevel() {

		GameManager gm = new GameManager();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./levels/"));
		final FileNameExtensionFilter filter = new FileNameExtensionFilter("SAV file", "sav");
		fileChooser.setFileFilter(filter);

		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {

//			try {
//				Desktop.getDesktop().open(fileChooser.getSelectedFile());
				try {

					FileInputStream fileIn = new FileInputStream(fileChooser.getSelectedFile());
					ObjectInputStream in = new ObjectInputStream(fileIn);

					gm = (GameManager) in.readObject();

					in.close();

					fileIn.close();

				} catch (IOException i) {
					i.printStackTrace();
					return null;

				} catch (ClassNotFoundException c) {
					c.printStackTrace();
					return null;
				}
		}

		return gm;
	}

	public Platform createPlatformPopup() {
		
		Platform tmp = new Platform();
		
		JDialog.setDefaultLookAndFeelDecorated(true);

		Object[] colors = { "Blue", "Red", "Green" };
		String initialSelection = "Color";
		color = (String) JOptionPane.showInputDialog(null, "Choose the color", "New Platform",
				JOptionPane.QUESTION_MESSAGE, null, colors, initialSelection);
		switch (color){
			case "Blue" :
				tmp.setColor(Color.BLUE);
				break;
			case "Red" :
				tmp.setColor(Color.RED);
				break;
			case "Green" :
				tmp.setColor(Color.GREEN);
				break;
			default:
				break;
		}

		JDialog.setDefaultLookAndFeelDecorated(true);
		
		Object[] directions = { "Up", "Down", "Right", "Left" };
		String dir = "Direction";
		direction = (String) JOptionPane.showInputDialog(null, "Choose the direction", "New Platform",
				JOptionPane.QUESTION_MESSAGE, null, directions, dir);
		switch (direction){
			case "Up" :
				tmp.setDirection(Direction.UP);
				break;
			case "Down" :
				tmp.setDirection(Direction.DOWN);
				break;
			case "Right" :
				tmp.setDirection(Direction.RIGHT);
				break;
			case "Left" :
				tmp.setDirection(Direction.LEFT);
				break;
			default:
				break;
		}

		JFrame frame = new JFrame();
		int number = -1;
		String errorMessage = "";
		do {
			String result = JOptionPane.showInputDialog(frame, errorMessage + "Enter radius:");
			try {
				number = Integer.parseInt(result);

				if (number < 0) {
					errorMessage = "Invalid input.\nInsert a positive value.\n\n";
				} else {
					errorMessage = "";
				}
			} catch (NumberFormatException e) {
				errorMessage = "Invalid input.\nInsert an integer value.\n\n";
			}
		} while (!errorMessage.isEmpty());
		tmp.setRadius(number);

		return tmp;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		if(toolsPanel.getStatus() == 'c' ){
			String optButtons[] = { "Yes", "No" };
			int sure = JOptionPane.showOptionDialog(null, "Are you sure you want to reset?",
					"Rock To Hell Level Editor", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
					optButtons, optButtons[1]);
			if (sure == JOptionPane.YES_OPTION) {
				cleanScreen();
			}
			toolsPanel.setStatus('k');		// ALTRIMENTI VA IN LOOP
		}

		else if(toolsPanel.getStatus() == 'u') {
			if (elementsQueue.size() > 1) {
			String optButtons[] = { "Yes", "No" };
			int sure = JOptionPane.showOptionDialog(null, "Undo last action?", "Rock To Hell Level Editor",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, optButtons, optButtons[1]);
				if (sure == JOptionPane.YES_OPTION) {
					undo();
				}
			toolsPanel.setStatus('k');			// ALTRIMENTI VA IN LOOP
			}
			else{
				JOptionPane.showMessageDialog(null, "Nothing to delete!");
				toolsPanel.setStatus('k');		// ALTRIMENTI VA IN LOOP
			}
		}
	}

	private void resetMouseCoords() {
		pressedX1 = -1;
		pressedY1 = -1;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { }
	@Override
	public void mouseClicked(MouseEvent e) { }
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
	@Override
	public void mouseReleased(MouseEvent e) { }
	@Override
	public void mouseDragged(MouseEvent arg0) { }
	@Override
	public void mousePressed(MouseEvent e) { }

}