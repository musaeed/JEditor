package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import say.swing.JFontChooser;

import Components.CMenu;
import Components.CMenuItem;
import Components.CTabbedPane;
import Gui.JEditor;
import OptionDialogs.ColorOptionDialog;

public class FormatMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	private CMenuItem chooseFont,colorOptions;
	private CMenu highlightingMode;

	public FormatMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
		addActions();
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
	
	public void addActions(){
		chooseFont.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFontChooser fchooser = new JFontChooser();
				fchooser.setSelectedFont(CTabbedPane.getInstance().getPanel().getFont());
				int result = fchooser.showDialog(JEditor.frame);
				
				if(result == JFontChooser.OK_OPTION){
					CTabbedPane.getInstance().getPanel().getTextArea().setFont(fchooser.getSelectedFont());
				}
				
			}
		});
		
		colorOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ColorOptionDialog().setVisible(true);
			}
		});
	}
	
}
