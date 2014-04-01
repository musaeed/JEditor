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
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				frame.setJMenuBar(new FrameMenu());
				frame.add(new RibbonMenu() , BorderLayout.NORTH);
				frame.add(new BottomPanel() , BorderLayout.SOUTH);
			}
		});
		thread.start();
		
		frame.add(CTabbedPane.getInstance() , BorderLayout.CENTER);		
		frame.addWindowListener(new frameExitListener());
		frame.setSize(new Dimension(950,650));
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("JEditor - Untitled");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}

}
