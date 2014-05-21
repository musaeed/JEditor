package MenuEvents;

import javax.swing.event.PopupMenuListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Components.CTabbedPane;
import Components.TextPanelPopupMenu;

public class PopupMenuEvent implements PopupMenuListener{

	@Override
	public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}

	@Override
	public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}

	@Override
	public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
		
		RSyntaxTextArea textArea = CTabbedPane.getInstance().getPanel().getTextArea();
		
		if(textArea.canUndo()){
			TextPanelPopupMenu.undo.setEnabled(true);
			TextPanelPopupMenu.undoAll.setEnabled(true);
		}
		else{
			
			TextPanelPopupMenu.undo.setEnabled(false);
			TextPanelPopupMenu.undoAll.setEnabled(false);
			
		}
		
		if(textArea.canRedo()){
			TextPanelPopupMenu.redo.setEnabled(true);
			TextPanelPopupMenu.redoAll.setEnabled(true);
		}
		else{
			
			TextPanelPopupMenu.redo.setEnabled(false);
			TextPanelPopupMenu.redoAll.setEnabled(false);
		}
		
		if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
			TextPanelPopupMenu.copyfilepath.setEnabled(false);
		}
		else{
			TextPanelPopupMenu.copyfilepath.setEnabled(true);
		}
		
	}

}
