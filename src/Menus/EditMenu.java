package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import Components.CCheckBoxMenuItem;
import Components.CMenu;
import Components.CMenuItem;
import Components.CTabbedPane;

public class EditMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	private CMenuItem viewSidePane,undo, undoAll, redo, redoAll, cut, copy, paste, selectAll;
	private CCheckBoxMenuItem suReadOnly;

	public EditMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
		addActions();
	}

	public void init(){
		viewSidePane = new CMenuItem("View side pane", "view the side pane", 'V', null);
		undo = new CMenuItem("Undo", "undo the last action", 'U', KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		redo = new CMenuItem("Redo", "redo the last action", 'R', KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
		undoAll = new CMenuItem("Undo all", "undo all the actions", '1', KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
		redoAll = new CMenuItem("Redo all", "redo all the actions", '1' , KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
		cut = new CMenuItem("Cut", "cut the text", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		copy = new CMenuItem("Copy", "cut the text", 'Y', KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		paste = new CMenuItem("Paste", "paste the text", 'P', KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		selectAll = new CMenuItem("Select all", "select all the text in the text area", 'S', KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		suReadOnly = new CCheckBoxMenuItem("Read only", "set the editor to read only mode");

	}

	public void addToMenu(){
		add(viewSidePane);
		add(undo);
		add(redo);
		add(undoAll);
		add(redoAll);
		add(cut);
		add(copy);
		add(paste);
		add(selectAll);
		add(suReadOnly);
	}

	public void addActions(){
		viewSidePane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

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
				// TODO Auto-generated method stub

			}
		});
	}
}
