package MenuEvents;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Components.CTabbedPane;
import Menus.EditMenu;

public class EditMenuEvent implements MenuListener{

	@Override
	public void menuCanceled(MenuEvent e) {}

	@Override
	public void menuDeselected(MenuEvent e) {}

	@Override
	public void menuSelected(MenuEvent e) {
		
		RSyntaxTextArea textArea = CTabbedPane.getInstance().getPanel().getTextArea();
		
		if(textArea.canUndo()){
			EditMenu.undo.setEnabled(true);
			EditMenu.undoAll.setEnabled(true);
		}
		else{
			
			EditMenu.undo.setEnabled(false);
			EditMenu.undoAll.setEnabled(false);			
		}
		
		if(textArea.canRedo()){
			EditMenu.redo.setEnabled(true);
			EditMenu.redoAll.setEnabled(true);
		}
		else{
			
			EditMenu.redo.setEnabled(false);
			EditMenu.redoAll.setEnabled(false);			
		}
		
		if(CTabbedPane.getInstance().getPanel().isReadAble()){
			EditMenu.suReadOnly.setSelected(true);
		}
		else{
			EditMenu.suReadOnly.setSelected(false);
		}
		
	}

}
