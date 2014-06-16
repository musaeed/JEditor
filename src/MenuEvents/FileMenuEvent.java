package MenuEvents;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Components.CTabbedPane;
import Menus.FileMenu;

public class FileMenuEvent implements MenuListener{

	@Override
	public void menuCanceled(MenuEvent arg0) {}

	@Override
	public void menuDeselected(MenuEvent arg0) {}

	@Override
	public void menuSelected(MenuEvent e) {
		
		if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
			FileMenu.delete.setEnabled(false);
		}
		else{
			FileMenu.delete.setEnabled(true);
		}
		
	}

}
