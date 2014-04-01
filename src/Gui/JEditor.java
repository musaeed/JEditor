package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import Components.BottomPanel;
import Components.CTabbedPane;
import Components.RibbonMenu;
import Menus.FrameMenu;

import component_listeners.frameExitListener;

public class JEditor {
	
	public static JFrame frame;
	
	public JEditor(){
		init();
	}
	
	public void init(){
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setJMenuBar(new FrameMenu());
		frame.add(new RibbonMenu() , BorderLayout.NORTH);
		frame.add(CTabbedPane.getInstance() , BorderLayout.CENTER);
		frame.add(new BottomPanel() , BorderLayout.SOUTH);
		frame.addWindowListener(new frameExitListener());
		frame.setSize(new Dimension(850,650));
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

}
