package component_listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Utility.WordSuggestions;

import core.TextPanel;

public class SpaceListenerForAddingSuggestions extends KeyAdapter{
	
	private TextPanel panel;
	
	public SpaceListenerForAddingSuggestions(TextPanel panel){
		this.panel = panel;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){

			JTextArea ta = (RSyntaxTextArea)e.getSource();
			int offset,start,end;
			String word = null;
			
			try {
				offset=ta.getLineOfOffset(ta.getCaretPosition());
				start=ta.getLineStartOffset(offset);
				end=ta.getLineEndOffset(offset);
				
				word = ta.getText(start, (end-start)-1);
				word = word.lastIndexOf(" ") == -1 ? word : word.substring(word.lastIndexOf(" ")+1);
				
			} catch (BadLocationException ex) {
				ex.printStackTrace();
				return;
			}
			
			WordSuggestions suggestions = panel.getWordSuggestions();
			ArrayList<String> list = suggestions.getList();
			
			if(!list.contains(word)){
				list.add(word);
			}
		}
	}

}
