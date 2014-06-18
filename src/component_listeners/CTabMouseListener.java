package component_listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Components.BottomPanel;
import Components.CTabbedPane;
import Components.RibbonMenu;
import Utility.EditorUtilities;

public class CTabMouseListener extends MouseAdapter{

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON2){
			RibbonMenu.close.doClick();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		String filepath = ((CTabbedPane)e.getSource()).getPanel().getCurrentFilePath();
		
		if(filepath == null){
			BottomPanel.fileType.setText("Untitled");
			return;
		}
		
		EditorUtilities.updateLabel(filepath);
	}
	
	

}
