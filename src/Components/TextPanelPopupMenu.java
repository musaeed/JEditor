package Components;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPopupMenu;

public class TextPanelPopupMenu {

	private static TextPanelPopupMenu instance = null;
	private JPopupMenu menu;
	private CMenuItem undo,redo,undoAll,redoAll,cut,copy,paste,selectAll,copyfilepath,openFolder,openterminal;

	private TextPanelPopupMenu(){
		init();
		addToMenu();
		addActions();
	}

	public static TextPanelPopupMenu getInstance(){

		if(instance == null){
			instance = new TextPanelPopupMenu();
		}

		return instance;
	}

	public void init(){

		menu = new JPopupMenu();

		undo = new CMenuItem("Undo", "undo the last action", 'U', null);
		redo = new CMenuItem("Redo", "redo the last action", 'R', null);
		undoAll = new CMenuItem("Undo all", "undo all the actions", '1', null);
		redoAll = new CMenuItem("Redo all", "redo all the actions", '1', null);
		cut = new CMenuItem("Cut", "cut the text to clipboard", 'C', null);
		copy = new CMenuItem("copy", "copy the text to clipboard", 'O', null);
		paste = new CMenuItem("Paste", "paste the text from clipboard", 'P', null);
		selectAll = new CMenuItem("Select all", "select all the text", 'S', null);
		copyfilepath = new CMenuItem("Copy file path", "copy the current file path to clipboard", 'F', null);
		openFolder = new CMenuItem("Open containing folder ", "open the folder in which this file is placed", 'A', null);
		openterminal = new CMenuItem("Open terminal in current directory", "open the teriminal with this directory set", 'T', null);

	}

	public void addToMenu(){
		menu.add(undo);
		menu.add(redo);
		menu.add(undoAll);
		menu.add(redoAll);
		menu.addSeparator();
		menu.add(cut);
		menu.add(copy);
		menu.add(paste);
		menu.add(selectAll);
		menu.addSeparator();
		menu.add(copyfilepath);
		menu.add(openFolder);
		menu.add(openterminal);
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

				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
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

				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
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

	public JPopupMenu getMenu(){
		return menu;
	}

}
