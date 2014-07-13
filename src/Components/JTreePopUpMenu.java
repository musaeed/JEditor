package Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.DefaultMutableTreeNode;

import Menus.CurrentFileMenu;

public class JTreePopUpMenu extends JPopupMenu implements PopupMenuListener{

	private static final long serialVersionUID = 1L;
	private CMenuItem close,openFolder,properties,copyFilePath;
	
	public JTreePopUpMenu(){
		init();
		addToMenu();
		addActions();
	}
	
	public void init(){
		close = new CMenuItem("Close", "close this file", 'C', null);
		openFolder = new CMenuItem("Open containing folder", "open the folder containing this file", 'O', null);
		properties = new CMenuItem("Properties", "show the properties of this file", 'P', null);
		copyFilePath = new CMenuItem("Copy file path", "copy the file path to the clipboard", 'F', null);
		addPopupMenuListener(this);
	}
	
	public void addToMenu(){
		add(close);
		add(copyFilePath);
		add(properties);
		add(openFolder);
	}
	
	public void addActions(){
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RibbonMenu.close.doClick();
			}
		});
		
		copyFilePath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TextPanelPopupMenu.copyfilepath.doClick();
			}
		});
		
		properties.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CurrentFileMenu.details.doClick();
				
			}
		});
		
		openFolder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TextPanelPopupMenu.openFolder.doClick();
			}
		});
		
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				FileViewer.getInstance().getTree().getLastSelectedPathComponent();

		if(node == null){
			close.setEnabled(false);
			openFolder.setEnabled(false);
			properties.setEnabled(false);
			copyFilePath.setEnabled(false);
		}
		
		else{
			close.setEnabled(true);
			openFolder.setEnabled(true);
			properties.setEnabled(true);
			copyFilePath.setEnabled(true);
		}
	}
	

}
