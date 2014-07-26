package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import Components.BottomPanel;
import Components.CTabbedPane;
import Components.RibbonMenu;
import Components.SplitPanelLeftComponent;
import IOFactory.Reader;
import Menus.FrameMenu;

import component_listeners.TabStrokeListener;
import component_listeners.frameListener;

public class JEditor {

	public static JFrame frame;
	public static JSplitPane splitPane;
	public static double AppVersion = 1.4;

	public JEditor(String args[]){
		init(args);
	}

	public void init(final String args[]){
		splitPane = new JSplitPane();
		splitPane.setDividerSize(4);
		frame = new JFrame();
		frame.setSize(new Dimension(1000,650));
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				frame.setJMenuBar(new FrameMenu());
				frame.add(RibbonMenu.getInstance(), BorderLayout.NORTH);
				frame.add(new BottomPanel() , BorderLayout.SOUTH);
			}
		});
		
		thread.start();
		

		splitPane.setRightComponent(CTabbedPane.getInstance());		
		splitPane.setLeftComponent(SplitPanelLeftComponent.getInstance());
		splitPane.setDividerLocation(130);
		frame.add(splitPane , BorderLayout.CENTER);
		frame.addWindowListener(new frameListener());
		frame.addComponentListener(new frameListener());
		frame.setSize(new Dimension(1000,650));
		frame.setLocationRelativeTo(null);
		frame.setTitle("JEditor - Untitled");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		new TabStrokeListener();
		
		for(int i = 0 ; i < args.length ; i++){
			
			if(i != 0 && i != args.length)
			CTabbedPane.getInstance().addTab("Untitled");
			
			Reader.loadFile(args[i]);
			
		}

		frame.setVisible(true);
		frame.validate();
		CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();

	}

}
