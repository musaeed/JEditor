package Menus;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import Components.CCheckBoxMenuItem;
import Components.CMenu;
import Components.CMenuItem;

public class ToolMenu extends CMenu{
	

	private static final long serialVersionUID = 1L;
	private CCheckBoxMenuItem hulnumbers;
	private CMenuItem stats, search, sreplace, searchInternet , gotoLine, toLower, toUpper;
	
	
	public ToolMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
	}
	
	public void init(){
		
		hulnumbers = new CCheckBoxMenuItem("Show line numbers", "hide or unhide the line numbers");
		
		stats = new CMenuItem("Document statistics", "shows the statistics for the current document", 'D', KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		search = new CMenuItem("Search", "search for text in the current documenr", 'S', KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		sreplace = new CMenuItem("Search and replace", "search and replace the text", 'R', null);
		searchInternet = new CMenuItem("Search Internet", "search for content on the internet", '1', null);
		gotoLine = new CMenuItem("Goto line", "go to a specific line number", 'G', KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		toLower = new CMenuItem("To lower", "set all the text to lower case characters", '1', null);
		toUpper = new CMenuItem("To upper", "set all the text to upper case letters", '1', null);
	}
	
	public void addToMenu(){
		add(hulnumbers);
		addSeparator();
		add(stats);
		add(search);
		add(sreplace);
		add(searchInternet);
		add(gotoLine);
		addSeparator();
		add(toLower);
		add(toUpper);
	}


}
