package it.unical.mat.igpe17.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ToolsPanel extends JPanel implements ActionListener {

	protected JButton james, wall, platform, spike, note_blue, note_red, note_green, goal, undo, clean;
	PanelEditor panel;

	private char status;

	public ToolsPanel() {
	
		panel = new PanelEditor(this);
		
		james = new JButton();
		wall = new JButton();
		spike = new JButton();
		note_blue = new JButton();
		note_green = new JButton("Green Note");
		note_red = new JButton();
		goal = new JButton("Goal");
		platform = new JButton("Platform");
		clean = new JButton();
		undo = new JButton();

		james.addActionListener(this);
		james.setIcon(new ImageIcon("res/icon/james.png"));
		add(james);

		wall.addActionListener(this);
		wall.setIcon(new ImageIcon("res/icon/wall.png"));
		add(wall);

		spike.addActionListener(this);
		spike.setIcon(new ImageIcon("res/icon/spike.png"));
		add(spike);

		note_blue.addActionListener(this);
		note_blue.setIcon(new ImageIcon("res/icon/note_blue.png"));
		add(note_blue);

		note_red.addActionListener(this);
		note_red.setIcon(new ImageIcon("res/icon/note_red.png"));
		add(note_red);
		
		note_green.addActionListener(this);
		// note_green.setIcon(new ImageIcon("res/icon/XX.png"));
		add(note_green);

		goal.addActionListener(this);
		goal.setIcon(new ImageIcon("res/icon/goal.png"));
		add(goal);

		platform.addActionListener(this);
		platform.setIcon(new ImageIcon("res/icon/plat.png"));
		add(platform);
		
		clean.addActionListener(this);
		clean.setIcon(new ImageIcon("res/icon/clean.png"));
		add(clean);
		
		undo.addActionListener(this);
		undo.setIcon(new ImageIcon("res/icon/undo.png"));
		add(undo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
// --------------- PLAYER BUTTON --------------------//
		if (e.getSource() == james) {
			status = 'p';
			
		}
		
// --------------- SPIKE BUTTON --------------------//
		if (e.getSource() == spike) {
			status = 's';
			
		}
// --------------- GOAL BUTTON --------------------//
		if (e.getSource() == goal) {
			status = 'g';
			
		}
				
// --------------- WALLS BUTTON ---------------------//
		if (e.getSource() == wall) {
			status = 'w';
			
		}
// --------------- PLATFORM BUTTON ------------------//
		if (e.getSource() == platform) {
			status = 'x';
			
		}
// --------------- BLU NOTE BUTTON ------------------//
		if (e.getSource() == note_blue) {
			status = 'm';
			
		}
// --------------- RED NOTE BUTTON -------------------//
		if (e.getSource() == note_red) {
			status = 'n';
			
		}
// --------------- GREEN NOTE BUTTON -----------------//
		if (e.getSource() == note_green) {
			status = 'b';
			
		}
// ------------------ UNDO BUTTON ------------------//
		if (e.getSource() == undo) {
			status = 'u';

		}
// ------------------ CLEAN BUTTON ------------------//
		if (e.getSource() == clean) {
			status = 'c';

		}

	}

	public char getStatus() {
		return status;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}

}
