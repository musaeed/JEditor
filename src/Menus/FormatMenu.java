package Menus;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;

public class FormatMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	private CMenuItem chooseFont,colorOptions;
	private CMenu highlightingMode;

	public FormatMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
	}
	
	public void init(){
		chooseFont = new CMenuItem("Choose font", "choose the text font", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
		colorOptions = new CMenuItem("Color options", "configure color options", 'O', null);
		highlightingMode = new CMenu("Highlighting mode", 'H');
	}
	
	public void addToMenu(){
		add(chooseFont);
		add(colorOptions);
		addSeparator();
		add(highlightingMode);
	}
	
}
