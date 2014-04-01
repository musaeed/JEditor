package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;
import IOFactory.Reader;

public class FileMenu extends CMenu{
	
	private static final long serialVersionUID = 1L;
	private CMenuItem newTab,open,reload,save,saveAs,close,closeAll,print,exit;

	public FileMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
		addActions();
	}
	
	public void init(){
		newTab = new CMenuItem("New Tab", "open a new tab", 'N', KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		open = new CMenuItem("Open", "open a new file", 'O', KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		reload = new CMenuItem("Reload", "reload the current file", 'R', KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		save = new CMenuItem("Save", "save the current file", 'S', KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveAs = new CMenuItem("Save as", "save the file with a new name", 'A', KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
		close = new CMenuItem("Close", "close the current file", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
		closeAll = new CMenuItem("Close all", "close all the files", 'L', KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
		print = new CMenuItem("Print", "print the content of the current tab", 'P', KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		exit = new CMenuItem("Exit", "exit the application", 'E', KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
	}

	public void addToMenu(){
		add(newTab);
		add(open);
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
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reader.openDialog();
			}
		});
	}


}
