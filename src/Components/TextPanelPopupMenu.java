package Components;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import com.inet.jortho.SpellChecker;

import MenuEvents.PopupMenuEvent;
import Menus.EditMenu;
import Menus.ToolMenu;
import Utility.ImageLoader;
import Utility.Notifications;

public class TextPanelPopupMenu extends JPopupMenu{

	private static final long serialVersionUID = 1L;
	private static TextPanelPopupMenu instance = null;
	public static CMenuItem undo,redo,undoAll,redoAll,cut,copy,paste,selectAll,copyfilepath,openFolder,openterminal;
	private JMenu words;

	public TextPanelPopupMenu(){
		init();
		addToMenu();
		addActions();
		addPopupMenuListener(new PopupMenuEvent());
		addIcons();
	}

	public static TextPanelPopupMenu getInstance(){

		if(instance == null){
			instance = new TextPanelPopupMenu();
		}

		return instance;
	}

	public void init(){

		undo = new CMenuItem("Undo", "undo the last action", 'U', null);
		redo = new CMenuItem("Redo", "redo the last action", 'R', null);
		undoAll = new CMenuItem("Undo all", "undo all the actions", '1', null);
		redoAll = new CMenuItem("Redo all", "redo all the actions", '1', null);
		cut = new CMenuItem("Cut", "cut the text to clipboard", 'C', null);
		copy = new CMenuItem("Copy", "copy the text to clipboard", 'O', null);
		paste = new CMenuItem("Paste", "paste the text from clipboard", 'P', null);
		selectAll = new CMenuItem("Select all", "select all the text", 'S', null);
		copyfilepath = new CMenuItem("Copy file path", "copy the current file path to clipboard", 'F', null);
		openFolder = new CMenuItem("Open containing folder ", "open the folder in which this file is placed", 'A', null);
		openterminal = new CMenuItem("Open terminal in current directory", "open the teriminal with this directory set", 'T', null);
		words = SpellChecker.createCheckerMenu();
		words.setToolTipText("Spelling suggestions");
	}

	public void addToMenu(){
		add(undo);
		add(redo);
		add(undoAll);
		add(redoAll);
		addSeparator();
		add(cut);
		add(copy);
		add(paste);
		add(selectAll);
		addSeparator();
		add(copyfilepath);
		add(openFolder);
		add(openterminal);
		add(words);
	}
	
	public void addIcons(){
		undo.setIcon(EditMenu.undo.getIcon());
		redo.setIcon(EditMenu.redo.getIcon());
		undoAll.setIcon(EditMenu.undoAll.getIcon());
		redoAll.setIcon(EditMenu.redoAll.getIcon());
		cut.setIcon(EditMenu.cut.getIcon());
		copy.setIcon(EditMenu.copy.getIcon());
		paste.setIcon(EditMenu.paste.getIcon());
		selectAll.setIcon(EditMenu.selectAll.getIcon());
		openFolder.setIcon(ImageLoader.loadImage("images_small/open.gif"));
		openterminal.setIcon(ImageLoader.loadImage("images_small/terminal.png"));
		words.setIcon(ToolMenu.search.getIcon());
	}

	public void addActions(){
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

		copyfilepath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
					return;
				}

				StringSelection stringSelection = new StringSelection (CTabbedPane.getInstance().getPanel().getCurrentFilePath());
				Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				clpbrd.setContents (stringSelection, null);
			}
		});

		openFolder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Notifications.showNotification("Opening the folder containing file ...");

				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
					
					try {
						Runtime.getRuntime().exec("nautilus");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return;
				}

				try {
					Desktop.getDesktop().open(new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).getParentFile());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		openterminal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Notifications.showNotification("Opening the system terminal in the current directory ...");

				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
					
					try {
						Runtime.getRuntime().exec(new String [] {"gnome-terminal"});
					} catch (IOException e1) {

						e1.printStackTrace();

					}
					
					return;
				}

				try {
					Runtime.getRuntime().exec(new String [] {"gnome-terminal" , "--working-directory=" + new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).getParent()});
				} catch (IOException e1) {

					e1.printStackTrace();

				}
			}
		});

	}

}
