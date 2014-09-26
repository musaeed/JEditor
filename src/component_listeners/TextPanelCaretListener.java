package component_listeners;


import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Components.BottomPanel;

public class TextPanelCaretListener implements CaretListener{

	@Override
	public void caretUpdate(CaretEvent e) {
		JTextArea editArea = (JTextArea)e.getSource();
		int linenum = 1;
		int columnnum = 1;
		try {
			int caretpos = editArea.getCaretPosition();
			linenum = editArea.getLineOfOffset(caretpos);
			columnnum = caretpos - editArea.getLineStartOffset(linenum);
			linenum += 1;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		updateStatus(linenum, columnnum);
	    
	}

	public void updateStatus(int linenum , int cnumber){
		BottomPanel.rowCount.setText("Row: " + linenum + " " + " Column: " +(cnumber + 1));
	}
}
