package Utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import Components.CTabbedPane;

public class WordSuggestions {

	private JTextArea textArea;
	private ArrayList<String> list;
	private JPopupMenu menu;
	private JList<Object> words;
	private String CurrentSubWord;

	public WordSuggestions(JTextArea textArea){
		this.textArea = textArea;
		init();
		registerComponent();
	}

	public void init(){
		menu = new JPopupMenu();
		list = new ArrayList<String>();
	}

	public void registerComponent(){

		textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_DOWN_MASK), 0);
		textArea.getActionMap().put(0, new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				buildAndShowMenu();
			}
		});

		textArea.addKeyListener(new KeyAdapter() {

			int count = 0;
			boolean showMenu = true;

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					menudown(e);
				}

				if(e.getKeyCode() == KeyEvent.VK_UP){
					menuUp(e);
				}

				if(e.getKeyCode() == KeyEvent.VK_ENTER){

					if(menu.isShowing()){

						e.consume();

						textArea.setSelectionStart(textArea.getCaretPosition() - CurrentSubWord.length() - 1);
						textArea.setSelectionEnd(textArea.getCaretPosition());
						textArea.replaceSelection(((String) words.getSelectedValue()));
						textArea.requestFocus();

					}

				}

				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					showMenu = true;
					count = 0;
				}
				
				if(e.getKeyCode() == KeyEvent.VK_PASTE){
					System.out.println("A paste occured");
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {

				if(menu.isShowing()){
					menu.setVisible(false);
					count = 0;
				}

				if(!showMenu){
					count = 0;
					return;
				}

				if(++count == 4){
					buildAndShowMenu();
					count = 0;
					showMenu = false;
				}

			}


		});

	}

	public ArrayList<String> getList(){
		return list;
	}

	public void buildAndShowMenu(){


		int offset, start;
		String subword = null;

		try {

			offset = textArea.getLineOfOffset(textArea.getCaretPosition());
			start = textArea.getLineStartOffset(offset);
			subword = textArea.getText(start, (textArea.getCaretPosition()-start)); 

		} catch (BadLocationException e) {
			e.printStackTrace();
			return;
		}

		subword = subword.lastIndexOf(" ") == -1 ? subword : subword.substring(subword.lastIndexOf(" ")+1);

		if(subword.equals("") || subword.equals(" ")){
			return;
		}

		CurrentSubWord = subword;

		menu.removeAll();

		menu.setLayout(new BorderLayout());
		JLabel label = new JLabel("Auto suggestions:");
		label.setForeground(Color.ORANGE);
		menu.add(label , BorderLayout.NORTH);

		ArrayList<Object> objects = new ArrayList<>();

		for(String word : list){

			if(word.startsWith(subword)){
				objects.add(word);
			}
		}

		if(objects.size() == 0){
			return;
		}

		words = new JList<Object>(objects.toArray());
		words.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		words.setSelectedIndex(0);
		addlistMouseListener();

		menu.add(words , BorderLayout.CENTER);

		JLabel endLabel = new JLabel("<html>Use &uarr; key and &darr; key to navigate through the words.</html>");
		endLabel.setForeground(Color.ORANGE);
		menu.add(endLabel , BorderLayout.SOUTH);

		Point location;

		try {
			location = textArea.modelToView(textArea.getCaretPosition()).getLocation();
		} catch (BadLocationException e) {
			e.printStackTrace();
			return;
		}

		menu.show(textArea, location.x+10, textArea.getBaseline(0, 0) + location.y);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CTabbedPane.getInstance().getPanel().getTextArea().requestFocusInWindow();
			}
		});

	}

	public void menudown(KeyEvent e){

		if(!menu.isShowing()){
			return;
		}

		e.consume();
		words.setSelectedIndex(words.getSelectedIndex() + 1);
		CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();
	}

	public void menuUp(KeyEvent e){

		if(!menu.isShowing()){
			return;
		}

		e.consume();
		words.setSelectedIndex(words.getSelectedIndex() - 1);
		CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();
	}

	public void addlistMouseListener(){

		words.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					textArea.setSelectionStart(textArea.getCaretPosition() - CurrentSubWord.length() - 1);
					textArea.setSelectionEnd(textArea.getCaretPosition());
					textArea.replaceSelection(((String) words.getSelectedValue()));
					menu.setVisible(false);
					textArea.requestFocus();

				}
			}
		});

	}

}
