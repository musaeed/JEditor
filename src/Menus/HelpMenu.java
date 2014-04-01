package Menus;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;

public class HelpMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	private CMenuItem help;
	
	public HelpMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
	}
	
	public void init(){
		help = new CMenuItem("Help", "show help dialog", 'H', KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
	}

	public void addToMenu(){
		add(help);
	}

}
