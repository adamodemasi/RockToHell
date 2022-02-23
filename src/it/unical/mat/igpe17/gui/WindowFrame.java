package it.unical.mat.igpe17.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unical.mat.igpe17.common.HighscoreManager;
import it.unical.mat.igpe17.common.ProfilesManager;


@SuppressWarnings("serial")
public class WindowFrame extends JFrame{

	private JPanel contentPanel;
	
    private GamePanel game;
    private PauseMenuPanel internalMenu;
    private EndLevelPanel endLevelPanel;
    private PreGamePanel preGamePanel;
//	private ProfilesPanel profilesPanel;
//	private NewHighScorePanel highScorePanel;
//	private ScoreBoardPanel scoreBoardPanel;
//	private ProfilesManager profilesManager = new ProfilesManager();
//	private HighscoreManager scoreBoard = new HighscoreManager();

	public WindowFrame(){

		setTitle("ROCK TO HELL");
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);

		contentPanel = new JPanel(new BorderLayout());
		add(contentPanel);

		preGamePanel = new PreGamePanel(this);
		game = new GamePanel(this);
		internalMenu = new PauseMenuPanel(this);
		endLevelPanel = new EndLevelPanel(this);

//		setHighScorePanel(new NewHighScorePanel(this));
//		setLoadGamePanel(new LoadGamePanel(this));
//		setProfilesPanel(new ProfilesPanel(this));
//		setScoreBoardPanel(new ScoreBoardPanel(this));

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		switchTo(preGamePanel);

	}

    public void switchTo(final JPanel panel) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				contentPanel.invalidate();
				contentPanel.removeAll();
		    	contentPanel.add(panel);
		    	contentPanel.validate();
		    	contentPanel.updateUI();
				panel.requestFocus();
			}
		});

    }
    
    public void backToMenu() {
		switchTo(internalMenu);
		
	}
    
    public void startGame() {
	
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				contentPanel.invalidate();
				contentPanel.removeAll();
		    	contentPanel.add(game);
		    	contentPanel.validate();
		    	contentPanel.updateUI();
				game.requestFocus();
			}
		});
		
		game.start();

	}
    
    public void startEditorGame(GamePanel game) {
	
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				contentPanel.invalidate();
				contentPanel.removeAll();
		    	contentPanel.add(game);
		    	contentPanel.validate();
		    	contentPanel.updateUI();
				game.requestFocus();

			}
		});
		
		game.start();
	}
    
    public GamePanel getGame() {
		return game;
	}

	public void endLevelMenu() {
		
    	switchTo(endLevelPanel);

    }
	
//    public ProfilesManager getProfilesManager() {
//		return profilesManager;
//	}
//
//	public void setProfilesManager(ProfilesManager profilesManager) {
//		this.profilesManager = profilesManager;
//	}

//	public ProfilesPanel getProfilesPanel() {
//		return profilesPanel;
//	}
//
//	public void setProfilesPanel(ProfilesPanel profilesPanel) {
//		this.profilesPanel = profilesPanel;
//	}
//
//	public HighscoreManager getScoreBoard() {
//		return scoreBoard;
//	}
//
//	public void setScoreBoard(HighscoreManager scoreBoard) {
//		this.scoreBoard = scoreBoard;
//	}
//
//	public ScoreBoardPanel getScoreBoardPanel() {
//		return scoreBoardPanel;
//	}
//
//	public void setScoreBoardPanel(ScoreBoardPanel scoreBoardPanel) {
//		this.scoreBoardPanel = scoreBoardPanel;
//	}
//
//	public NewHighScorePanel getHighScorePanel() {
//		return highScorePanel;
//	}
//
//	public void setHighScorePanel(NewHighScorePanel highScorePanel) {
//		this.highScorePanel = highScorePanel;
//	}
//
//	public static void main(String[] args) {
//		
//		frame = new WindowFrame();
//		
//		frame.pack();
//		frame.setVisible(true);
//
//	}
}