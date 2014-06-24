package MenuEvents;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Components.CTabbedPane;
import Menus.SourceMenu;

public class SourceMenuEvent implements MenuListener{

	@Override
	public void menuCanceled(MenuEvent arg0) {}

	@Override
	public void menuDeselected(MenuEvent arg0) {}

	@Override
	public void menuSelected(MenuEvent e) {

		
		if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
			SourceMenu.renderHtml.setEnabled(false);
			SourceMenu.renderHtml.setToolTipText("no file opened in the current tab");
		}
		else{
			SourceMenu.renderHtml.setEnabled(true);
			SourceMenu.renderHtml.setToolTipText("render the text as html");
		}
		
	}

}
