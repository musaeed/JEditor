package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import alarm.AlarmDialog;

import Components.BottomPanel;
import Components.CTabbedPane;
import Components.RibbonMenu;
import Components.SearchPanel;
import Components.SplitPanelLeftComponent;
import IOFactory.Reader;
import Menus.FrameMenu;
import OptionDialogs.HelpDialog;

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
		frame = new JFrame();

		new Thread(new Runnable() {

			@Override
			public void run() {
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.addWindowListener(new frameListener());
				frame.addComponentListener(new frameListener());
				frame.setSize(new Dimension(1100,650));
				frame.setLocationRelativeTo(null);
				frame.setTitle("JEditor - Untitled");
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		}).start();

		frame.setLayout(new BorderLayout());

		new Thread(new Runnable() {

			@Override
			public void run() {
				frame.setJMenuBar(new FrameMenu());
				frame.add(RibbonMenu.getInstance(), BorderLayout.NORTH);
				frame.add(new BottomPanel() , BorderLayout.SOUTH);
			}
		}).start();


		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Setting up the alarm dialog...");
				AlarmDialog.getInstance();
				System.out.println("Setting up the search panel...");
				SearchPanel.getInstance();
				System.out.println("Setting up the help dialog...");
				HelpDialog.getInstance();
			}
		}).start();


		splitPane.setRightComponent(CTabbedPane.getInstance());		
		splitPane.setLeftComponent(SplitPanelLeftComponent.getInstance());
		frame.add(splitPane , BorderLayout.CENTER);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				splitPane.setDividerLocation(130);
				new TabStrokeListener();
			}
		}).start();

		for(int i = 0 ; i < args.length ; i++){

			if(i != 0 && i != args.length)
				CTabbedPane.getInstance().addTab("Untitled");

			Reader.loadFile(args[i]);

		}

		frame.setVisible(true);
		CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();

	}

}
