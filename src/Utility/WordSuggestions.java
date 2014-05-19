package Utility;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import Components.SuggestionMenuItem;

public class WordSuggestions {

	private JTextArea textArea;
	private ArrayList<String> list;
	private JPopupMenu menu;

	public WordSuggestions(JTextArea textArea){
		this.textArea = textArea;
		init();
		registerComponent();
	}

	public void init(){
		menu = new JPopupMenu();
		list = new ArrayList<String>();
		list.add("salam");
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

	}
	
	public ArrayList<String> getList(){
		return list;
	}

	public void buildAndShowMenu(){

		int offset, start;
		String subword = null;
		try {
			
			offset = textArea.getLineOfOffset(textArea.getCaretPosition());
			start=textArea.getLineStartOffset(offset);
			subword = textArea.getText(start, (textArea.getCaretPosition()-start)); 
			
		} catch (BadLocationException e) {
			e.printStackTrace();
			return;
		}
		
		subword = subword.lastIndexOf(" ") == -1 ? subword : subword.substring(subword.lastIndexOf(" ")+1);
		
		if(subword.equals("") || subword.equals(" ")){
			return;
		}
		
		menu.removeAll();
		
		for(String word : list){
			
			if(word.startsWith(subword)){
				menu.add(new SuggestionMenuItem(word, textArea , subword));
			}
		}
		
		Point location;
		
		try {
			location = textArea.modelToView(textArea.getCaretPosition()).getLocation();
		} catch (BadLocationException e) {
			e.printStackTrace();
			return;
		}
	
		menu.show(textArea, location.x, textArea.getBaseline(0, 0) + location.y);

	}

}
