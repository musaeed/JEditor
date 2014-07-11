package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;
import Components.CTabbedPane;
import Gui.JEditor;
import MenuEvents.CurrentFileMenuEvent;
import OptionDialogs.FilePropertiesDialog;
import Utility.ImageLoader;

public class CurrentFileMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	public static CMenuItem delete,details;

	public CurrentFileMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addActions();
		addToMenu();
		addMenuListener(new CurrentFileMenuEvent());
		addIcons();
	}
	
	public void init(){
		
		delete = new CMenuItem("Delete", "delete the current file", 'D', KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		details = new CMenuItem("File properties", "see the properties of the current file", 'F', KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		
	}
	
	public void addActions(){
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(JEditor.frame, "Are you sure you want to delete the current file from the disk?", "Delete confirm", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if(result == JOptionPane.YES_OPTION){
					
					if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
						return;
					}
					
					if(!new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).delete()){
						JOptionPane.showMessageDialog(JEditor.frame, "Unable to delete the file. Please try later.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					FileMenu.close.doClick();
				}
				
			}
		});
		
		details.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FilePropertiesDialog();
			}
		});
	}
	
	public void addIcons(){
		details.setIcon(ImageLoader.loadImage("images_small/properties.png"));
	}
	
	public void addToMenu(){
		add(delete);
		add(details);
	}

}
