package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;

import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;
import Components.CTabbedPane;
import IOFactory.Reader;
import IOFactory.Writer;
import OptionDialogs.HtmlDialog;
import Utility.EditorUtilities;

public class FileMenu extends CMenu{
	
	private static final long serialVersionUID = 1L;
	private CMenuItem newTab,open,openWeb,reload,save,saveAs,close,closeAll,print,exit;
	public static CMenu recentFiles = new CMenu("Recent files", 'R'); 

	public FileMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
		addActions();
	}
	
	public void init(){
		newTab = new CMenuItem("New Tab", "open a new tab", 'N', KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		open = new CMenuItem("Open", "open a new file", 'O', KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		openWeb = new CMenuItem("Open web html ..", "open the website as html in the editor", 'W', null);
		reload = new CMenuItem("Reload", "reload the current file", 'R', KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		save = new CMenuItem("Save", "save the current file", 'S', KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveAs = new CMenuItem("Save as", "save the file with a new name", 'A', KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		close = new CMenuItem("Close", "close the current file", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
		closeAll = new CMenuItem("Close all", "close all the files", 'L', KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		print = new CMenuItem("Print", "print the content of the current tab", 'P', KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		exit = new CMenuItem("Exit", "exit the application", 'E', KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
	}

	public void addToMenu(){
		add(newTab);
		add(open);
		add(openWeb);
		add(recentFiles);
		add(reload);
		addSeparator();
		add(save);
		add(saveAs);
		addSeparator();
		add(close);
		add(closeAll);
		addSeparator();
		add(print);
		add(exit);
	}
	
	public void addActions(){
		
		newTab.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CTabbedPane.getInstance().addTab("Untitled");
			}
		});
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reader.openDialog();
			}
		});
		
		openWeb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new HtmlDialog().setVisible(true);
			}
		});
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
					return;
				}
				
				Reader.loadFile(CTabbedPane.getInstance().getPanel().getCurrentFilePath());
			}
		});
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
					saveAs.doClick();
					return;
				}
				
				Writer.saveFile(CTabbedPane.getInstance().getPanel().getCurrentFilePath());
			}
		});
		
		saveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Writer.showSaveDialog();
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CTabbedPane.getInstance().closeCurrentTab();
			}
		});
		
		closeAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CTabbedPane.getInstance().closeAllTabs();
				
			}
		});
		
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							CTabbedPane.getInstance().getPanel().getTextArea().print();
						} catch (PrinterException e) {
							e.printStackTrace();
						}
					}
				});
				
				thread.start();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EditorUtilities.exitApplication();
			}
		});
		
	}


}
