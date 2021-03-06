package Menus;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import Components.CCheckBoxMenuItem;
import Components.CMenu;
import Components.CMenuItem;
import Components.CTabbedPane;
import Components.RibbonMenu;
import Components.SplitPanelLeftComponent;
import Gui.JEditor;
import MenuEvents.EditMenuEvent;
import Utility.ImageLoader;
import Utility.Notifications;

public class EditMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	public static CMenuItem undo, undoAll, redo, redoAll, cut, copy, paste, selectAll,fullscreen;
	public static CCheckBoxMenuItem suReadOnly , viewRibbon, viewSidePane;

	public EditMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				addToMenu();
				addActions();
				addMenuListener(new EditMenuEvent());
				addIcons();
			}
		}).start();

	}

	public void init(){
		viewRibbon = new CCheckBoxMenuItem("View ribbon menu", "hide or un hide the ribbon menu");
		viewRibbon.setSelected(true);
		viewSidePane = new CCheckBoxMenuItem("View side pane", "view the side pane");
		viewSidePane.setSelected(true);
		undo = new CMenuItem("Undo", "undo the last action", 'U', KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		redo = new CMenuItem("Redo", "redo the last action", 'R', KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
		undoAll = new CMenuItem("Undo all", "undo all the actions", '1', KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
		redoAll = new CMenuItem("Redo all", "redo all the actions", '1' , KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
		cut = new CMenuItem("Cut", "cut the text", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		copy = new CMenuItem("Copy", "cut the text", 'Y', KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		paste = new CMenuItem("Paste", "paste the text", 'P', KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		selectAll = new CMenuItem("Select all", "select all the text in the text area", 'S', KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		suReadOnly = new CCheckBoxMenuItem("Read only", "set the editor to read only mode");
		fullscreen = new CMenuItem("Full screen", "view the editor in full screen", 'F', null);
	}

	public void addToMenu(){
		add(viewRibbon);
		add(viewSidePane);
		addSeparator();
		add(undo);
		add(redo);
		add(undoAll);
		add(redoAll);
		add(cut);
		add(copy);
		add(paste);
		add(selectAll);
		addSeparator();
		add(suReadOnly);
	}
	
	public void addIcons(){
		undo.setIcon(ImageLoader.loadImage("images_small/undo.png"));
		undoAll.setIcon(ImageLoader.loadImage("images_small/undoall.png"));
		redo.setIcon(ImageLoader.loadImage("images_small/redo.png"));
		redoAll.setIcon(ImageLoader.loadImage("images_small/redoall.png"));
		cut.setIcon(ImageLoader.loadImage("images_small/cut.png"));
		copy.setIcon(ImageLoader.loadImage("images_small/copy.gif"));
		paste.setIcon(ImageLoader.loadImage("images_small/paste.png"));
		selectAll.setIcon(ImageLoader.loadImage("images_small/selectall.png"));
	}

	public void addActions(){
		
		viewRibbon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!viewRibbon.isSelected()){
					JEditor.frame.remove(RibbonMenu.getInstance());
				}
				else{
					JEditor.frame.add(RibbonMenu.getInstance() , BorderLayout.NORTH);
				}
				
				JEditor.frame.validate();
				JEditor.frame.repaint();
			}
		});
		
		viewSidePane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!viewSidePane.isSelected()){
					JEditor.splitPane.setLeftComponent(null);
				}
				else{
					JEditor.splitPane.setLeftComponent(SplitPanelLeftComponent.getInstance());
					JEditor.splitPane.setDividerLocation(130);
				}

			}
		});

		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (CTabbedPane.getInstance().getPanel().getTextArea().canUndo()) {
					CTabbedPane.getInstance().getPanel().getTextArea().undoLastAction();
				}
			}
		});

		redo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (CTabbedPane.getInstance().getPanel().getTextArea().canRedo()) {
					CTabbedPane.getInstance().getPanel().getTextArea().redoLastAction();
				}	
			}
		});

		undoAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				while (CTabbedPane.getInstance().getPanel().getTextArea().canUndo()) {
					CTabbedPane.getInstance().getPanel().getTextArea().undoLastAction();
				}
			}
		});

		redoAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				while (CTabbedPane.getInstance().getPanel().getTextArea().canRedo()) {
					CTabbedPane.getInstance().getPanel().getTextArea().redoLastAction();
				}	
			}
		});

		cut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CTabbedPane.getInstance().getPanel().getTextArea().cut();

			}
		});

		copy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CTabbedPane.getInstance().getPanel().getTextArea().copy();

			}
		});

		paste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CTabbedPane.getInstance().getPanel().getTextArea().paste();

			}
		});

		selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CTabbedPane.getInstance().getPanel().getTextArea().selectAll();

			}
		});

		suReadOnly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(suReadOnly.isSelected()){
					CTabbedPane.getInstance().getPanel().setReadOnly(false);
					Notifications.showNotification("Read-only mode activated");
				}
				else{
					CTabbedPane.getInstance().getPanel().setReadOnly(true);
					Notifications.showNotification("Read-only mode deactivated");
				}

			}
		});
		
	}
}
