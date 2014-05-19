package Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class SuggestionMenuItem extends JMenuItem{

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private String word;
	
	public SuggestionMenuItem(String text , JTextArea area, String word){
		this.textArea = area;
		this.word = word;
		setText(text);
		addAction();
	}
	
	public void addAction(){
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				textArea.setSelectionStart(textArea.getCaretPosition() - word.length());
				textArea.setSelectionEnd(textArea.getCaretPosition());
				textArea.replaceSelection(getText());
				
			}
			
		});
		
	}

}
