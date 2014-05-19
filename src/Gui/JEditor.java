package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import Components.BottomPanel;
import Components.CTabbedPane;
import Components.FileViewer;
import Components.RibbonMenu;
import IOFactory.Reader;
import Menus.FrameMenu;

import component_listeners.TabStrokeListener;
import component_listeners.frameExitListener;

public class JEditor {

	public static JFrame frame;
	public static JSplitPane splitPane;

	public JEditor(String args[]){
		init(args);
	}

	public void init(final String args[]){
		splitPane = new JSplitPane();
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setJMenuBar(new FrameMenu());
		frame.add(RibbonMenu.getInstance(), BorderLayout.NORTH);
		frame.add(new BottomPanel() , BorderLayout.SOUTH);
		splitPane.setRightComponent(CTabbedPane.getInstance());		
		splitPane.setLeftComponent(new JScrollPane(FileViewer.getInstance().getTree()));
		splitPane.setDividerLocation(130);
		frame.add( splitPane , BorderLayout.CENTER);
		frame.addWindowListener(new frameExitListener());
		frame.setSize(new Dimension(1000,650));
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("JEditor - Untitled");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		new TabStrokeListener();
		
		for(int i = 0 ; i < args.length ; i++){
			
			if(i != 0 && i != args.length)
			CTabbedPane.getInstance().addTab("Untitled");
			
			Reader.loadFile(args[i]);
			
		}
		
		frame.setVisible(true);
		

	}

}
