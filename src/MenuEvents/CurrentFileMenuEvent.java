package MenuEvents;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Components.CTabbedPane;
import Menus.CurrentFileMenu;

public class CurrentFileMenuEvent implements MenuListener{

	@Override
	public void menuCanceled(MenuEvent arg0) {}

	@Override
	public void menuDeselected(MenuEvent arg0) {}

	@Override
	public void menuSelected(MenuEvent arg0) {
		
		if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
			CurrentFileMenu.delete.setEnabled(false);
			CurrentFileMenu.details.setEnabled(false);
		}
		else{
			CurrentFileMenu.delete.setEnabled(true);
			CurrentFileMenu.details.setEnabled(true);
		}
		
	}

}
