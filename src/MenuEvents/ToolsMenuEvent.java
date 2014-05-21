package MenuEvents;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Components.CTabbedPane;
import Menus.ToolMenu;

public class ToolsMenuEvent implements MenuListener{

	@Override
	public void menuCanceled(MenuEvent e) {}

	@Override
	public void menuDeselected(MenuEvent e) {}

	@Override
	public void menuSelected(MenuEvent e) {
		
		RSyntaxTextArea textArea = CTabbedPane.getInstance().getPanel().getTextArea();
		
		if(textArea.getSelectedText() == null){
			
			ToolMenu.toLower.setEnabled(false);
			ToolMenu.toUpper.setEnabled(false);
			
		}
		else{
			
			ToolMenu.toLower.setEnabled(true);
			ToolMenu.toUpper.setEnabled(true);
		}
	}

}
