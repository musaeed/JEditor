package component_listeners;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utility.EditorUtilities;

import Components.BottomPanel;
import Components.CTabbedPane;
import Components.FileViewer;
import Gui.JEditor;

public class TabChangeListener implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent e) {
		try{
			JEditor.frame.setTitle("JEditor - " + CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null ? "Untitled" : CTabbedPane.getInstance().getPanel().getCurrentFilePath());

		} catch (Exception ex){

			JEditor.frame.setTitle("JEditor - Untitled");
		}
		
		if(CTabbedPane.getInstance().getPanel() == null){
			return;
		}
		
		if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
			BottomPanel.fileType.setText("Untitled");
			return;
		}
		
		EditorUtilities.updateLabel(CTabbedPane.getInstance().getPanel().getCurrentFilePath());
		FileViewer.getInstance().setSelectedFile(CTabbedPane.getInstance().getPanel().unique);
	}
}
