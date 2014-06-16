package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;
import Components.CTabbedPane;
import Components.RibbonMenu;
import Gui.JEditor;
import IOFactory.Reader;
import MenuEvents.FileMenuEvent;
import OptionDialogs.HtmlDialog;
import Utility.EditorUtilities;
import Utility.ImageLoader;

public class FileMenu extends CMenu{
	
	private static final long serialVersionUID = 1L;
	public static CMenuItem newTab,open,openWeb,reload,save,saveAs,close,closeAll,delete,print,exit;
	public static CMenu recentFiles = new CMenu("Recent files", 'R'); 

	public FileMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
		addActions();
		addIcons();
		addMenuListener(new FileMenuEvent());
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
		delete = new CMenuItem("Delete", "delete the current fule", 'D', KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
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
		add(delete);
		addSeparator();
		add(print);
		add(exit);
	}
	
	public void addIcons(){
		newTab.setIcon(ImageLoader.loadImage("images_small/newtab.png"));
		open.setIcon(ImageLoader.loadImage("images_small/open.gif"));
		reload.setIcon(ImageLoader.loadImage("images_small/reload.png"));
		save.setIcon(ImageLoader.loadImage("images_small/save.png"));
		saveAs.setIcon(ImageLoader.loadImage("images_small/saveas.png"));
		print.setIcon(ImageLoader.loadImage("images_small/print.png"));
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
				RibbonMenu.save.doClick();
			}
		});
		
		saveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RibbonMenu.saveas.doClick();
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
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(JEditor.frame, "Are you sure you want to delete the current file from the disk?", "Delete confirm", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if(result == JOptionPane.YES_OPTION){
					
					if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
						return;
					}
					
					if(!new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).delete()){
						JOptionPane.showMessageDialog(JEditor.frame, "Unable to delete the file. Please try later.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					close.doClick();
				}
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
