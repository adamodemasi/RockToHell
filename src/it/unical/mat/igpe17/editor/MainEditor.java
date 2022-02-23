package it.unical.mat.igpe17.editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class MainEditor {
	
	PanelEditor panel;
	ToolsPanel tools;
	Container contentPane;
	JScrollPane scrollPane;

	public MainEditor() {

		final JFrame frame = new JFrame("Level Editor");
		frame.setLayout(new BorderLayout());
		
		tools = new ToolsPanel();
		panel = new PanelEditor(tools);

		contentPane = frame.getContentPane();

		panel.setPreferredSize(new Dimension(3600, 800));
		tools.setPreferredSize(new Dimension(250, 608));

		frame.setJMenuBar(panel.menuBar);

		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(tools, BorderLayout.EAST);

		scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        contentPane.add(scrollPane);
        contentPane.setPreferredSize(new Dimension(1000, 600));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					//DO_NOTHING_ON_CLOSE

		frame.addWindowListener(new WindowAdapter() {							//confirmation popup
		    @Override
		    public void windowClosing(WindowEvent we){

		    	String optButtons[] = {"Yes","No"};
		        int result = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Rock To Hell Level Editor",
		        						JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,optButtons,optButtons[1]);
		        if(result==JOptionPane.YES_OPTION)
		                    System.exit(0);
		    }});

		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		new MainEditor();
	}

}
