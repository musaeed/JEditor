package component_listeners;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Components.CTabbedPane;

public class TextAreaDocumentListener implements DocumentListener{

	@Override
	public void changedUpdate(DocumentEvent arg0) {

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		
		updateInfo();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		
		updateInfo();
	}
	
	public void updateInfo(){
		
		if(!CTabbedPane.getInstance().getPanel().isNeedsToBeSaved()){
			CTabbedPane.getInstance().getPanel().setNeedsToBeSaved(true);
			CTabbedPane.getInstance().setTitleAt(CTabbedPane.getInstance().getSelectedIndex(), CTabbedPane.getInstance().getTitleAt(CTabbedPane.getInstance().getSelectedIndex())+"*");
			CTabbedPane.getInstance().setIconAt(CTabbedPane.getInstance().getSelectedIndex(), new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/document_new_small.png"))));
		}
	}

}
