package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;
import Components.RibbonMenu;
import OptionDialogs.MakeSuggestionDialog;

public class HelpMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	private CMenuItem help,makeSuggestion;
	
	public HelpMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
		addActions();
	}
	
	public void init(){
		help = new CMenuItem("Help", "show help dialog", 'H', KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		makeSuggestion = new CMenuItem("Make a suggestion", "help the developer improve JEditor", 'M', null);
	}

	public void addToMenu(){
		add(help);
		add(makeSuggestion);
	}
	
	public void addActions(){
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RibbonMenu.help.doClick();
			}
		});
		
		makeSuggestion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MakeSuggestionDialog.getInstance().show();
			}
		});
		
	}

}
